package com.inf1.app.batch.modelisations.calculators.machinelearning.univariate;

import com.inf1.app.batch.modelisations.calculators.machinelearning.MachineLearningCalculator;
import com.inf1.app.batch.modelisations.calculators.utils.WekaDataset;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InfectionUnivariateCalculator extends MachineLearningCalculator {

    @Override
    public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
        ModelisationDTO modelisationDTO = new ModelisationDTO();
        try {
            // Initializing dataset
            Instances dataset = initDataset(situationsReellesDTO);
            // Predicting
            univariateLinearRegression(dataset, modelisationDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelisationDTO;
    }

    @Override
    public Instances initDataset(List<SituationReelleDTO> situationsReellesDTO) throws ParseException {
        // Defining dataset attributes
        ArrayList<Attribute> atts = new ArrayList<>();
        atts.add(new Attribute("date", "yyyy-MM-dd"));
        atts.add(new Attribute("cumul_casConfirmes"));
        atts.add(new Attribute("cumul_infections_J+1"));

        Instances dataset = new Instances("InfectionLinearCalculator dataset", atts, 0);
        dataset.setClassIndex(atts.size() - 1);

        // Adding a first line of NaN values to let the model starts learning from the real first values
        double[] firstInstanceValue = new double[dataset.numAttributes()];
        firstInstanceValue[0] = dataset.attribute("date").parseDate("2020-03-01");
        firstInstanceValue[1] = Double.NaN;
        firstInstanceValue[2] = Double.NaN;
        dataset.add(new DenseInstance(1.0, firstInstanceValue));

        // Parsing each line of situationsReellesDTO list to fill the dataset with real values
        for (int i = 0; i < situationsReellesDTO.size(); i++) {
            double[] instanceValue = new double[dataset.numAttributes()];
            instanceValue[0] = dataset.attribute("date")
                    .parseDate(situationsReellesDTO.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            instanceValue[1] = situationsReellesDTO.get(i).getCumulCasConfirmes() == null ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getCumulCasConfirmes());
            instanceValue[2] = (i + 1 >= situationsReellesDTO.size()) ? Double.NaN
                    : situationsReellesDTO.get(i + 1).getCumulCasConfirmes() == null ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i + 1).getCumulCasConfirmes());
            // Changing negative values to NaN values
            WekaDataset.cleanNegativeValues(instanceValue);
            dataset.add(new DenseInstance(1.0, instanceValue));
        }
        return dataset;
    }
}
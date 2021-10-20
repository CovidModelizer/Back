package com.inf1.app.batch.modelisations.calculators.machinelearning.multivariate;

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

public class InfectionMultivariateCalculator extends MachineLearningCalculator {

    @Override
    public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
        ModelisationDTO modelisationDTO = new ModelisationDTO();
        try {
            // Initializing dataset
            Instances dataset = initDataset(situationsReellesDTO);
            // Predicting
            multivariateLinearRegression(dataset, modelisationDTO);
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
        atts.add(new Attribute("nouveaux_deces"));
        atts.add(new Attribute("nouveaux_gueris"));
        atts.add(new Attribute("nouveaux_decesEhpad"));
        atts.add(new Attribute("total_reanimation"));
        atts.add(new Attribute("nouveaux_casConfirmes"));
        atts.add(new Attribute("total_hospitalises"));
        atts.add(new Attribute("nouveaux_testsRealises"));
        atts.add(new Attribute("nouveaux_testsPositifs"));
        atts.add(new Attribute("nouveaux_casConfirmesEhpad"));
        atts.add(new Attribute("nouvelles_reanimations"));
        atts.add(new Attribute("nouvelles_hospitalisations"));
        atts.add(new Attribute("nouvelles_premieresInjections"));
        atts.add(new Attribute("nouveau_r0"));
        atts.add(new Attribute("nouvelles_infections_J+N"));

        Instances dataset = new Instances("InfectionMachineLearningCalculator dataset", atts, 0);
        dataset.setClassIndex(atts.size() - 1);

        // Adding a first line of NaN values to let the model starts learning from the real first values
        double[] firstInstanceValue = new double[dataset.numAttributes()];
        firstInstanceValue[0] = dataset.attribute("date").parseDate("2020-03-01");
        for (int j = 1; j < firstInstanceValue.length; j++) {
            firstInstanceValue[j] = Double.NaN;
        }
        dataset.add(new DenseInstance(1.0, firstInstanceValue));

        // Parsing each line of situationsReellesDTO list to fill the dataset with real values
        for (int i = 0; i < situationsReellesDTO.size(); i++) {
            double[] instanceValue = new double[dataset.numAttributes()];
            instanceValue[0] = dataset.attribute("date").parseDate(
                    situationsReellesDTO.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            instanceValue[1] = situationsReellesDTO.get(i).getNouveauxDeces() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouveauxDeces());
            instanceValue[2] = situationsReellesDTO.get(i).getNouveauxGueris() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouveauxGueris());
            instanceValue[3] = situationsReellesDTO.get(i).getNouveauxDecesEhpad() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouveauxDecesEhpad());
            instanceValue[4] = situationsReellesDTO.get(i).getReanimation() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getReanimation());
            instanceValue[5] = situationsReellesDTO.get(i).getNouveauxCasConfirmes() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouveauxCasConfirmes());
            instanceValue[6] = situationsReellesDTO.get(i).getHospitalises() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getHospitalises());
            instanceValue[7] = situationsReellesDTO.get(i).getTestsRealises() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getTestsRealises());
            instanceValue[8] = situationsReellesDTO.get(i).getTestsPositifs() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getTestsPositifs());
            instanceValue[9] = situationsReellesDTO.get(i).getNouveauxCasConfirmesEhpad() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouveauxCasConfirmesEhpad());
            instanceValue[10] = situationsReellesDTO.get(i).getNouvellesReanimations() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesReanimations());
            instanceValue[11] = situationsReellesDTO.get(i).getNouvellesHospitalisations() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesHospitalisations());
            instanceValue[12] = situationsReellesDTO.get(i).getNouvellesPremieresInjections() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesPremieresInjections());
            instanceValue[13] = Double.NaN;
            // Adding the r0 indicator when it exists
            for (int d = 0; d < 15; d++) {
                if (i > 15 && !(situationsReellesDTO.get(i - d).getR0() == null)) {
                    instanceValue[13] = Double.parseDouble(situationsReellesDTO.get(i - d).getR0());
                    break;
                }
            }
            instanceValue[14] = Double.NaN;
            // Changing negative values to NaN values
            WekaDataset.cleanNegativeValues(instanceValue);
            dataset.add(new DenseInstance(1.0, instanceValue));
        }
        return dataset;
    }
}
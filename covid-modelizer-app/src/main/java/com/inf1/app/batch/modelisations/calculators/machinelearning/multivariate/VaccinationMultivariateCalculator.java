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

public class VaccinationMultivariateCalculator extends MachineLearningCalculator {

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
        atts.add(new Attribute("total_stockNombreTotalDoses"));
        atts.add(new Attribute("total_stockNombreDosesPfizer"));
        atts.add(new Attribute("total_stockNombreDosesModerna"));
        atts.add(new Attribute("total_stockEhpadNombreDosesPfizer"));
        atts.add(new Attribute("nouvelles_premieresInjections"));
        atts.add(new Attribute("total_prisesRendezVousSemaine"));
        atts.add(new Attribute("total_prisesRendezVousSemaineRang1"));
        atts.add(new Attribute("total_prisesRendezVousSemaineRang2"));
        atts.add(new Attribute("nouvelles_livraisonsNombreTotalDoses"));
        atts.add(new Attribute("nouvelles_livraisonsNombreDosesPfizer"));
        atts.add(new Attribute("nouvelles_livraisonsNombreDosesModerna"));
        atts.add(new Attribute("nouvelles_vaccinations_J+N"));

        Instances dataset = new Instances("VaccinationMachineLearningCalculator dataset", atts, 0);
        dataset.setClassIndex(atts.size() - 1);

        // Adding a first line of NaN values to let the model starts learning from the real first values
        double[] firstInstanceValue = new double[dataset.numAttributes()];
        firstInstanceValue[0] = dataset.attribute("date").parseDate("2020-12-26");
        for (int j = 1; j < firstInstanceValue.length; j++) {
            firstInstanceValue[j] = Double.NaN;
        }
        dataset.add(new DenseInstance(1.0, firstInstanceValue));

        // Parsing each line of situationsReellesDTO list to fill the dataset with real values
        for (int i = 300; i < situationsReellesDTO.size(); i++) {
            double[] instanceValue = new double[dataset.numAttributes()];
            instanceValue[0] = dataset.attribute("date").parseDate(
                    situationsReellesDTO.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            instanceValue[1] = situationsReellesDTO.get(i).getStockNombreTotalDoses() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getStockNombreTotalDoses());
            instanceValue[2] = situationsReellesDTO.get(i).getStockNombreDosesPfizer() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getStockNombreDosesPfizer());
            instanceValue[3] = situationsReellesDTO.get(i).getStockNombreDosesModerna() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getStockNombreDosesModerna());
            instanceValue[4] = situationsReellesDTO.get(i).getStockEhpadNombreDosesPfizer() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getStockEhpadNombreDosesPfizer());
            instanceValue[5] = situationsReellesDTO.get(i).getNouvellesPremieresInjections() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesPremieresInjections());
            instanceValue[6] = situationsReellesDTO.get(i).getTotalPrisesRendezVousSemaine() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getTotalPrisesRendezVousSemaine());
            instanceValue[7] = situationsReellesDTO.get(i).getPrisesRendezVousSemaineRang1() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getPrisesRendezVousSemaineRang1());
            instanceValue[8] = situationsReellesDTO.get(i).getPrisesRendezVousSemaineRang2() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getPrisesRendezVousSemaineRang2());
            instanceValue[9] = situationsReellesDTO.get(i).getNouvellesLivraisonsNombreTotalDoses() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesLivraisonsNombreTotalDoses());
            instanceValue[10] = situationsReellesDTO.get(i).getNouvellesLivraisonsNombreDosesPfizer() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesLivraisonsNombreDosesPfizer());
            instanceValue[11] = situationsReellesDTO.get(i).getNouvellesLivraisonsNombreDosesModerna() == null
                    ? Double.NaN
                    : Double.parseDouble(situationsReellesDTO.get(i).getNouvellesLivraisonsNombreDosesModerna());
            instanceValue[12] = Double.NaN;
            // Changing negative values to NaN values
            WekaDataset.cleanNegativeValues(instanceValue);
            dataset.add(new DenseInstance(1.0, instanceValue));
        }
        return dataset;
    }
}
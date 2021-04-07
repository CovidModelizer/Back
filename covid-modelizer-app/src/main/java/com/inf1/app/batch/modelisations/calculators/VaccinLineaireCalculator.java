package com.inf1.app.batch.modelisations.calculators;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class VaccinLineaireCalculator implements ModelisationCalculator {

	@Override
	public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
		ModelisationDTO model = new ModelisationDTO();

		// Variables utiles à l'entraînement du modèle de prédiction
		int expanse = 21;

		double calculationValue = Double.NaN;

		Instances dataSet = null;
		Instances trainSet = null;
		Instances testSet = null;

		LinearRegression lrClassifier = null;

		Evaluation eval = null;

		Instance predictiveData = null;

		try {
			dataSet = initDataSet(situationsReellesDTO);
			// Entraînement du modèle de régression linéaire à une variable
			trainSet = dataSet.trainCV(5, 0, new Random());
			testSet = dataSet.testCV(5, 0);

			lrClassifier = new LinearRegression();
			lrClassifier.setOptions(new String[] { "-R", "1" });
			lrClassifier.buildClassifier(trainSet);

			eval = new Evaluation(trainSet);
			eval.evaluateModel(lrClassifier, testSet);

			for (int i = 1; i < dataSet.numAttributes(); i++) {
				if (lrClassifier.coefficients()[i] != 0.0) {
					model.getCoeff().put("PredJ+1_" + dataSet.attribute(i).name(), lrClassifier.coefficients()[i]);
				}
			}
			model.getCoeff().put("PredJ+1_constante",
					lrClassifier.coefficients()[lrClassifier.coefficients().length - 1]);

			// Prédictions
			for (int i = 0; i < (2 * expanse); i++) {
				predictiveData = dataSet.instance(dataSet.size() - (2 * expanse) - 1 + i);
				model.getValues()
						.put(LocalDate.parse(predictiveData.stringValue(0), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
								.plusDays(1),
								lrClassifier.classifyInstance(predictiveData) < 0 ? "0"
										: String.valueOf((int) lrClassifier.classifyInstance(predictiveData)));
			}
			predictiveData = dataSet.lastInstance();
			calculationValue = predictiveData.value(1);
			for (int i = 0; i < expanse; i++) {
				calculationValue = lrClassifier.coefficients()[1] * calculationValue + lrClassifier.coefficients()[2];
				model.getValues()
						.put(LocalDate.parse(predictiveData.stringValue(0), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
								.plusDays(i + 1), calculationValue < 0 ? "0" : String.valueOf((int) calculationValue));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	private Instances initDataSet(List<SituationReelleDTO> srDTO) throws ParseException {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
		atts.add(new Attribute("date", "yyyy-MM-dd"));
		atts.add(new Attribute("cumul_premieresInjections"));
		atts.add(new Attribute("cumul vaccines J+1"));

		Instances dataSet = new Instances("VaccinLineaireCalculator dataSet", atts, 0);
		dataSet.setClassIndex(atts.size() - 1);

		double[] firstInstanceValue = new double[dataSet.numAttributes()];
		firstInstanceValue[0] = dataSet.attribute("date").parseDate("2020-12-26");
		firstInstanceValue[1] = Double.NaN;
		firstInstanceValue[2] = Double.NaN;
		dataSet.add(new DenseInstance(1.0, firstInstanceValue));

		for (int i = 300; i < srDTO.size(); i++) {
			double[] instanceValue = new double[dataSet.numAttributes()];
			instanceValue[0] = dataSet.attribute("date")
					.parseDate(srDTO.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			instanceValue[1] = srDTO.get(i).getCumulPremieresInjections() == null ? Double.NaN
					: Double.parseDouble(srDTO.get(i).getCumulPremieresInjections());
			instanceValue[2] = (i + 1 >= srDTO.size()) ? Double.NaN
					: srDTO.get(i + 1).getCumulPremieresInjections() == null ? Double.NaN
							: Double.parseDouble(srDTO.get(i + 1).getCumulPremieresInjections());
			for (int j = 1; j < instanceValue.length; j++) {
				instanceValue[j] = Double.isNaN(instanceValue[j]) ? instanceValue[j]
						: instanceValue[j] < 0.0 ? Double.NaN : instanceValue[j];
			}
			dataSet.add(new DenseInstance(1.0, instanceValue));
		}
		return dataSet;
	}

}

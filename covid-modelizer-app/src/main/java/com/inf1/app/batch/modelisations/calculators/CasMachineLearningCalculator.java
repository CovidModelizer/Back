package com.inf1.app.batch.modelisations.calculators;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class CasMachineLearningCalculator implements ModelisationCalculator {

	private static final Logger LOG = LoggerFactory.getLogger(CasMachineLearningCalculator.class);

	@Override
	public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
		ModelisationDTO model = new ModelisationDTO();

		// Variables utiles à l'entraînement du modèle de prédiction
		double realValue = Double.NaN;
		int expanse = 21;

		Instances[] allDataSet = new Instances[expanse];

		Instances dataSet = null;
		Instances trainSet = null;
		Instances testSet = null;
		LinearRegression[] lrClassifier = new LinearRegression[expanse];
		Evaluation[] eval = new Evaluation[expanse];

		Instance predictionData = null;
		double[] predictedValue = new double[expanse];

		try {
			dataSet = initDataSet(situationsReellesDTO);

			// Entraînement du modèle de régression linéaire
			for (int n = 1; n <= expanse; n++) {
				for (int i = 0; i < dataSet.size(); i++) {
					realValue = (i + n) >= dataSet.size() ? Double.NaN : dataSet.instance(i + n).value(5);
					dataSet.instance(i).setValue(dataSet.numAttributes() - 1, realValue);
				}
				
				dataSet.renameAttribute(dataSet.numAttributes() - 1, "nouveaux cas J+" + n);

				trainSet = dataSet.trainCV(5, 0, new Random());
				testSet = dataSet.testCV(5, 0);

				lrClassifier[n - 1] = new LinearRegression();
				lrClassifier[n - 1].buildClassifier(trainSet);

				eval[n - 1] = new Evaluation(trainSet);
				eval[n - 1].evaluateModel(lrClassifier[n - 1], testSet);

				predictionData = dataSet.get(dataSet.size() - 1 - n);
				predictedValue[n - 1] = lrClassifier[n - 1].classifyInstance(predictionData);

				allDataSet[n - 1] = new Instances(dataSet);
				
				LOG.info(lrClassifier[n - 1].toString());

				for (int i = 0; i < dataSet.numAttributes(); i++) {
					if (lrClassifier[n - 1].coefficients()[i] != 0.0) {
						model.getCoeff().put("PredJ+" + n + "_" + dataSet.attribute(i).name(),
								lrClassifier[n - 1].coefficients()[i]);
					}
				}
				model.getCoeff().put("PredJ+" + n + "_constante",
						lrClassifier[n - 1].coefficients()[lrClassifier[n - 1].coefficients().length - 1]);
			}

			// Prédictions sur 21 (= expanse) jours
			for (int i = 0; i < allDataSet.length; i++) {

				predictionData = allDataSet[i].get(allDataSet[i].size() - 1 - expanse);

				predictedValue[i] = lrClassifier[i].classifyInstance(predictionData);

				model.getValues()
						.put(LocalDate.parse(predictionData.stringValue(0), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
								.plusDays(i + 1),
								String.valueOf((int) lrClassifier[i].classifyInstance(predictionData)));
			}
			
			for (int i = 0; i < expanse; i++) {
				predictionData = allDataSet[i].lastInstance();

				model.getValues()
						.put(LocalDate.parse(predictionData.stringValue(0), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
								.plusDays(i + 1),
								String.valueOf((int) lrClassifier[i].classifyInstance(predictionData)));
			}

			for (Map.Entry<LocalDate, String> entry : model.getValues().entrySet()) {
				LOG.info(entry.getKey() + " : " + entry.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	private Instances initDataSet(List<SituationReelleDTO> situationsReellesDTO) throws ParseException {
		ArrayList<Attribute> atts = new ArrayList<Attribute>();
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
		atts.add(new Attribute("nouveaux cas J+N"));

		Instances dataSet = new Instances("CasMachineLearningCalculator dataSet", atts, 0);
		dataSet.setClassIndex(atts.size() - 1);

		double[] firstInstanceValue = new double[dataSet.numAttributes()];
		firstInstanceValue[0] = dataSet.attribute("date").parseDate("2020-03-01");
		for (int j = 1; j < firstInstanceValue.length; j++) {
			firstInstanceValue[j] = Double.NaN;
		}
		dataSet.add(new DenseInstance(1.0, firstInstanceValue));

		for (int i = 0; i < situationsReellesDTO.size(); i++) {
			double[] instanceValue = new double[dataSet.numAttributes()];

			instanceValue[0] = dataSet.attribute("date")
					.parseDate(situationsReellesDTO.get(i).getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			instanceValue[1] = (i == 0)
					? situationsReellesDTO.get(i).getDeces() == null ? Double.NaN
							: Double.parseDouble(situationsReellesDTO.get(i).getDeces())
					: situationsReellesDTO.get(i).getDeces() == null
							|| situationsReellesDTO.get(i - 1).getDeces() == null ? Double.NaN
									: Double.parseDouble(situationsReellesDTO.get(i).getDeces())
											- Double.parseDouble(situationsReellesDTO.get(i - 1).getDeces());
			instanceValue[2] = (i == 0)
					? situationsReellesDTO.get(i).getGueris() == null ? Double.NaN
							: Double.parseDouble(situationsReellesDTO.get(i).getGueris())
					: situationsReellesDTO.get(i).getGueris() == null
							|| situationsReellesDTO.get(i - 1).getGueris() == null ? Double.NaN
									: Double.parseDouble(situationsReellesDTO.get(i).getGueris())
											- Double.parseDouble(situationsReellesDTO.get(i - 1).getGueris());
			instanceValue[3] = (i == 0)
					? situationsReellesDTO.get(i).getDecesEhpad() == null ? Double.NaN
							: Double.parseDouble(situationsReellesDTO.get(i).getDecesEhpad())
					: situationsReellesDTO.get(i).getDecesEhpad() == null
							|| situationsReellesDTO.get(i - 1).getDecesEhpad() == null ? Double.NaN
									: Double.parseDouble(situationsReellesDTO.get(i).getDecesEhpad())
											- Double.parseDouble(situationsReellesDTO.get(i - 1).getDecesEhpad());
			instanceValue[4] = situationsReellesDTO.get(i).getReanimation() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getReanimation());
			instanceValue[5] = (i == 0)
					? situationsReellesDTO.get(i).getCasConfirmes() == null ? Double.NaN
							: Double.parseDouble(situationsReellesDTO.get(i).getCasConfirmes())
					: situationsReellesDTO.get(i).getCasConfirmes() == null
							|| situationsReellesDTO.get(i - 1).getCasConfirmes() == null ? Double.NaN
									: Double.parseDouble(situationsReellesDTO.get(i).getCasConfirmes())
											- Double.parseDouble(situationsReellesDTO.get(i - 1).getCasConfirmes());
			instanceValue[6] = situationsReellesDTO.get(i).getHospitalises() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getHospitalises());
			instanceValue[7] = situationsReellesDTO.get(i).getTestsRealises() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getTestsRealises());
			instanceValue[8] = situationsReellesDTO.get(i).getTestsPositifs() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getTestsPositifs());
			instanceValue[9] = (i == 0)
					? situationsReellesDTO.get(i).getCasConfirmesEhpad() == null ? Double.NaN
							: Double.parseDouble(situationsReellesDTO.get(i).getCasConfirmesEhpad())
					: situationsReellesDTO.get(i).getCasConfirmesEhpad() == null
							|| situationsReellesDTO.get(i - 1).getCasConfirmesEhpad() == null ? Double.NaN
									: Double.parseDouble(situationsReellesDTO.get(i).getCasConfirmesEhpad()) - Double
											.parseDouble(situationsReellesDTO.get(i - 1).getCasConfirmesEhpad());
			instanceValue[10] = situationsReellesDTO.get(i).getNouvellesReanimations() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getNouvellesReanimations());
			instanceValue[11] = situationsReellesDTO.get(i).getNouvellesHospitalisations() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getNouvellesHospitalisations());
			instanceValue[12] = situationsReellesDTO.get(i).getNouvellesPremieresInjections() == null ? Double.NaN
					: Double.parseDouble(situationsReellesDTO.get(i).getNouvellesPremieresInjections());
			instanceValue[13] = Double.NaN;

			dataSet.add(new DenseInstance(1.0, instanceValue));
		}

		return dataSet;
	}
}

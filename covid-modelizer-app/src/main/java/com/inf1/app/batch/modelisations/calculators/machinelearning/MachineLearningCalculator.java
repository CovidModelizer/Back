package com.inf1.app.batch.modelisations.calculators.machinelearning;

import com.inf1.app.batch.modelisations.calculators.utils.ModelisationCalculator;
import com.inf1.app.batch.modelisations.calculators.utils.WekaDataset;
import com.inf1.app.dto.ModelisationDTO;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public abstract class MachineLearningCalculator implements ModelisationCalculator, WekaDataset {

    protected static void univariateLinearRegression(final Instances dataset,
                                                     ModelisationDTO modelisationDTO) throws Exception {
        // Initializing
        final int
                expanse = 21,
                expanseX2 = expanse * 2,
                datasetSize = dataset.size(),
                lastIndex = datasetSize - 1,
                numAttributes = dataset.numAttributes(),
                lastAttribute = numAttributes - 1,
                expanseX2Index = lastIndex - expanseX2;
        double
                realValue,
                predictiveValue;
        Instances
                trainSet,
                testSet;
        LinearRegression
                lrClassifier;
        Evaluation
                evaluation;
        Instance
                predictiveData;
        LocalDate
                nextDate;

        // Training the univariate linear regression model
        for (int i = 0; i < datasetSize; i++) {
            realValue = (i + 1) >= datasetSize ? Double.NaN : dataset.instance(i + 1).value(1);
            dataset.instance(i).setValue(lastAttribute, realValue);
        }

        trainSet = dataset.trainCV(5, 0, new Random());
        testSet = dataset.testCV(5, 0);

        lrClassifier = new LinearRegression();
        lrClassifier.setOptions(new String[]{"-R", "1"});
        lrClassifier.buildClassifier(trainSet);

        evaluation = new Evaluation(trainSet);
        evaluation.evaluateModel(lrClassifier, testSet);

        for (int i = 1; i < numAttributes; i++) {
            if (lrClassifier.coefficients()[i] != 0.0) {
                modelisationDTO.getCoeff().put("PredJ+1_" + dataset.attribute(i).name(),
                        lrClassifier.coefficients()[i]);
            }
        }
        modelisationDTO.getCoeff().put("PredJ+1_constante",
                lrClassifier.coefficients()[lrClassifier.coefficients().length - 1]);

        // Predicting
        for (int i = 0; i < expanseX2; i++) {
            predictiveData = dataset.instance(expanseX2Index + i);
            nextDate = LocalDate.parse(predictiveData.stringValue(0),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1);
            predictiveValue = lrClassifier.classifyInstance(predictiveData);
            modelisationDTO.getValues().put(nextDate,
                    predictiveValue < 0 ? "0" : String.valueOf((int) predictiveValue));
        }
        predictiveData = dataset.lastInstance();
        for (int i = 0; i < expanse; i++) {
            nextDate = LocalDate.parse(predictiveData.stringValue(0),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1);
            predictiveValue = lrClassifier.classifyInstance(predictiveData);
            modelisationDTO.getValues().put(nextDate,
                    predictiveValue < 0 ? "0" : String.valueOf((int) predictiveValue));
            predictiveData.setValue(0, dataset.attribute("date").parseDate(nextDate.toString()));
            predictiveData.setValue(1, (int) predictiveValue);
        }
    }

    protected static void multivariateLinearRegression(final Instances dataset,
                                                       ModelisationDTO modelisationDTO) throws Exception {
        // Initializing
        final int
                expanse = 21,
                datasetSize = dataset.size(),
                lastIndex = datasetSize - 1,
                numAttributes = dataset.numAttributes(),
                lastAttribute = numAttributes - 1;
        final String
                lastAttributeName = dataset.attribute(lastAttribute).name();
        int
                currentIndex;
        double
                realValue,
                predictiveValue;
        Instances[]
                allDataSet = new Instances[expanse];
        Instances
                trainSet,
                testSet;
        LinearRegression[]
                lrClassifier = new LinearRegression[expanse];
        Evaluation[]
                evaluation = new Evaluation[expanse];
        Instance
                predictiveData;
        LocalDate
                nextDate;

        // Training the multivariate linear regression model
        for (int n = 1; n <= expanse; n++) {
            for (int i = 0; i < datasetSize; i++) {
                realValue = (i + n) >= datasetSize ? Double.NaN : dataset.instance(i + n).value(5);
                dataset.instance(i).setValue(lastAttribute, realValue);
            }

            dataset.renameAttribute(lastAttribute,
                    lastAttributeName.substring(0, lastAttributeName.length() - 1) + n);

            trainSet = dataset.trainCV(5, 0, new Random());
            testSet = dataset.testCV(5, 0);

            lrClassifier[n - 1] = new LinearRegression();
            lrClassifier[n - 1].setOptions(new String[]{"-R", "1"});
            lrClassifier[n - 1].buildClassifier(trainSet);

            evaluation[n - 1] = new Evaluation(trainSet);
            evaluation[n - 1].evaluateModel(lrClassifier[n - 1], testSet);

            allDataSet[n - 1] = new Instances(dataset);

            for (int i = 1; i < numAttributes; i++) {
                if (lrClassifier[n - 1].coefficients()[i] != 0.0) {
                    modelisationDTO.getCoeff().put("PredJ+" + n + "_" + dataset.attribute(i).name(),
                            lrClassifier[n - 1].coefficients()[i]);
                }
            }
            modelisationDTO.getCoeff().put("PredJ+" + n + "_constante",
                    lrClassifier[n - 1].coefficients()[lrClassifier[n - 1].coefficients().length - 1]);
        }

        // Predicting
        for (int x = 2; x >= 0; x--) {
            currentIndex = lastIndex - (x * expanse);
            for (int i = 0; i < expanse; i++) {
                predictiveData = allDataSet[i].instance(currentIndex);
                nextDate = LocalDate.parse(predictiveData.stringValue(0),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i + 1);
                predictiveValue = lrClassifier[i].classifyInstance(predictiveData);
                modelisationDTO.getValues().put(nextDate,
                        predictiveValue < 0 ? "0" : String.valueOf((int) predictiveValue));
            }
        }
    }
}
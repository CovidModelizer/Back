package com.inf1.app.batch.modelisations.calculators.biologic;

import com.inf1.app.batch.modelisations.calculators.utils.ModelisationCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

import java.time.LocalDate;
import java.util.List;

public abstract class BiologicalCalculator implements ModelisationCalculator {
    // Data details
    // N → Approximate population of France
    // d → Average duration of infection before recovery or death
    // Isolation time after COVID-19 positivity :
    // https://www.ameli.fr/assure/covid-19/isolement-principes-et-regles-respecter/isolement-principes-generaux
    // gamma → 1 / d → Recovery rate
    // initialS → Number of people susceptible to be infected
    // initialV → Number of vaccinated people
    // initialI → Number of infected people
    // initialR → Number of cured people
    // vaccinRate → Daily vaccination rate
    // r0 → Virus reproduction rate
    // beta → r0 * gamma → Transmission rate

    private final static double
            N = 67000000.0,
            d = 10.0,
            gamma = 1.0 / d;
    private static double
            beta;

    private static double[] SIRCalculation(final SituationReelleDTO data) {
        // Initializing
        double
                initialS = Double.parseDouble(data.getSirS()),
                initialI = Double.parseDouble(data.getSirI()),
                initialR = Double.parseDouble(data.getSirR()),
                r0 = Double.parseDouble(data.getR0());

        /* ******* Calculation ******** */
        // dS = - beta * S * I
        // dI = beta * S * I - gamma * I
        // dR = gamma * I

        beta = r0 * gamma;
        double S = initialS - (beta / N) * initialS * initialI;
        double I = initialI + (beta / N) * initialS * initialI - gamma * initialI;
        double R = initialR + gamma * initialI;

        return new double[]{S, I, R};
    }

    private static double[] SVIRCalculation(final SituationReelleDTO data) {
        // Initializing
        double
                initialS = Double.parseDouble(data.getSvirS()),
                initialV = Double.parseDouble(data.getSvirV()),
                initialI = Double.parseDouble(data.getSvirI()),
                initialR = Double.parseDouble(data.getSvirR()),
                initialVaccinRate = Double.parseDouble(data.getSvirNouveauTauxVaccination()),
                r0 = Double.parseDouble(data.getR0());

        /* ******* Calculation ******** */
        // dS = - beta * S * I - vaccinRate * S
        // dV = vaccinRate * S
        // dI = beta * S * I - gamma * I
        // dR = gamma * I

        beta = r0 * gamma;
        double S = initialS - (beta / N) * initialS * initialI - initialVaccinRate * initialS;
        double V = initialV + initialVaccinRate * initialS;
        double I = initialI + (beta / N) * initialS * initialI - gamma * initialI;
        double R = initialR + gamma * initialI;
        double vaccinRate = initialVaccinRate * initialS / S;

        return new double[]{S, V, I, R, vaccinRate};
    }

    private static void SIRPredictiveDataUpdate(final double[] sir,
                                                SituationReelleDTO data) {
        data.setSirS(String.valueOf(sir[0]));
        data.setSirI(String.valueOf(sir[1]));
        data.setSirR(String.valueOf(sir[2]));
    }

    private static void SVIRPredictiveDataUpdate(final double[] svir,
                                                 SituationReelleDTO data) {
        data.setSvirS(String.valueOf(svir[0]));
        data.setSvirV(String.valueOf(svir[1]));
        data.setSvirI(String.valueOf(svir[2]));
        data.setSvirR(String.valueOf(svir[3]));
        data.setSvirNouveauTauxVaccination(String.valueOf(svir[4]));
    }

    protected void biologicalCalculation(final List<SituationReelleDTO> situationsReellesDTO,
                                         final String model,
                                         ModelisationDTO modelisationDTO) {
        // Initializing
        final boolean
                isModelSIR = model.equals("SIR");
        final int
                expanse = 21,
                expanseX2 = expanse * 2,
                lastIndex = situationsReellesDTO.size() - 1,
                expanseX2Index = lastIndex - expanseX2;
        final CalculationInterface
                calculationInterface = isModelSIR ? BiologicalCalculator::SIRCalculation
                : BiologicalCalculator::SVIRCalculation;
        final PredictiveDataUpdateInterface
                predictiveDataUpdateInterface = isModelSIR ? BiologicalCalculator::SIRPredictiveDataUpdate
                : BiologicalCalculator::SVIRPredictiveDataUpdate;
        int
                currentIndex;
        double[]
                predictiveModel;
        SituationReelleDTO
                predictiveData;
        LocalDate
                nextDate;

        // Predicting
        for (int i = 0; i < expanseX2; i++) {
            currentIndex = expanseX2Index + i;
            predictiveData = situationsReellesDTO.get(currentIndex);
            updateR0(situationsReellesDTO, currentIndex, predictiveData);
            nextDate = predictiveData.getDate().plusDays(1);
            predictiveModel = calculationInterface.doCalculation(predictiveData);
            modelisationDTO.getValues().put(nextDate, String.valueOf((int) predictiveModel[1]));
        }
        predictiveData = situationsReellesDTO.get(lastIndex);
        updateR0(situationsReellesDTO, lastIndex, predictiveData);
        for (int i = 0; i < expanse; i++) {
            nextDate = predictiveData.getDate().plusDays(i + 1);
            predictiveModel = calculationInterface.doCalculation(predictiveData);
            modelisationDTO.getValues().put(nextDate, String.valueOf((int) predictiveModel[1]));
            // Updating predictiveData with calculated values from predictiveModel
            predictiveDataUpdateInterface.doPredictiveDataUpdate(predictiveModel, predictiveData);
        }
        modelisationDTO.getCoeff().put("beta", beta);
        modelisationDTO.getCoeff().put("gamma", gamma);
    }

    private void updateR0(final List<SituationReelleDTO> srs,
                          final int dataIndex,
                          SituationReelleDTO data) {
        // Updating data r0 with the most recent r0 recorded during the last 2 weeks before dataIndex
        for (int i = 0; i < 15; i++) {
            if (srs.get(dataIndex - i).getR0() != null) {
                data.setR0(srs.get(dataIndex - i).getR0());
                break;
            }
        }
    }

    private interface CalculationInterface {
        // abstract for SIRCalculation and SVIRCalculation
        double[] doCalculation(final SituationReelleDTO data);
    }

    private interface PredictiveDataUpdateInterface {
        // abstract for SIRPredictiveDataUpdate and SVIRPredictiveDataUpdate
        void doPredictiveDataUpdate(final double[] model, SituationReelleDTO data);
    }
}
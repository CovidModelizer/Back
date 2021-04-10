package com.inf1.app.batch.modelisations.calculators;

import java.util.List;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

public class InfectionSIRCalculator implements ModelisationCalculator {

	@Override
	public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
		ModelisationDTO model = new ModelisationDTO();

		// Initialisation
		final double N = 67000000.0;
		double initialS = Double.NaN;
		double initialI = Double.NaN;
		double initialR = Double.NaN;
		double r0 = Double.NaN;
		double d = 10.0;
		double gamma = 1.0 / d;
		double beta = Double.NaN;

		int expanse = 21;

		// Calcul du SIR
		double[] predictiveSIR = null;

		for (int i = 0; i < (2 * expanse); i++) {
			initialS = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSirS());
			initialI = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSirI());
			initialR = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSirR());
			for (int j = 0; j < 15; j++) {
				if (situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i - j).getR0() != null) {
					r0 = Double.parseDouble(
							situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i - j).getR0());
					break;
				}
			}
			beta = r0 * gamma;
			predictiveSIR = SIRCalculation(N, initialS, initialI, initialR, gamma, beta);
			model.getValues().put(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getDate().plusDays(1),
					String.valueOf((int) predictiveSIR[1]));
		}

		predictiveSIR[0] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSirS());
		predictiveSIR[1] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSirI());
		predictiveSIR[2] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSirR());
		for (int j = 0; j < 15; j++) {
			if (situationsReellesDTO.get(situationsReellesDTO.size() - 1 - j).getR0() != null) {
				r0 = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1 - j).getR0());
				break;
			}
		}
		beta = r0 * gamma;
		model.getCoeff().put("beta", beta);
		model.getCoeff().put("gamma", gamma);

		for (int i = 0; i < expanse; i++) {
			predictiveSIR = SIRCalculation(N, predictiveSIR[0], predictiveSIR[1], predictiveSIR[2], gamma, beta);
			model.getValues().put(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getDate().plusDays(i + 1),
					String.valueOf((int) predictiveSIR[1]));
		}

		return model;
	}

	private double[] SIRCalculation(double N, double S0, double I0, double R0, double gamma, double beta) {
		/********* Parameters **********/
		// dS = - beta * S * I
		// dI = beta * S * I - gamma * I
		// dR = gamma * I

		double S = S0 - (beta / N) * S0 * I0;
		double I = I0 + (beta / N) * S0 * I0 - gamma * I0;
		double R = R0 + gamma * I0;

		return new double[] { S, I, R };
	}

}

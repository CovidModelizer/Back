package com.inf1.app.batch.modelisations.calculators;

import java.util.List;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

public class VaccinationSVIRCalculator implements ModelisationCalculator {

	@Override
	public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
		ModelisationDTO model = new ModelisationDTO();

		// Initialisation
		final double N = 67000000.0;
		double initialS = Double.NaN;
		double initialV = Double.NaN;
		double initialI = Double.NaN;
		double initialR = Double.NaN;
		double r0 = Double.NaN;
		double txV = Double.NaN;
		double d = 10.0;
		double gamma = 1.0 / d;
		double beta = Double.NaN;

		int expanse = 21;

		// Calcul du SIR
		double[] predictiveSVIR = null;

		for (int i = 0; i < (2 * expanse); i++) {
			initialS = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSvirS());
			initialV = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSvirV());
			initialI = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSvirI());
			initialR = Double.parseDouble(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getSvirR());
			for (int j = 0; j < 15; j++) {
				if (situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i - j).getR0() != null) {
					r0 = Double.parseDouble(
							situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i - j).getR0());
					break;
				}
			}
			txV = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i)
					.getSvirNouveauTauxVaccination());
			beta = r0 * gamma;
			predictiveSVIR = SVIRCalculation(N, initialS, initialV, initialI, initialR, gamma, beta, txV);
			model.getValues().put(
					situationsReellesDTO.get(situationsReellesDTO.size() - (2 * expanse) - 1 + i).getDate().plusDays(1),
					String.valueOf((int) predictiveSVIR[1]));
		}

		predictiveSVIR[0] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSvirS());
		predictiveSVIR[1] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSvirV());
		predictiveSVIR[2] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSvirI());
		predictiveSVIR[3] = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSvirR());
		for (int j = 0; j < 15; j++) {
			if (situationsReellesDTO.get(situationsReellesDTO.size() - 1 - j).getR0() != null) {
				r0 = Double.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1 - j).getR0());
				break;
			}
		}
		predictiveSVIR[4] = Double
				.parseDouble(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getSvirNouveauTauxVaccination());
		beta = r0 * gamma;
		model.getCoeff().put("beta", beta);
		model.getCoeff().put("gamma", gamma);
		model.getCoeff().put("taux_vaccination", predictiveSVIR[4]);

		for (int i = 0; i < expanse; i++) {
			predictiveSVIR = SVIRCalculation(N, predictiveSVIR[0], predictiveSVIR[1], predictiveSVIR[2],
					predictiveSVIR[3], gamma, beta, predictiveSVIR[4]);
			model.getValues().put(situationsReellesDTO.get(situationsReellesDTO.size() - 1).getDate().plusDays(i + 1),
					String.valueOf((int) predictiveSVIR[1]));
		}

		return model;
	}

	private double[] SVIRCalculation(double N, double S0, double V0, double I0, double R0, double gamma, double beta,
			double txV0) {
		/********* Parameters **********/
		// dS = - beta * S * I - txV * S
		// dV = txV * S
		// dI = beta * S * I - gamma * I
		// dR = gamma * I

		double S = S0 - (beta / N) * S0 * I0 - txV0 * S0;
		double V = V0 + txV0 * S0;
		double I = I0 + (beta / N) * S0 * I0 - gamma * I0;
		double R = R0 + gamma * I0;
		double txV = txV0 * S0 / S;

		return new double[] { S, V, I, R, txV };
	}
}

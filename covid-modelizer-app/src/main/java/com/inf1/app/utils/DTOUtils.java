package com.inf1.app.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.inf1.app.dto.CollectSituationReelleDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.entities.SituationReelle;

public class DTOUtils {

	private static final ModelMapper MODEL_MAPPER = new ModelMapper();

	public static List<SituationReelleDTO> listSituationReelleDTOMapper(List<SituationReelle> srs) {
		List<SituationReelleDTO> sDTO = new ArrayList<SituationReelleDTO>();
		for (SituationReelle s : srs) {
			sDTO.add(MODEL_MAPPER.map(s, SituationReelleDTO.class));
		}
		return sDTO;
	}

	public static SituationReelleDTO[] collectToArrayOfSituationReelleDTOMapper(CollectSituationReelleDTO[] csr) {
		SituationReelleDTO[] srDTO = new SituationReelleDTO[csr.length];

		int N = 67000000;
		int daysToRecover = 10;

		for (int i = 0; i < csr.length; i++) {
			srDTO[i] = MODEL_MAPPER.map(csr[i], SituationReelleDTO.class);

			if (i == 0) {
				srDTO[i].setNouveauxCasConfirmes(csr[i].getCumulCasConfirmes());
				srDTO[i].setNouveauxDeces(csr[i].getCumulDeces());
			} else {
				srDTO[i].setNouveauxCasConfirmes(
						csr[i].getCumulCasConfirmes() == null || csr[i - 1].getCumulCasConfirmes() == null ? null
								: Integer.parseInt(csr[i].getCumulCasConfirmes())
										- Integer.parseInt(csr[i - 1].getCumulCasConfirmes()) < 0 ? null
												: String.valueOf(Integer.parseInt(csr[i].getCumulCasConfirmes())
														- Integer.parseInt(csr[i - 1].getCumulCasConfirmes())));
				srDTO[i].setNouveauxCasConfirmesEhpad(
						csr[i].getCumulCasConfirmesEhpad() == null || csr[i - 1].getCumulCasConfirmesEhpad() == null
								? null
								: Integer.parseInt(csr[i].getCumulCasConfirmesEhpad())
										- Integer.parseInt(csr[i - 1].getCumulCasConfirmesEhpad()) < 0 ? null
												: String.valueOf(Integer.parseInt(csr[i].getCumulCasConfirmesEhpad())
														- Integer.parseInt(csr[i - 1].getCumulCasConfirmesEhpad())));
				srDTO[i].setNouveauxGueris(csr[i].getCumulGueris() == null || csr[i - 1].getCumulGueris() == null ? null
						: Integer.parseInt(csr[i].getCumulGueris()) - Integer.parseInt(csr[i - 1].getCumulGueris()) < 0
								? null
								: String.valueOf(Integer.parseInt(csr[i].getCumulGueris())
										- Integer.parseInt(csr[i - 1].getCumulGueris())));
				srDTO[i].setNouveauxDeces(csr[i].getCumulDeces() == null || csr[i - 1].getCumulDeces() == null ? null
						: Integer.parseInt(csr[i].getCumulDeces()) - Integer.parseInt(csr[i - 1].getCumulDeces()) < 0
								? null
								: String.valueOf(Integer.parseInt(csr[i].getCumulDeces())
										- Integer.parseInt(csr[i - 1].getCumulDeces())));
				srDTO[i].setNouveauxDecesEhpad(
						csr[i].getCumulDecesEhpad() == null || csr[i - 1].getCumulDecesEhpad() == null ? null
								: Integer.parseInt(csr[i].getCumulDecesEhpad())
										- Integer.parseInt(csr[i - 1].getCumulDecesEhpad()) < 0 ? null
												: String.valueOf(Integer.parseInt(csr[i].getCumulDecesEhpad())
														- Integer.parseInt(csr[i - 1].getCumulDecesEhpad())));
				if (i > 7) {
					srDTO[i].setNouvellesLivraisonsNombreTotalDoses(csr[i].getCumulLivraisonsNombreTotalDoses() == null
							|| csr[i - 7].getCumulLivraisonsNombreTotalDoses() == null
									? null
									: Integer.parseInt(csr[i].getCumulLivraisonsNombreTotalDoses())
											- Integer.parseInt(csr[i - 7].getCumulLivraisonsNombreTotalDoses()) < 0
													? null
													: String.valueOf(Integer
															.parseInt(csr[i].getCumulLivraisonsNombreTotalDoses())
															- Integer.parseInt(
																	csr[i - 7].getCumulLivraisonsNombreTotalDoses())));
					srDTO[i].setNouvellesLivraisonsNombreDosesPfizer(csr[i]
							.getCumulLivraisonsNombreDosesPfizer() == null
							|| csr[i - 7].getCumulLivraisonsNombreDosesPfizer() == null
									? null
									: Integer.parseInt(csr[i].getCumulLivraisonsNombreDosesPfizer())
											- Integer.parseInt(csr[i - 7].getCumulLivraisonsNombreDosesPfizer()) < 0
													? null
													: String.valueOf(Integer
															.parseInt(csr[i].getCumulLivraisonsNombreDosesPfizer())
															- Integer.parseInt(
																	csr[i - 7].getCumulLivraisonsNombreDosesPfizer())));
					srDTO[i].setNouvellesLivraisonsNombreDosesModerna(
							csr[i].getCumulLivraisonsNombreDosesModerna() == null
									|| csr[i - 7].getCumulLivraisonsNombreDosesModerna() == null
											? null
											: Integer.parseInt(csr[i].getCumulLivraisonsNombreDosesModerna()) - Integer
													.parseInt(csr[i - 7].getCumulLivraisonsNombreDosesModerna()) < 0
															? null
															: String.valueOf(Integer.parseInt(
																	csr[i].getCumulLivraisonsNombreDosesModerna())
																	- Integer.parseInt(csr[i - 7]
																			.getCumulLivraisonsNombreDosesModerna())));
				}
				if (i > 90) {
					srDTO[i].setSirR(csr[i - daysToRecover].getCumulCasConfirmes());
					srDTO[i].setSirI(String.valueOf(
							Integer.parseInt(csr[i].getCumulCasConfirmes()) - Integer.parseInt(srDTO[i].getSirR())));
					srDTO[i].setSirS(String
							.valueOf(N - Integer.parseInt(srDTO[i].getSirI()) - Integer.parseInt(srDTO[i].getSirR())));
				}
				if (i > 299) {
					srDTO[i].setSvirR(csr[i - daysToRecover].getCumulCasConfirmes());
					srDTO[i].setSvirI(String.valueOf(
							Integer.parseInt(csr[i].getCumulCasConfirmes()) - Integer.parseInt(srDTO[i].getSvirR())));
					srDTO[i].setSvirV(csr[i].getCumulPremieresInjections());
					srDTO[i].setSvirS(String.valueOf(N - Integer.parseInt(srDTO[i].getSvirV())
							- Integer.parseInt(srDTO[i].getSvirI()) - Integer.parseInt(srDTO[i].getSvirR())));
					srDTO[i].setSvirNouveauTauxVaccination(
							String.valueOf(Double.parseDouble(csr[i].getNouvellesPremieresInjections())
									/ Double.parseDouble(srDTO[i].getSvirS())));
				}
			}
		}
		return srDTO;
	}
}
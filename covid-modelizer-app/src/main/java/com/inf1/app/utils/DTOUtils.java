package com.inf1.app.utils;

import java.util.ArrayList;
import java.util.List;

import com.inf1.app.dto.EvenementDTO;
import com.inf1.app.dto.IndicateurDTO;
import com.inf1.app.dto.SituationReelleDTO;
import com.inf1.app.jpa.entities.Evenement;
import com.inf1.app.jpa.entities.Indicateur;
import com.inf1.app.jpa.entities.SituationReelle;

public class DTOUtils {

	public static SituationReelleDTO situationReelleDTOMapper(SituationReelle s) {
		SituationReelleDTO sDTO = new SituationReelleDTO();

		sDTO.setCasConfirmes(s.getCasConfirmes());
		sDTO.setCumulPremieresInjections(s.getCumulPremieresInjections());
		sDTO.setDate(s.getDate());
		sDTO.setDeces(s.getDeces());
		sDTO.setDecesEhpad(s.getDecesEhpad());
		sDTO.setGueris(s.getGueris());
		sDTO.setHospitalises(s.getHospitalises());
		sDTO.setLivraisonsCumulNombreDosesModerna(s.getLivraisonsCumulNombreDosesModerna());
		sDTO.setLivraisonsCumulNombreDosesPfizer(s.getLivraisonsCumulNombreDosesPfizer());
		sDTO.setLivraisonsCumulNombreTotalDoses(s.getLivraisonsCumulNombreTotalDoses());
		sDTO.setNouvellesHospitalisations(s.getNouvellesHospitalisations());
		sDTO.setNouvellesPremieresInjections(s.getNouvellesHospitalisations());
		sDTO.setNouvellesReanimations(s.getNouvellesReanimations());
		sDTO.setPrisesRendezVousSemaineRang1(s.getPrisesRendezVousSemaineRang1());
		sDTO.setPrisesRendezVousSemaineRang2(s.getPrisesRendezVousSemaineRang2());
		sDTO.setReanimation(s.getReanimation());
		sDTO.setStockEhpadNombreDosesPfizer(s.getStockEhpadNombreDosesPfizer());
		sDTO.setStockNombreDosesModerna(s.getStockNombreDosesModerna());
		sDTO.setStockNombreDosesPfizer(s.getStockNombreDosesPfizer());
		sDTO.setStockNombreTotalDoses(s.getStockNombreTotalDoses());
		sDTO.setTotalPrisesRendezVousSemaine(s.getTotalPrisesRendezVousSemaine());

		return sDTO;
	}

	public static List<SituationReelleDTO> situationsReellesDTOsMapper(List<SituationReelle> srs) {
		List<SituationReelleDTO> sDTO = new ArrayList<SituationReelleDTO>();
		for (SituationReelle s : srs) {
			sDTO.add(situationReelleDTOMapper(s));
		}
		return sDTO;
	}

	public static IndicateurDTO indicateurDTOMapper(Indicateur i) {
		IndicateurDTO iDTO = new IndicateurDTO();

		iDTO.setDate(i.getDate());
		iDTO.setTypeIndicateur(i.getTypeIndicateur());
		iDTO.setTypeModel(i.getTypeModele());
		iDTO.setValue(i.getValeur());
		return null;
	}

	public static List<IndicateurDTO> indicateursDTOsMapper(List<Indicateur> ind) {
		List<IndicateurDTO> iDTO = new ArrayList<IndicateurDTO>();
		for (Indicateur i : ind) {
			iDTO.add(indicateurDTOMapper(i));
		}
		return iDTO;
	}

	public static IndicateurDTO indicateurReelVaccinDTOMapper(SituationReelle i) {
		IndicateurDTO iDTO = new IndicateurDTO();

		iDTO.setDate(i.getDate());
		iDTO.setTypeIndicateur("VAC");
		iDTO.setTypeModel("REL");
		iDTO.setValue(i.getCumulPremieresInjections());
		return iDTO;
	}

	public static List<IndicateurDTO> indicateursReelsVaccinsDTOsMapper(List<SituationReelle> srs) {
		List<IndicateurDTO> iDTO = new ArrayList<IndicateurDTO>();
		for (SituationReelle s : srs) {
			iDTO.add(indicateurReelVaccinDTOMapper(s));
		}
		return iDTO;
	}

	public static IndicateurDTO indicateurReelCasDTOMapper(SituationReelle i) {
		IndicateurDTO iDTO = new IndicateurDTO();
		iDTO.setDate(i.getDate());
		iDTO.setTypeIndicateur("CAS");
		iDTO.setTypeModel("REL");
		iDTO.setValue(i.getCasConfirmes());
		return iDTO;
	}

	public static List<IndicateurDTO> indicateursReelsCasDTOsMapper(List<SituationReelle> srs) {
		List<IndicateurDTO> iDTO = new ArrayList<IndicateurDTO>();
		for (SituationReelle s : srs) {
			iDTO.add(indicateurReelCasDTOMapper(s));
		}
		return iDTO;
	}
	
	public static EvenementDTO evenementDTOMapper(Evenement e) {
		EvenementDTO eDTO = new EvenementDTO();
		eDTO.setDate(e.getDate());
		eDTO.setTypeIndicateur(e.getTypeIndicateur());
		eDTO.setTypeModele(e.getTypeModele());
		return eDTO;
	}
	
	public static List<EvenementDTO> evenementsDTOsMapper(List<Evenement> es) {
		List<EvenementDTO> eDTO = new ArrayList<EvenementDTO>();
		for (Evenement e : es) {
			eDTO.add(evenementDTOMapper(e));
		}
		return eDTO;
	}
	
	
}
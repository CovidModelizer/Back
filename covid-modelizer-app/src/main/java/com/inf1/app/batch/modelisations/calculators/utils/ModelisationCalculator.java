package com.inf1.app.batch.modelisations.calculators.utils;

import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

import java.util.List;

public interface ModelisationCalculator {

    ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO);
}
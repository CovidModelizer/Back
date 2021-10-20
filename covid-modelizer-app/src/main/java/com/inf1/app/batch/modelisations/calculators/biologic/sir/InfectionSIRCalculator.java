package com.inf1.app.batch.modelisations.calculators.biologic.sir;

import com.inf1.app.batch.modelisations.calculators.biologic.BiologicalCalculator;
import com.inf1.app.dto.ModelisationDTO;
import com.inf1.app.dto.SituationReelleDTO;

import java.util.List;

public class InfectionSIRCalculator extends BiologicalCalculator {

    @Override
    public ModelisationDTO calculate(List<SituationReelleDTO> situationsReellesDTO) {
        ModelisationDTO modelisationDTO = new ModelisationDTO();

        // Predicting
        biologicalCalculation(situationsReellesDTO, "SIR", modelisationDTO);

        return modelisationDTO;
    }
}
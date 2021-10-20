package com.inf1.app.batch.modelisations.calculators.utils;

import com.inf1.app.dto.SituationReelleDTO;
import weka.core.Instances;

import java.text.ParseException;
import java.util.List;

public interface WekaDataset {

    static void cleanNegativeValues(double[] instanceValue) {
        for (int i = 1; i < instanceValue.length; i++) {
            instanceValue[i] = Double.isNaN(instanceValue[i]) ? instanceValue[i]
                    : instanceValue[i] < 0.0 ? Double.NaN : instanceValue[i];
        }
    }

    Instances initDataset(List<SituationReelleDTO> situationsReellesDTO) throws ParseException;
}
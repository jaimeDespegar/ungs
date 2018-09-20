package ungs.transformers;

import ungs.model.InformationDto;

public interface TransformerInformation<ORIGIN> {

    InformationDto transform(ORIGIN origin);

}
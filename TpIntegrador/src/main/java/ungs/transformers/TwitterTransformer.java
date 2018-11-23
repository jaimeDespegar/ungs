package ungs.transformers;

import ungs.dto.TwitterObjectDto;
import ungs.model.InformationDto;
import ungs.model.Origin;
import ungs.themes.Theme;

public class TwitterTransformer implements TransformerInformation<TwitterObjectDto> {

    @Override
    public InformationDto transform(TwitterObjectDto twitterObjectDto) {
        String description = twitterObjectDto.getDescription();
        return new InformationDto("", "", "", description, twitterObjectDto.getDate().toString());
    }

}
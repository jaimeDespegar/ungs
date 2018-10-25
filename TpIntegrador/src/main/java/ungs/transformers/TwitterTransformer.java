package ungs.transformers;

import ungs.dto.TwitterObjectDto;
import ungs.model.InformationDto;
import ungs.model.Origin;
import ungs.themes.Theme;

public class TwitterTransformer implements TransformerInformation<TwitterObjectDto> {

    @Override
    public InformationDto transform(TwitterObjectDto twitterObjectDto) {
        Theme theme = null;
        String description = twitterObjectDto.getDescription();
        return new InformationDto("", Origin.TWITTER, theme, description, twitterObjectDto.getDate().toString());
    }

}
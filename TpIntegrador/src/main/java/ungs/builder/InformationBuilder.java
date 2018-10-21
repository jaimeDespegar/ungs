package ungs.builder;

import ungs.services.InformationService;

public class InformationBuilder {

    private InformationService instance;

    public InformationBuilder() {
        this.instance = new InformationService();
    }

    public InformationBuilder withService(String service) {
        return this;
    }

    public InformationBuilder withFilter(String filter) {
        return this;
    }

    public InformationBuilder withTheme(String theme) {
        return this;
    }

    public InformationService build() {
        return this.instance;
    }

}
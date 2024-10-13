package net.axel.wafasalaf.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidatorProducer {

    @ApplicationScoped
    @Produces
    public Validator createValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}

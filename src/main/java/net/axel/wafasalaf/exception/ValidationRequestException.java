package net.axel.wafasalaf.exception;

import java.util.Map;

public class ValidationRequestException extends RuntimeException {

    private final Map<String, String> errors;

    public ValidationRequestException(Map<String, String> errors) {
        super("error in requset validation , data is not correct");
        this.errors = errors;
        errors.forEach((k, v) -> System.out.println(k + " ->  " + v));
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

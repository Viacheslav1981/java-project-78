package hexlet.code;

import hexlet.code.shemas.NumberSchema;
import hexlet.code.shemas.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }
}

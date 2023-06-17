package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {

    public static void main(String[] args) {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.required();

    }

    public Validator() {
        BaseSchema.CHECKS.clear();
    }

    public StringSchema string() {

        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}

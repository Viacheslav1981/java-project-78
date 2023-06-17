package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        super.setSchema();
    }

    public NumberSchema required() {
        addChecks("requiredNumber", it -> it instanceof Integer);

        return this;
    }

    public NumberSchema positive() {
        addChecks("positive", it -> it instanceof Integer i && i > 0 || it == null);

        return this;
    }

    public NumberSchema range(int range1, int range2) {
        addChecks("range", it -> it instanceof Integer i && i >= range1
                && i <= range2 || it == null);


        return this;
    }

}

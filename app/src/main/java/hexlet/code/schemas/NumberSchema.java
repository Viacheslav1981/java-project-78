package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addChecks(it -> it instanceof Integer);

        return this;
    }

    public NumberSchema positive() {
        addChecks(it -> it instanceof Integer i && i > 0 || it == null);

        return this;
    }

    public NumberSchema range(int range1, int range2) {
        addChecks(it -> it instanceof Integer i && i >= range1
                && i <= range2 || it == null);

        return this;
    }

}

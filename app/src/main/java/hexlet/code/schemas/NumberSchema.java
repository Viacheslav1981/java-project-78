package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    private int limit1;
    private int limit2;

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
        this.limit1 = range1;
        this.limit2 = range2;

        addChecks("range", it -> it instanceof Integer i && i >= limit1
                && i <= limit2 || it == null);

        return this;
    }

}

package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    private int minLength = 0;

    public StringSchema() {
        super.setSchema();
    }

    public StringSchema required() {
        addChecks("requiredString", it -> it instanceof String && it != "");

        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        addChecks("minLength", it -> it instanceof String s && s.length() >= minLength);

        return this;
    }

    public StringSchema contains(String str) {
        Check check;
        check = it -> it instanceof String s && s.contains(str);

        addChecks("contains", check);

        return this;
    }
}

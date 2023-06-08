package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        super.setSchema();
    }

    public StringSchema required() {
        addChecks("requiredString", it -> it instanceof String && it != "");

        return this;
    }

    public StringSchema minLength(int length) {
        addChecks("minLength", it -> it instanceof String s && s.length() >= length);

        return this;
    }

    public StringSchema contains(String str) {
        addChecks("contains", it -> it instanceof String s && s.contains(str));

        return this;
    }
}

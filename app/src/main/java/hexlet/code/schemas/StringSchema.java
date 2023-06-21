package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addChecks(it -> it instanceof String && it != "");
        return this;
    }

    public StringSchema minLength(int length) {
        addChecks(it -> it instanceof String s && s.length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        addChecks(it -> ((String) it).contains(str));
        return this;
    }
}

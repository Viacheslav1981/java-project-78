package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        super.setSchema();
    }

    public StringSchema required() {
         addChecks("requiredString", it -> it instanceof String && it != "");
      //  addChecks(it -> it instanceof String && it != "", this);

        return this;
    }

    public StringSchema minLength(int length) {
         addChecks("minLength", it -> it instanceof String s && s.length() >= length);
        //   addChecks(this, it -> it instanceof String s && s.length() >= length);

      //  addChecks(it -> it instanceof String s && s.length() >= length, this);

        return this;
    }

    public StringSchema contains(String str) {
         addChecks("contains", it -> it instanceof String s && s.contains(str));
      //  addChecks(it -> it instanceof String s && s.contains(str), this);

        return this;
    }
}

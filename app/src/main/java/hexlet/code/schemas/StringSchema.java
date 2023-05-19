package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private boolean checkRequired = false;
    private boolean checkMinLength = false;
    private int minLength = 0;
    private boolean checkString = false;
    private String subString;

    public StringSchema required() {
        this.checkRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.checkMinLength = true;
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        this.checkString = true;
        this.subString = str;

        return this;

    }

    public boolean isValid(Object data) {

        if ((checkRequired) && ((!super.isNotNull(data))
                || (data.equals(""))
                || !(data.getClass() == String.class))) {
            return false;
        }

        if ((checkString) && (super.isNotNull(data)) && (!data.toString().contains(subString))) {
            return false;
        }

        if ((checkMinLength) && (super.isNotNull(data)) && (!(data.toString().length() >= minLength))) {
            return false;
        }

        return true;

    }
}

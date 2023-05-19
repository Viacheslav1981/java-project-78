package hexlet.code.shemas;

public class StringSchema extends BaseSchema {
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

        /*
        if ((checkRequired) && ((!super.isValid(data))
                || (data.equals(""))
                || !(data.getClass() == String.class))) {
                return false;
            }

         */

        if (checkRequired) {
            if (((!super.isValid(data))
                    || (data.equals(""))
                    || !(data.getClass() == String.class))) {
                return false;
            }
        }

        if (checkString) {
            return data.toString().indexOf(subString) != 1;
        }

        if (checkMinLength) {
            return data.toString().length() >= minLength;
        }
        return true;
    }
}

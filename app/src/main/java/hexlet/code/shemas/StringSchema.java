package hexlet.code.shemas;

public class StringSchema {
    private boolean checkNull = false;
    private boolean checkMinLength = false;
    private int minLength = 0;
    private boolean checkString = false;
    private String subString;

    public StringSchema required() {
        this.checkNull = true;
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

        if (this.checkNull) {
            if (data == null) {
                return false;
            } else {
                return !data.toString().equals("");
            }
        }

        if (this.checkString) {
            String fullString = data.toString();
            int index = fullString.indexOf(this.subString);
            return index != -1;
        }
        if (this.checkMinLength) {
            return data.toString().length() >= this.minLength;
        }

        return true;

    }
}

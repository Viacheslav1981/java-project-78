package hexlet.code.shemas;

public class StringSchema extends BaseSchema{
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
        boolean bRet = true;

        if (this.checkRequired) {
            bRet = !(super.isValid(data) || (data.equals(""))
                    || !(data instanceof String));
        }

        if (this.checkString) {
            String fullString = data.toString();
            int index = fullString.indexOf(this.subString);
            bRet = index != -1;

        }

        if (this.checkMinLength) {
           // assert data != null;
            bRet = data.toString().length() >= this.minLength;

        }

        return bRet;
    }
}

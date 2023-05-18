package hexlet.code.shemas;

public class NumberSchema extends BaseSchema {

    private boolean checkRequired = false;
    private boolean checkPositive = false;

    private boolean checkRange = false;
    private int limit1;
    private int limit2;

    public NumberSchema required() {
        this.checkRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.checkPositive = true;
        return this;
    }

    public NumberSchema range(int limit1, int limit2) {
        this.checkRange = true;
        this.limit1 = limit1;
        this.limit2 = limit2;

        return this;
    }

    public boolean isValid(Object data) {

        boolean bRet = true;
        /*
        if ((this.checkRequired) && !(super.isValid(data)
                && (data instanceof Integer))) {
                return false;
        }

         */

        if (this.checkRequired) {
            if ((!super.isValid(data))
                    || !(data.getClass() == Integer.class)) {
                return false;
            }
        }

        if (this.checkPositive) {
            bRet = (Integer) data > 0;
        }

        if (this.checkRange) {
            bRet =  ((Integer) data >= limit1 && (Integer) data <= limit2);
        }

        return bRet;
    }


}

package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

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

        if (checkRequired) {
            if ((!super.isNotNull(data))
                    || !(data.getClass() == Integer.class)) {
                return false;
            }
        }

        if (checkPositive && (super.isNotNull(data))) {
            bRet = (Integer) data > 0;
        }

        if (checkRange && (super.isNotNull(data))) {
            bRet =  ((Integer) data >= limit1 && (Integer) data <= limit2);
        }

        return bRet;
    }


}

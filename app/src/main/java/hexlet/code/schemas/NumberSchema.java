package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    private boolean checkRequired = false;
    private boolean checkPositive = false;

    private boolean checkRange = false;
    private int limit1;
    private int limit2;

    public NumberSchema required() {
        this.checkRequired = true;

        addCheck(it -> it instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        this.checkPositive = true;

        addCheck(it -> it instanceof Integer i && i > 0 || it == null);
      //  addCheck(it -> (Integer) it > 0);

        return this;
    }

    public NumberSchema range(int range1, int range2) {
        this.checkRange = true;
        this.limit1 = range1;
        this.limit2 = range2;

        addCheck(it -> it instanceof Integer i && i >= limit1
                && i <= limit2);

        return this;
    }
    /*

    public boolean isValid(Object data) {

        boolean bRet = true;

        if ((checkRequired) && ((super.isNotNull(data))
                || !(data.getClass() == Integer.class))) {

            return false;
        }

        if (checkPositive && (!super.isNotNull(data))) {
            bRet = (Integer) data > 0;
        }


        if (checkRange && (!super.isNotNull(data))) {
            bRet = ((Integer) data >= limit1 && (Integer) data <= limit2);
        }

        return bRet;

    }

     */


}

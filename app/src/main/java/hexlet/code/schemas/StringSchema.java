package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
  //  private boolean checkRequired = false;
  //  private boolean checkMinLength = false;
    private int minLength = 0;
  //  private boolean checkString = false;
  //  private String subString;

    public StringSchema required() {
      //  this.checkRequired = true;

        addCheck(it -> it instanceof String && it != "");

        return this;
    }

    public StringSchema minLength(int length) {
     //   this.checkMinLength = true;
        this.minLength = length;

        addCheck(it -> it instanceof String s && s.length() >= minLength);

        return this;
    }

    public StringSchema contains(String str) {
     //   this.checkString = true;
     //   this.subString = str;
        Check check;
        check = it -> it instanceof String s && s.contains(str);

        addCheck(check);

        //Check check = data -> data.toString().contains(str);

        return this;

    }



    /*
    public boolean isValid(Object data) {


        if ((checkRequired) && ((super.isNotNull(data))
                || (data.equals("")))) {
            return false;
        }


        if ((checkString)
                && (!data.toString().contains(subString))) {
            return false;
        }


        return (!checkMinLength)
                || (data.toString().length() >= minLength);
    }

     */


}

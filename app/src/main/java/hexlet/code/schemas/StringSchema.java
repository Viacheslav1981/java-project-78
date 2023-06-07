package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
  //  private boolean checkRequired = false;
  //  private boolean checkMinLength = false;
    private int minLength = 0;
  //  private boolean checkString = false;
  //  private String subString;
    // public String nameCheck = "string";
    //public static String schema = "StringSchema";
    public static StringSchema stringSchema;

    public StringSchema() {
       // super(stringSchema);
        super.setSchema(stringSchema);
    }

    public StringSchema required() {
      //  this.checkRequired = true;
        addChecks("requiredString", it -> it instanceof String && it != "" );

      //  addCheck(it -> it instanceof String && it != "");

        return this;
    }

    public StringSchema minLength(int length) {
     //   this.checkMinLength = true;
        this.minLength = length;

        addChecks("minLength", it -> it instanceof String s && s.length() >= minLength);

      //  addCheck(it -> it instanceof String s && s.length() >= minLength);

        return this;
    }

    public StringSchema contains(String str) {
     //   this.checkString = true;
     //   this.subString = str;
        Check check;
        check = it -> it instanceof String s && s.contains(str);

        addChecks("contains", check);


      //  addCheck(check);

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

package hexlet.code.schemas;


public abstract class BaseSchema {

    public final boolean isNotNull(Object data) {
        return data == null;
    }

    public abstract boolean isValid(Object data);


}

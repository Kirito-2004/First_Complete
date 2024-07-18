package shared.transferObject;

import java.io.Serializable;

public class Response implements Serializable {
    private String message;
    private Object arg;

    public Response(String message, Object arg) {
        this.message = message;
        this.arg = arg;
    }

    public String getMessage() {
        return message;
    }

    public Object getArg() {
        return arg;
    }
}

package HelloSpringMVC.Exception;

/**
 * Created by Administrator on 2017/2/7.
 */
public class ParameterException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1194948002785147045L;

    public ParameterException() {
        super();
    }

    public ParameterException(String message){
        super(message);
    }

    public ParameterException(Throwable cause){
        super(cause);
    }

    public ParameterException(String meaade, Throwable cause){
        super(meaade, cause);
    }
}

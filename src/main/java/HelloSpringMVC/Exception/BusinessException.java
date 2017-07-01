package HelloSpringMVC.Exception;

/**
 * Created by Administrator on 2017/2/7.
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 7580414007533573480L;

    private String code;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Throwable cause){
        super(cause);
    }

    public BusinessException(String message, Throwable cause){
        super(message, cause);
    }

    public BusinessException(String code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
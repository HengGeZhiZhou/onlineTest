package HelloSpringMVC.controller;

import HelloSpringMVC.Exception.BusinessException;
import HelloSpringMVC.Exception.ParameterException;
import HelloSpringMVC.entity.ReturnInfo;
import HelloSpringMVC.entity.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


public class BaseController {
    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    @ResponseBody
    public ReturnInfo exp(Exception ex) {
        if(ex instanceof BusinessException) {
            ReturnInfo returnInfo=new ReturnInfo();
            returnInfo.setCode(Integer.parseInt(((BusinessException) ex).getCode()));
            returnInfo.setMsg(ex.getMessage());
            return returnInfo;
        }else if(ex instanceof ParameterException) {
            ReturnInfo returnInfo=new ReturnInfo();
            returnInfo.setCode(Integer.parseInt(((BusinessException) ex).getCode()));
            returnInfo.setMsg(ex.getMessage());
            return returnInfo;
        } else {
            ReturnInfo returnInfo=new ReturnInfo();
            returnInfo.setCode(Integer.parseInt(((BusinessException) ex).getCode()));
            returnInfo.setMsg(ex.getMessage());
            return returnInfo;
        }
    }
}

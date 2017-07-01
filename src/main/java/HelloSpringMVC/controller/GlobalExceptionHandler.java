package HelloSpringMVC.controller;

import HelloSpringMVC.entity.ReturnInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ReturnInfo handle(Exception ex) {
//        System.out.println("GlobalExceptionHandler: " + ex.getMessage());
//        ReturnInfo returnInfo=new ReturnInfo();
//        returnInfo.setCode(400);
//        returnInfo.setMsg(ex.getMessage());
//        return returnInfo;
//    }
}

package HelloSpringMVC.controller;

import HelloSpringMVC.Exception.BusinessException;
import HelloSpringMVC.Exception.ParameterException;
import HelloSpringMVC.entity.ReturnInfo;
import HelloSpringMVC.entity.User;
import HelloSpringMVC.service.UserService;

import HelloSpringMVC.util.CheckExcelFileTypeUtil;
import io.swagger.annotations.*;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static HelloSpringMVC.util.ReadExcel.readExcel;


@Controller
@RequestMapping(value = "/swagger/Test/*")
@Api(value = "HelloController", description = "Hello������")
public class TestController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @ApiOperation("�û���¼")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "�û���", required = true, dataType = "string"), @ApiImplicitParam(name = "password", value = "����", required = true, dataType = "string")})
    @ResponseBody
    @RequestMapping(value = "getUser", method = RequestMethod.POST, produces = "application/json")
    public User getUser(@RequestBody User userInfo) {
        userService.addUser(userInfo);
        return userInfo;
    }

    @ApiOperation("json���ز���")
    @RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String returnString() {
        return "hello return string �������ģ���û������";
    }

    @RequestMapping(value = "returnTest/user/{userId}/roles/{roleId}")
    @ResponseBody
    @ApiOperation(value = "�ӿ�", httpMethod = "GET")
    public String returnTest(@ApiParam(value = "�û��˻�", name = "userAccount", required = true)
                             @PathVariable("userId") String id,
                             @ApiParam(value = "�û�����", name = "userPassword", required = true)
                             @PathVariable("roleId") String roleId) {
        System.out.println("�û�idΪ��" + id);
        System.out.println("��ɫidΪ��" + roleId);
        return "success";
    }

//    @RequestMapping(value = "saveUser/{username}")
//    @ResponseBody
//    public User saveUser(@PathVariable("username") String username) {
////        userService.addUser(username);
////        return "ok";
////        return userService.addUser(username);
//
//    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "result", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    ReturnInfo returnInfo() {
        User user = new User();
        user.setUid("1");
        user.setUsername("�й���");
        user.setPassword("�����Ȳ�");
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(100);
        returnInfo.setMsg("msg");
        returnInfo.setObj(user);
        return returnInfo;
    }

    @RequestMapping(value = "/Exception/{id}", method = RequestMethod.GET, produces = "application/json;utf-8")
    public void controller(@PathVariable("id") Integer id) throws Exception {
        switch (id) {
            case 1:
                throw new BusinessException("10", "controller10");
            case 2:
                System.out.println(10 / 0);
            case 3:
                throw new BusinessException("30", "controller30");
            case 4:
                throw new BusinessException("40", "controller40");
            case 5:
                throw new BusinessException("50", "controller50");
            default:
                throw new ParameterException("Controller Parameter Error");
        }
    }

    @RequestMapping(value = "/testExceptionHandlerExceptionResolver/{i}")
    @ResponseBody
    public String testExceptionHandlerExceptionResolver(@PathVariable("i") int i) {
        System.out.println("result: " + (10 / i));
        return "success";
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnInfo handle(Exception ex) {
        System.out.println("GlobalExceptionHandler: " + ex);
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(400);
        returnInfo.setMsg(ex.getMessage());
        return returnInfo;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
//                user.setToken("1400"+System.currentTimeMillis());
//                userService.addToken(user);
            } catch (AuthenticationException ae) {
                System.out.println("��¼ʧ��" + ae.getMessage());
            }
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "getValue",method =RequestMethod.POST)
    public String getValue(@RequestParam String Lng,@RequestParam String Lat){
        System.out.println(Lat);
        System.out.println(Lng);
        return "sdas";
    }

    /**
     * ͼƬ�ļ��ϴ�
     *
     */
//    @ResponseBody
//    @RequestMapping(value = "photoUpload", method = RequestMethod.POST)
//    public String photoUpload(MultipartFile file, HttpServletRequest request ) throws IllegalStateException, IOException {
//        String path = "";
//        if(!file.isEmpty()){
//            String uuid = UUID.randomUUID().toString().replaceAll("-","");
//            String fileName=file.getOriginalFilename();
//            String suffix=fileName.substring(fileName.indexOf(".")+1);
//            if (suffix.equals("xlsx") || suffix.equals("xls")){
//                path="D:\\pic\\"+uuid+"."+suffix;
//                file.transferTo(new File(path));
//                File readFile=new File(path);
//                List<List<Object>> list = readExcel(readFile,0);
//                for (List<Object> list2 : list) {
//                    String hcp_idString = list2.get(0).toString();
//                    String hcp_nameString = list2.get(1).toString();
//                    System.out.println("===="+hcp_idString);
//                    System.out.println(hcp_nameString);
//                }
//            }else {
//                return "�ļ���ʽ�����޷��ϴ�";
//            }
//        }
////        System.out.println(path);
//        return "success";
//    }
    @ResponseBody
    @RequestMapping(value="photosUpload",method=RequestMethod.POST)
    private String fildUpload(MultipartFile[] file,
                              HttpServletRequest request)throws Exception{
        //�������·��webapp����·��
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        List<String> listImagePath=new ArrayList<String>();
        for (MultipartFile mf : file) {
            if(!mf.isEmpty()){
                //����uuid��Ϊ�ļ�����
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                //����ļ����ͣ������ж��������ͼƬ����ֹ�ϴ���
                String contentType=mf.getContentType();
                //����ļ���׺����
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                path="/pic/"+uuid+"."+imageName;
                mf.transferTo(new File(pathRoot+path));
                listImagePath.add(path);
                System.out.println(path);
            }
        }

        return "success";
    }

}

package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Repository.UserRepository;

//this class maps four request:/user/getPerInfo, /user/changeInfo, /user/changeImg and /user/changePasswd,
//which relate to personInfo

@RestController
public class PerInfo {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/getPerInfo")
    @ResponseBody
    public Result getPerInfo(@RequestBody Request request){
        Data data = new Data();
        User user = userRepository.findByUserID(request.userID);
        if (user==null){
            return Result.Error("201","用户不存在");
        }
        else{
            data.email = user.email;
            data.phoneNum = user.phoneNum;
            data.realName = user.realName;
            data.url = user.url;
            data.userName = user.userName;
            return Result.Success(data);
        }
    }


    @RequestMapping("/user/changeInfo")
    @ResponseBody
    public Result changeInfo(@RequestBody ChangeInfoRequest changeInfoRequest){
        User user = userRepository.findByUserID(changeInfoRequest.userID);
        if (user==null){
            return Result.Error("201","用户不存在");
        }
        else {
            user.email = changeInfoRequest.email;
            user.phoneNum = changeInfoRequest.phoneNum;
            user.realName = changeInfoRequest.realName;
            userRepository.save(user);
            return Result.Success();
        }
    }

    @RequestMapping("/user/changeImg")
    @ResponseBody
    public Result changeImg(@RequestBody ChangeImgRequest changeInfoRequest){
        User user = userRepository.findByUserID(changeInfoRequest.userID);
        Result result = new Result();
        if (user == null){
            return Result.Error("201","用户不存在");
        }
        else{
            user.url = changeInfoRequest.url;
            userRepository.save(user);
            return Result.Success();
        }
    }

    @RequestMapping("/user/changePasswd")
    @ResponseBody
    public Result changeImg(@RequestBody ChangePasswdRequest changePasswdRequest){
        User user = userRepository.findByUserID(changePasswdRequest.userID);
        Result result = new Result();
        if (user == null){
            return Result.Error("201","用户不存在");
        }
        else if (!user.passwd.equals(changePasswdRequest.oldPasswd)){
            return Result.Error("201","旧密码错误");
        }
        else{
            user.passwd = changePasswdRequest.newPasswd;
            userRepository.save(user);
            return Result.Success();
        }
    }
    private class Data{
        String userName;
        String realName;
        String email;
        String phoneNum;
        String url;
    }


    private class Request{
        int userID;
    }
    private class ChangeInfoRequest extends Request{
        String realName;
        String email;
        String phoneNum;
    }
    private class ChangeImgRequest extends Request{
        String url;
    }
    private class ChangePasswdRequest extends Request{
        String oldPasswd;
        String newPasswd;
    }

}


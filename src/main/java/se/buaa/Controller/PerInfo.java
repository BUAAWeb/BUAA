package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    public GetPerInfoRes getPerInfo(@RequestBody Request request){
        GetPerInfoRes getPerInfoRes = new GetPerInfoRes();
        getPerInfoRes.data = new Data();
        User user = userRepository.findByUserID(request.userID);
        if (user==null){
            getPerInfoRes.code = 201;
            getPerInfoRes.msg="用户不存在";
        }
        else{
            getPerInfoRes.code=200;
            getPerInfoRes.msg = "查找成功";
            getPerInfoRes.data.email = user.email;
            getPerInfoRes.data.phoneNum = user.phoneNum;
            getPerInfoRes.data.realName = user.realName;
            getPerInfoRes.data.url = user.url;
            getPerInfoRes.data.userName = user.userName;
        }
        return getPerInfoRes;
    }


    @RequestMapping("/user/changeInfo")
    @ResponseBody
    public Result changeInfo(@RequestBody ChangeInfoRequest changeInfoRequest){
        Result result = new Result();
        User user = userRepository.findByUserID(changeInfoRequest.userID);
        if (user==null){
            result.code=201;
            result.msg="用户不存在";
        }
        else {
            user.email = changeInfoRequest.email;
            user.phoneNum = changeInfoRequest.phoneNum;
            user.realName = changeInfoRequest.realName;
            userRepository.save(user);
            result.code=200;
            result.msg="修改成功";
        }
        return result;
    }

    @RequestMapping("/user/changeImg")
    @ResponseBody
    public Result changeImg(@RequestBody ChangeImgRequest changeInfoRequest){
        User user = userRepository.findByUserID(changeInfoRequest.userID);
        Result result = new Result();
        if (user == null){
            result.code=201;
            result.msg="用户不存在";
        }
        else{
            user.url = changeInfoRequest.url;
            userRepository.save(user);
            result.code=200;
            result.msg = "修改成功";
        }
        return result;
    }

    @RequestMapping("/user/changePasswd")
    @ResponseBody
    public Result changeImg(@RequestBody ChangePasswdRequest changePasswdRequest){
        User user = userRepository.findByUserID(changePasswdRequest.userID);
        Result result = new Result();
        if (user == null){
            result.code=201;
            result.msg="用户不存在";
        }
        else if (!user.passwd.equals(changePasswdRequest.oldPasswd)){
            result.code=201;
            result.msg="旧密码错误";
        }
        else{
            user.passwd = changePasswdRequest.newPasswd;
            userRepository.save(user);
            result.code=200;
            result.msg = "修改成功";
        }
        return result;
    }



    // 这里用了两个总的类，Result和Request，其他请求的特定格式result和request都继承自这两个类，
    // 不知道有没有用，但为了好玩就这样写了，感觉看起来更整齐一些哈哈哈。    By YZX
    private class Result{
        int code;
        String msg;
    }
    private class GetPerInfoRes extends Result{
        Data data;
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


package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Repository.UserRepository;

//this class maps four request:/user/getPerInfo, /user/changeInfo, /user/changeImg and /user/changePasswd,
//which relates to personInfo

@RestController
public class PerInfo {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/getPerInfo")
    @ResponseBody
    public Result getPerInfo(@RequestParam int userID){
        Data data = new Data();
        User user = userRepository.findByUserID(userID);
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
    public Result changeInfo(@RequestParam int userID,String realName,String email,String phoneNum){
        User user = userRepository.findByUserID(userID);
        if (user==null){
            return Result.Error("201","用户不存在");
        }
        else {
            user.email = email;
            user.phoneNum = phoneNum;
            user.realName = realName;
            userRepository.save(user);
            return Result.Success();
        }
    }

    @RequestMapping("/user/changeImg")
    @ResponseBody
    public Result changeImg(@RequestParam int userID, String url){
        User user = userRepository.findByUserID(userID);
        if (user == null){
            return Result.Error("201","用户不存在");
        }
        else{
            user.url = url;
            userRepository.save(user);
            return Result.Success();
        }
    }

    @RequestMapping("/user/changePasswd")
    @ResponseBody
    public Result changeImg(@RequestParam int userID, String oldPasswd,String newPasswd){
        User user = userRepository.findByUserID(userID);
        if (user == null){
            return Result.Error("201","用户不存在");
        }
        else if (!user.passwd.equals(oldPasswd)){
            return Result.Error("201","旧密码错误");
        }
        else{
            user.passwd = newPasswd;
            userRepository.save(user);
            return Result.Success("修改成功！");
        }
    }
    public class Data{
        public String userName;
        public String realName;
        public String email;
        public String phoneNum;
        public String url;
    }


}


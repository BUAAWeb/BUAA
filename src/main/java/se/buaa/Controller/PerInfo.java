package se.buaa.Controller;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Config.JwtUtils;
import se.buaa.Entity.Data.FollowedExpertInfo;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Entity.User_Expert;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Repository.UserRepository;
import se.buaa.Repository.User_ExpertRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//this class maps four request:/user/getPerInfo, /user/changeInfo, /user/changeImg and /user/changePasswd,
//which relates to personInfo

@RestController
public class PerInfo {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    User_ExpertRepository user_expertRepository;

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
    public Result changePassword(@RequestParam int userID, String oldPasswd,String newPasswd){
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

    @GetMapping("/user/getFollowList")
    public Result getFollowList(@RequestParam(value = "userID") int userid) {
        List<User_Expert> ueList = user_expertRepository.findByUserId(userid);
        List<FollowedExpertInfo> list = new ArrayList<>();

        for (User_Expert ue : ueList) {
            Expert e = expertRepository.findByExpertID(ue.expertId);
            list.add(new FollowedExpertInfo(e.getExpertID(), e.getName()));
        }

        return Result.Success(list);
    }
    @RequestMapping("/user/isadmin")
    @ResponseBody
    public Result isAdmin(@RequestParam String token){
        Map map= JwtUtils.parseToken(token);
        boolean is_admin = (Boolean) map.get("is_admin");
        if (is_admin)
            return Result.Success();
        else
            return Result.Error();
    }


    public class Data{
        public String userName;
        public String realName;
        public String email;
        public String phoneNum;
        public String url;
    }


}


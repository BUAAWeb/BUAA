package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Config.JwtUtils;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpertRepository expertRepository;

    @GetMapping("/expert/test")
    public Result test(){
        Expert expert = expertRepository.findByExpertID("2");
        return Result.Success(expert);
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public Result login(@RequestParam String userName, String passwd){
        Data data = new Data();
        User db_user = userRepository.findByUserName(userName);
        if (db_user!=null&&db_user.passwd.equals(passwd)){
            data.userID = db_user.userID;
            Map map = new HashMap<>();
            map.put("id",db_user.userID);
            map.put("username",db_user.userName);
            map.put("password",db_user.passwd);
            map.put("is_admin",db_user.isAdmin);
            data.token = JwtUtils.createToken(map);
            data.is_admin = db_user.isAdmin==1;

        }
        else {
            data.userID = -1;
            return Result.Error("201","用户名或密码错误",data);
        }
        return Result.Success(data);
    }
    public class Data{
        public int userID=-1;
        public String token;
        public boolean is_admin;
    }
}


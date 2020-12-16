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

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpertRepository expertRepository;

    @RequestMapping("PostLoad Test")
    public void test(){
        Expert expert = expertRepository.findByExpertID("2");
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
            data.token = JwtUtils.createToken(map);
        }
        else {
            data.userID = -1;
            return Result.Error("201","用户名或密码错误");
        }
        return Result.Success();
    }
    public class Data{
        int userID=-1;
        String token;
    }
}


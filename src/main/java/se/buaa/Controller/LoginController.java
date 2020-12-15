package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Config.JwtUtils;
import se.buaa.Entity.Expert;
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
    public Result login(@RequestBody User vue_user){
        Result result = new Result();
        result.data = new Data();
        User db_user = userRepository.findByUserName(vue_user.userName);
        if (userRepository.findByUserName(vue_user.userName).passwd.equals(vue_user.passwd)){
            result.code=200;
            result.msg="登录成功";
            result.data.userID = db_user.userID;
            Map map = new HashMap<>();
            map.put("id",db_user.userID);
            map.put("username",db_user.userName);
            map.put("password",db_user.passwd);
            result.data.token = JwtUtils.createToken(map);
        }
        else {
            result.code = 201;
            result.msg = "用户名或密码错误";
            result.data.userID = -1;
        }
        return result;
    }
    private class Data{
        int userID=-1;
        String token;
    }
    private class Result{
        int code;
        String msg;
        Data data;
    }
}


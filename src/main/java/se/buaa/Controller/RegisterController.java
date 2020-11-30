package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Entity.User;
import se.buaa.Repository.UserRepository;

@RestController
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        Result result = new Result();
        result.data = new Data();

        if (userRepository.findByUserName(user.userName)!=null){
            result.code=201;
            result.msg="用户名已被使用";
        }
        else if (userRepository.findByUserName(user.email)!=null){
            result.code=201;
            result.msg="邮箱已被使用";
        }
        else {
            result.code=200;
            result.msg="注册成功！";
            user.isAdmin = 0;
            user.isBanned = 0;
            user.isVerified = 0;
            userRepository.save(user);
            user.userID = userRepository.findByUserName(user.userName).userID;
        }
        return result;
    }


    private class Data{
        int userID = -1;
    }
    private class Result{
        int code;
        String msg;
        Data data;
    }
}

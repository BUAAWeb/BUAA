package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Repository.UserRepository;

@RestController
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/register")
    @ResponseBody
    public Result register(@RequestParam String userName,String passwd, String email){
        Data data = new Data();

        if (userRepository.findByUserName(userName)!=null){
            return Result.Error("201","用户名已被使用");

        }
        else if (userRepository.findByUserName(email)!=null){
            return Result.Error("201","邮箱已被使用");
        }
        else {
            User user = new User();
            user.passwd = passwd;
            user.userName = userName;
            user.email = email;
            user.isAdmin = 0;
            user.isBanned = 0;
            user.isVerified = 0;
            userRepository.save(user);
            data.userID = userRepository.findByUserName(user.userName).userID;
            return Result.Success(data);
        }
    }


    public class Data{
        public int userID = -1;
    }
}

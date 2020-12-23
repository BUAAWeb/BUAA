package se.buaa.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Config.MailSender;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.UserRepository;

@RestController
public class SendEmailController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/sendEmail")
    @ResponseBody
    public Result sendEmail(@RequestParam String email){
        System.out.println(email);
        int verification;
//        try {
//            verification=(int)Math.round((Math.random()) * 10000);
//            MailSender.sendMail(email, "欢迎您使用LinkedBrains学术搜索引擎！\n您的验证码为:"+verification+"，请尽快完成验证\n\n---------From BUAA科技集团");
//        }catch (Exception e){
//            return Result.Error("201","邮件发送异常，请联系管理员或重试。");
//        }
        verification = 1821;
        Data data = new Data();
        data.verification = Integer.toString(verification);
        return Result.Success(data);
    }

    public class Data{

        public String verification;
    }
}

package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.buaa.Config.MailSender;
import se.buaa.Repository.UserRepository;

@RestController
public class SendEmailController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/sendEmail")
    @ResponseBody
    public Result sendEmail(@RequestBody String email){
        System.out.println(email);
        Result result = new Result();
        int verification;
        try {
            MailSender mSender = new MailSender();
            verification=(int)Math.round((Math.random()+1) * 1000);
            mSender.sendMail(email, "欢迎您使用LinkedBrains学术搜索引擎！\n您的验证码为:"+verification+"，请尽快完成验证\n\n---------From BUAA科技集团");
        }catch (Exception e){
            result.code=201;
            result.msg="邮件发送异常，请联系管理员或重试。";
            return result;
        }
        result.verification = verification;
        result.code = 200;
        result.msg = "发送成功，请注意接收";
        return result;
    }
    private class Result{
        int code;
        String msg;
        int verification;
    }
}

package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Config.MailSender;
import se.buaa.Repository.UserRepository;

@RestController
public class SendEmailController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user/sendEmail")
    @ResponseBody
    public Result sendEmail(@RequestParam String email){
        Result result = new Result();
        int verification;
        try {
            MailSender mSender = new MailSender();
            verification=(int)Math.round((Math.random()+1) * 1000);
            mSender.sendMail(email, "您的验证码为:"+verification+"，请尽快完成验证\n\n---------From BUAA开发团队");
        }catch (Exception e){
            result.code=201;
            result.msg="邮件发送异常，请联系管理员重试。";
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

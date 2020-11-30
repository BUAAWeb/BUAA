package se.buaa.Config;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Component
public class MailSender {
    private static String FromEmail = "buaa_21@sina.com";
    private static String EmailPassword = "f9945e9fe96aa7fc";
    private static String EmailSMTPhost = "smtp.sina.com";

    public static void sendMail (String toEmail , String identifyCode) throws Exception{
        Properties props = new Properties();

        //设置邮件Debug功能,f9945e9fe96aa7fc
        props.setProperty("smtp.debug", "true");

        //设置是否开启邮件认证功能
        props.setProperty("mail.smtp.auth", "true");

        //设置SMTP端口
        props.put("mail.smtp.port", 25);

        //设置邮件传输的协议
        props.setProperty("mail.transport.protocol", "smtp");

        //设置发送验证邮件的邮箱
        props.setProperty("mail.smtp.host", EmailSMTPhost);

        Session session = Session.getInstance(props);
        session.setDebug(true);

        Message msg = new MimeMessage(session);

        msg.setSubject("邮件主题");
        msg.setText(identifyCode);
        msg.setSentDate(new Date());
        msg.setFrom(new InternetAddress(FromEmail,"BUAA21","UTF-8"));

        Transport transport = session.getTransport();
        transport.connect(EmailSMTPhost,FromEmail,EmailPassword);

        transport.sendMessage(msg, new Address[] {new InternetAddress(toEmail)});

        transport.close();

    }
}

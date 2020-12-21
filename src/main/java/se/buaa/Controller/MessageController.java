package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Config.JwtUtils;
import se.buaa.Entity.Message;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.MessageRepository;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/getMessage")
    public Result getMessage(@RequestParam("userID") Integer id, @RequestParam("token") String token){
        if (JwtUtils.verifyToken(token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        List<Message> messageList = messageRepository.findByUser_id(id);
        return Result.Success(messageList);
    }

    @GetMapping("/setMessageStatus")
    public Result setMessageStatus(@RequestParam("messageID") Integer id, @RequestParam("token") String token){
        if (JwtUtils.verifyToken(token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        Message message = messageRepository.findByMsg_id(id);
        message.is_read = true;
        messageRepository.save(message);
        return Result.Success();
    }

    @GetMapping("/deleteMessage")
    public Result deleteMessage(@RequestParam("messageID") Integer id, @RequestParam("token") String token){
        if (JwtUtils.verifyToken(token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        messageRepository.deleteByMsg_id(id);
        return Result.Success();
    }

}

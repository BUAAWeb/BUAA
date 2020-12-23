package se.buaa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import se.buaa.Entity.Response.Result;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping("/user/upload")
    @ResponseBody
    public Result upload(@RequestParam MultipartFile file) {
        String filePath = "C:\\Users\\LENOVO\\Desktop\\uploadPic";
        String fileName = file.getOriginalFilename();
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return Result.Success();
        } catch (IOException e) {
            return Result.Error("201","写入失败");
        }


    }
}


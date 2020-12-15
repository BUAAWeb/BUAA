package se.buaa.Controller;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Config.JwtUtils;
import se.buaa.Entity.ApplicationForm;
import se.buaa.Entity.Document;
import se.buaa.Entity.Response.Result;
import se.buaa.Repository.ApplicationRepository;
import se.buaa.Repository.DocumentRepository;
import se.buaa.Repository.ExpertRepository;
import se.buaa.Repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    ExpertRepository expertRepository;

    @RequestMapping("create")
    public Result getAll(@RequestBody createReq request){
        if (JwtUtils.verifyToken(request.token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.email = request.email;
        applicationForm.flag = request.flag;
        applicationForm.objectID = request.ObjectID;
        applicationForm.userID = request.userID;
        applicationForm.userName = userRepository.findByUserID(request.userID).userName;
        if (request.flag == 1)
            applicationForm.objectName = expertRepository.findByExpertID(request.ObjectID).getName();
        else
            applicationForm.objectName = documentRepository.findByDocumentID(request.ObjectID).getTitle();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        applicationForm.time = dateFormat.format(date);
        applicationForm.result = 0;
        applicationRepository.save(applicationForm);
        return Result.Success(null);
    }
    @RequestMapping("getAll")
    public Result getAll(@RequestBody getAllReq request){
        if (JwtUtils.verifyToken(request.token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        ArrayList<ApplicationForm> list = applicationRepository.findAll();
        int size = list.size();
        getAllRes data = new getAllRes();
        data.totalPages = size/request.size;
        data.page = request.page;
        data.totalElements = request.page* request.size>size? request.size : size - request.size;//若为最后一页，则该页长度不是每页的个数request.size
        data.rows = (ArrayList<ApplicationForm>) list.subList((request.page-1)* request.size, request.page* request.size-1);
        return Result.Success(data);
    }
    @RequestMapping("reject")
    public Result reject(@RequestBody rejectReq request){
        if (JwtUtils.verifyToken(request.token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        ApplicationForm applicationForm = applicationRepository.findApplicationFormByFormID(request.formID);
        if (applicationForm==null){
            return Result.Error("201","表单不存在，请刷新重试");
        }
        applicationForm.result = 2;
        applicationForm.msg = request.reason;
        applicationRepository.save(applicationForm);
        return Result.Success();
    }
    @RequestMapping("agree")
    public Result agree(@RequestBody agreeReq request){
        if (JwtUtils.verifyToken(request.token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        ApplicationForm applicationForm = applicationRepository.findApplicationFormByFormID(request.formID);
        if (applicationForm==null){
            return Result.Error("201","表单不存在，请刷新重试");
        }
        applicationForm.result = 1;
        return Result.Success();
    }
    private class createReq{
        String token;
        int userID;
        String ObjectID;
        int flag;
        String email;
    }


    private class getAllReq{
        int size;
        int page;
        boolean isAll;
        String token;
        int flag;

    }
    private class rejectReq{
        String token;
        int formID;
        String reason;

    }
    private class agreeReq{
        String token;
        int formID;
    }
    private class getAllRes{
        int totalPages;
        int page;
        int totalElements;
        ArrayList<ApplicationForm> rows = new ArrayList<>();
    }

}

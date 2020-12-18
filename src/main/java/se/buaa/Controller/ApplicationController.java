package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Config.JwtUtils;
import se.buaa.Entity.ApplicationForm;
import se.buaa.Entity.Document;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Relation.Document_Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Repository.*;

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
    @Autowired
    Docu_ExpertRepository docu_expertRepository;

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
        User user = userRepository.findByUserID(applicationForm.userID);
        if (request.flag == 1){//门户
            Expert expert = expertRepository.findByExpertID(request.ObjectID);
            if(expert==null)
                return Result.Error("201","专家不存在");
            if (expert.isVerified==1)
                return Result.Error("201","门户已被认领");
            if (user.isVerified==1){
                return Result.Error("201","您已认领门户，请勿重复认领！");
            }
            applicationForm.objectName = expertRepository.findByExpertID(request.ObjectID).getName();
        }

        else if (request.flag==0){
            if(documentRepository.findByDocumentID(request.ObjectID)==null)
                return Result.Error("201","文献不存在");
            if (user.isVerified!=1){
                return Result.Error("201","请先认领门户后再进行认领文献操作！");
            }
            applicationForm.objectName = documentRepository.findByDocumentID(request.ObjectID).getTitle();
        }
        else return Result.Error("201","flag参数错误");
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

        User user = userRepository.findByUserID(applicationForm.userID);
        if (applicationForm.flag==1){//门户
            if (user.isVerified==1){
                return Result.Error("201","用户已认证，无法再次认领门户！");
            }
            user.expertID = applicationForm.objectID;
            user.isVerified = 1;
            Expert expert = expertRepository.findByExpertID(applicationForm.objectID);
            expert.isVerified = 1;
            expert.userID = user.userID;
            expertRepository.save(expert);
            userRepository.save(user);
        }
        else if (applicationForm.flag==0){
            if (user.isVerified!=1){
                return Result.Error("201","用户未认证，请先认领门户后再认领文献！");
            }
            Document_Expert document_expert = new Document_Expert();
            document_expert.setDocumentID(applicationForm.objectID);
            document_expert.setExpertID(user.expertID);
            docu_expertRepository.save(document_expert);
        }
        else return Result.Error("201","flag参数错误");
        applicationForm.result = 1;
        return Result.Success();
    }
    public class createReq{
        public String token;
        public int userID;
        public String ObjectID;
        public int flag;
        public String email;
    }


    public class getAllReq{
        public int size;
        public int page;
        public boolean isAll;
        public String token;
        public int flag;

    }
    public class rejectReq{
        public String token;
        public int formID;
        public String reason;

    }
    public class agreeReq{
        public String token;
        public int formID;
    }
    public class getAllRes{
        public int totalPages;
        public int page;
        public int totalElements;
        public ArrayList<ApplicationForm> rows = new ArrayList<>();
    }

}

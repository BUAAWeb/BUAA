package se.buaa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.buaa.Config.JwtUtils;
import se.buaa.Dao.ES_DocumentDao;
import se.buaa.Entity.ApplicationForm;
import se.buaa.Entity.Document;
import se.buaa.Entity.ESDocument.ES_Document;
import se.buaa.Entity.Expert;
import se.buaa.Entity.Relation.Document_Expert;
import se.buaa.Entity.Response.Result;
import se.buaa.Entity.User;
import se.buaa.Repository.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    ES_DocumentDao es_documentDao;

    @RequestMapping("create")
    public Result getAll(@RequestBody createReq request){
        if (JwtUtils.verifyToken(request.token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.email = request.email;
        applicationForm.flag = request.flag;
        applicationForm.objectID = request.objectID;
        applicationForm.userID = request.userID;
        applicationForm.userName = userRepository.findByUserID(request.userID).userName;
        User user = userRepository.findByUserID(applicationForm.userID);
        if (request.flag == 1){//门户
            Expert expert = expertRepository.findByExpertID(request.objectID);
            if(expert==null)
                return Result.Error("201","专家不存在");
            if (expert.isVerified==1)
                return Result.Error("201","门户已被认领");
            if (user.isVerified==1){
                return Result.Error("201","您已认领门户，请勿重复认领！");
            }
            applicationForm.objectName = expertRepository.findByExpertID(request.objectID).getName();
        }

        else if (request.flag==0){
            ES_Document es_document = es_documentDao.findByDocumentid(request.objectID);
            if(es_document==null)
                return Result.Error("201","文献不存在");
            if (user.isVerified!=1){
                return Result.Error("201","请先认领门户后再进行认领文献操作！");
            }
            applicationForm.objectName = documentRepository.findDocumentByDocumentID(request.objectID).getTitle();
        }
        else return Result.Error("201","flag参数错误");
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        applicationForm.time = dateFormat.format(date);
        applicationForm.result = 0;
        applicationRepository.save(applicationForm);
        return Result.Success();
    }
    @RequestMapping("getAll")
    public Result getAll(@RequestParam int size,boolean isAll,int flag,String token,int page){
        ArrayList<ApplicationForm> list;
        if (JwtUtils.verifyToken(token)!=0){
            return Result.Error("201","token非法，请重新登录");
        }
        if (!isAll){
            list = applicationRepository.findApplicationFormsByResultIs(0);
            list.removeIf(i -> i.flag != flag);
        }

        else {
            list = applicationRepository.findApplicationFormsByResultIs(1);
            list.addAll(applicationRepository.findApplicationFormsByResultIs(2));
            list.removeIf(i -> i.flag != flag);
        }

        int list_size = list.size();
        getAllRes data = new getAllRes();
        data.totalPages = list_size/size;
        data.page = page;
        data.totalElements = page* size<list_size? size : list_size - size*(page-1);//若为最后一页，则该页长度不是每页的个数request.size
        data.rows = list.subList((page-1)* size, (page-1)* size+data.totalElements);
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
    public static class createReq{
        public String token;
        public int userID;
        public String objectID;
        public int flag;
        public String email;
    }


    public static class getAllReq{
        public int size;
        public int page;
        public boolean isAll;
        public String token;
        public int flag;

    }
    public static class rejectReq{
        public String token;
        public int formID;
        public String reason;

    }
    public static class agreeReq{
        public String token;
        public int formID;
    }
    public static class getAllRes{
        public int totalPages;
        public int page;
        public int totalElements;
        public List rows ;
    }

}

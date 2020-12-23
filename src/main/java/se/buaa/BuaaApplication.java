package se.buaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.buaa.Controller.AcademicController;

import java.util.List;


@SpringBootApplication
public class BuaaApplication {

    public static void main(String[] args) {
        init();
        SpringApplication.run(BuaaApplication.class, args);

        /* 初始化xml文件定义的错误类型码 */
        //ErrorDict.getInstance();
    }

    private static void init() {
        List<String> typeList = AcademicController.typeListDefault;
        typeList.add("期刊");
        typeList.add("图书");
        typeList.add("专利");
        typeList.add("会议");
        typeList.add("学位");
    }
//注释
}

package se.buaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.buaa.Error.ErrorDict;


@SpringBootApplication
public class BuaaApplication {

    public static void main(String[] args) {

        SpringApplication.run(BuaaApplication.class, args);

        /* 初始化xml文件定义的错误类型码 */
        //ErrorDict.getInstance();
    }
//注释
}

package spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("spring.bean")
public class Main {
    public static void main(String[] args) {
        // 创建Spring容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        A springBean = (A) applicationContext.getBean("a");
        if (springBean == null) {
            return;
        }
        System.out.println(springBean);
        // Spring容器销毁
        applicationContext.close();
    }
}

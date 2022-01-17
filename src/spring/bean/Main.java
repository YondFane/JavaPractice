package spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("spring.bean")
public class Main {
    public static void main(String[] args) {
        // 创建Spring容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        SpringBean springBean = (SpringBean) applicationContext.getBean("springBean");
//        System.out.println();
//        System.out.println(springBean.getId());
        if (springBean == null) {
            return;
        }
        // Spring容器销毁
        applicationContext.close();
    }
}

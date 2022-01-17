package spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {


    private String id;

    public SpringBean() {
        System.out.println("SpringBean构造方法()执行");
    }
    public String getId() {
        return id;
    }
    @Value("ID")
    public void setId(String id) {
        System.out.println("SpringBean--id属性注入");
        this.id = id;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware--setBeanFactory()方法执行");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware--setBeanName()方法执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean--afterPropertiesSet()方法执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean--destroy()方法执行");
    }
}

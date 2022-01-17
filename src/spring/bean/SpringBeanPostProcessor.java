package spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("springBean".equals(beanName)) {
            System.out.println("BeanPostProcessor--postProcessBeforeInitialization()方法执行");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("springBean".equals(beanName)) {
            System.out.println("BeanPostProcessor--postProcessAfterInitialization()方法执行");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

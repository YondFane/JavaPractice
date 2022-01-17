package spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("springBean".equals(beanName)) {
            System.out.println("InstantiationAwareBeanPostProcessor--postProcessBeforeInstantiation()方法执行");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("springBean".equals(beanName)) {
            System.out.println("InstantiationAwareBeanPostProcessor--postProcessAfterInstantiation()方法执行");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("springBean".equals(beanName)) {
            System.out.println("InstantiationAwareBeanPostProcessor--postProcessProperties()方法执行");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }
}

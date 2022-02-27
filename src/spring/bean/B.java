package spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
    @Autowired
    private A a;

    public B(){}

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}

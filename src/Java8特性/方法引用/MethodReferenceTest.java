package Java8特性.方法引用;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法引用通过方法的名字来指向一个方法
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码
 * 方法引用使用一对冒号 ::
 */
public class MethodReferenceTest {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person person = new Person("ID", "Jack");
        list.add(person);
        list.add(new Person("ID", "Tom"));


        // 1、构造器引用 Class::new 或 Class< T >::new
        System.out.println("1 构造器引用");
        Person person1 = Person.create(Person::new);
        // 2、静态方法引用 Class::static_method
        System.out.println("2 静态方法引用");
        list.forEach(Person::staticToString);
        // 3、特定类的任意对象的方法引用  Class::method
        System.out.println("3 特定类的任意对象的方法引用");
        list.forEach(Person::toString);
        // 4、特定对象的方法引用
        System.out.println("4 特定对象的方法引用");
//        list.forEach(person::toString);

        // getNmae()方法返回的是String，类型与<Person>对不上报错
//        PersonInterface<Person> tempS = person::getName;
        // 不指定类型，默认为String类型，String get(){return person.getName;}
        PersonInterface s = person::getName;
        Object o = s.get();
        System.out.println(o);//Jack

    }
}

@FunctionalInterface
interface PersonInterface<T> {
    T get();
}
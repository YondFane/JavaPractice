package Java8特性.方法引用;

public class Person {
    private String id;
    private String name;

    public Person(){}

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Person create(PersonInterface<Person> p) {
        return p.get();
    }

    @Override
    public String toString() {
        System.out.println("toString()方法");
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public  void toString(Person person) {
        System.out.println("toString(Person person)方法");
        System.out.println(person.toString());
    }

    public static void staticToString(Person person) {
        System.out.println("staticToString(Person person)方法");
        System.out.println(person.toString());
    }

    public void test() {
        System.out.println("test()方法");
    }

}

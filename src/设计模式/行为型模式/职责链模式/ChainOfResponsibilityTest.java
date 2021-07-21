package 设计模式.行为型模式.职责链模式;

public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        HandleChain handleChain = new HandleChain();
        handleChain.addHandle(new HandleA());
        handleChain.addHandle(new HandleB());
        handleChain.handle();
    }
}

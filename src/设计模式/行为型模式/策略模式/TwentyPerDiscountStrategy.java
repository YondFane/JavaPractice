package 设计模式.行为型模式.策略模式;

/**
 * 八折
 */
public class TwentyPerDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculate(double sum) {
        return sum * 0.2;
    }
}

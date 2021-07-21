package 设计模式.行为型模式.策略模式;

/**
 * 正常折扣
 */
public class NormalDiscountStrategy implements DiscountStrategy{
    @Override
    public double calculate(double sum) {
        return sum;
    }
}

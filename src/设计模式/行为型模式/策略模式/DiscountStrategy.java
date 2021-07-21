package 设计模式.行为型模式.策略模式;

/**
 * 折扣接口
 */
public interface DiscountStrategy {
    /**
     * 计算结果
     * 算法逻辑方法
     * @param sum
     * @return
     */
    double calculate(double sum);
}

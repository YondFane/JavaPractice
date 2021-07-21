package 设计模式.行为型模式.策略模式;

import java.util.HashMap;

/**
 * 策略工厂
 */
public class DiscountStrategyFactory {
    private static HashMap<String, DiscountStrategy> discountStrategyHashMap = new HashMap<>();

    static {
        discountStrategyHashMap.put("NORMAL", new NormalDiscountStrategy());
        discountStrategyHashMap.put("TWENTYPER", new TwentyPerDiscountStrategy());
    }
    public static DiscountStrategy getDisCountStrategy(String type) {
        return discountStrategyHashMap.get(type);
    }
}

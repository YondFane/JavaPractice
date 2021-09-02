package Hutool.DateUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

public class DateUtilTest {
    public static void main(String[] args) {
        // 计时
        TimeInterval timer = DateUtil.timer();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        // 获取执行时间
        System.out.println(timer.interval());
    }
}

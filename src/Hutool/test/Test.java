package Hutool.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.IoUtil;

public class Test {
    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        System.out.println(timer.interval());
    }
}

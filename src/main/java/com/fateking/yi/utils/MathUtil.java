package com.fateking.yi.utils;

import java.math.BigDecimal;

public class MathUtil {

    /**
     * 和
     *
     * @param numbers
     * @return
     */
    public final static BigDecimal sum(BigDecimal... numbers) {
        if (numbers == null) {
            return null;
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal number : numbers) {
            sum = sum.add(number);
        }
        return sum;
    }

    /**
     * 平均数
     *
     * @param numbers
     * @return
     */
    public final static BigDecimal average(BigDecimal... numbers) {
        if (numbers == null) {
            return null;
        }
        int size = numbers.length;
        BigDecimal sum = sum(numbers);
        return sum.divide(BigDecimal.valueOf(size), 12, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 方差
     *
     * @param numbers
     * @return
     */
    public final static BigDecimal variance(BigDecimal... numbers) {
        if (numbers == null) {
            return null;
        }
        int size = numbers.length;
        BigDecimal var = BigDecimal.ZERO;
        BigDecimal ave = average(numbers);
        for (BigDecimal number : numbers) {
            var.add((ave.subtract(number)).pow(2));
        }
        return var.divide(BigDecimal.valueOf(size), 12, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 标准差
     *
     * @param numbers
     * @return
     */
    public final static BigDecimal deviation(BigDecimal... numbers) {
        if (numbers == null) {
            return null;
        }
        BigDecimal var = variance(numbers);
        return sqrt(var);
    }

    /**
     * 开根
     *
     * @param number
     * @return
     */
    public final static BigDecimal sqrt(BigDecimal number) {
        return BigDecimal.valueOf(Math.sqrt(number.doubleValue())).setScale(12, BigDecimal.ROUND_HALF_UP);
    }
}

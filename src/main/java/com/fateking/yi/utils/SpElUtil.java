package com.fateking.yi.utils;

import com.google.common.collect.Maps;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * @author dingxin
 */
public class SpElUtil {

    /**
     * @param SpEl
     * @param context
     * @return
     */
    public static String parse(String SpEl, Map<String, Object> context) {
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        if (context != null) {
            context.forEach((key, value) -> evaluationContext.setVariable(key, value));
        }

        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(SpEl, new TemplateParserContext()).getValue(evaluationContext, String.class);
    }


    public static void main(String[] args) {
        String spel = "https://api.huobi.pro/v1/order/orders/#{#orderId}/submitcancel";
        Map<String, Object> params = Maps.newHashMap();
        params.put("orderId", "123");
        System.err.println(">>>" + parse(spel, params));
    }
}

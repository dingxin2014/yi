package com.fateking.yi.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author dingxin
 */
public class BeanUtils {

    private static final Log LOG = LogFactory.getLog(BeanUtils.class);

    /**
     * @param s
     * @param clazz
     * @param ignoreProperties
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T convert(S s, Class<T> clazz, String... ignoreProperties) {
        return convert(s, clazz, null, ignoreProperties);
    }

    /**
     * @param s
     * @param clazz
     * @param function
     * @param ignoreProperties
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T convert(S s, Class<T> clazz, Function<T, T> function, String... ignoreProperties) {
        if (s == null) {
            return null;
        }

        try {
            T t = clazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(s, t, ignoreProperties);
            if (function != null) {
                return function.apply(t);
            } else {
                return t;
            }
        } catch (InstantiationException e) {
            LOG.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param obj
     * @param ignoreProperties
     * @return
     */
    public static Map<String, Object> toMap(Object obj, String... ignoreProperties) {
        Map<String, Object> map = Maps.newHashMap();
        if (obj == null) {
            return map;
        }
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        } catch (IntrospectionException e) {
            LOG.error(e.getMessage(), e);
        }
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        List<String> ignoreList = null;
        if (ignoreProperties != null) {
            ignoreList = Arrays.asList(ignoreProperties);
        }
        for (PropertyDescriptor pd : pds) {
            String key = pd.getName();
            if (ignoreList != null && ignoreList.contains(key)) {
                continue;
            }
            Object value = null;
            try {
                value = FieldUtils.readField(obj, pd.getName(), true);
            } catch (IllegalAccessException e) {
                LOG.error(e.getMessage(), e);
            }
            map.put(key, value);
        }
        return map;
    }

    public static final <T> T isNull(T target, T replace) {
        if (target == null) {
            return replace;
        }
        return target;
    }

}

package com.fateking.yi.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.util.Date;

@Slf4j
public class OSUtil {

    /**
     * 设置操作系统时间
     *
     * @param time
     */
    public static void setOSTime(Long time) {
        String osName = System.getProperty("os.name");
        Date date = new Date(time);
        try {
            if (osName.matches("^(?i)Windows.*$")) {        // Window 系统
                String cmd;

                cmd = " cmd /c date " + DateFormatUtils.format(date, "yyyy-MM-dd"); // 格式：yyyy-MM-dd
                Runtime.getRuntime().exec(cmd);

                cmd = " cmd /c time " + DateFormatUtils.format(date, "HH:mm:ss"); // 格式 HH:mm:ss
                Runtime.getRuntime().exec(cmd);
            } else if (osName.matches("^(?i)Linux.*$")) {   // Linux 系统
                String command = "date -s " + "\"" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss") + "\"";// 格式：yyyy-MM-dd HH:mm:ss
                Runtime.getRuntime().exec(command);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }
}

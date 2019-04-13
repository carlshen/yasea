package net.ossrs.yasea.demo;

import android.util.Log;

/**
 * 该Log的封装带有ThreadName Classname LineNumber MethodName等信息 并且处理了JSON字符串不完整的问题
 */
public class LogUtils {

    private static String TAG = "HHT-LOG";

    //可以全局控制是否打印log日志
    private static boolean isPrintLog = true;
    private static int LOG_MAX_LENGTH = 2000;

    private final static int LOG_V = 0x001;
    private final static int LOG_D = 0x002;
    private final static int LOG_I = 0x003;
    private final static int LOG_W = 0x004;
    private final static int LOG_E = 0x005;

    public static void v(String msg) {
        log(TAG, msg, LOG_V);
    }

    public static void d(String msg) {
        log(TAG, msg, LOG_D);
    }

    public static void i(String msg) {
        log(TAG, msg, LOG_I);
    }

    public static void w(String msg) {
        log(TAG, msg, LOG_W);
    }

    public static void e(String msg) {
        log(TAG, msg, LOG_E);
    }

    public static void v(String tag, String msg) {
        log(tag, msg, LOG_V);
    }

    public static void d(String tag, String msg) {
        log(tag, msg, LOG_D);
    }

    public static void i(String tag, String msg) {
        log(tag, msg, LOG_I);
    }

    public static void w(String tag, String msg) {
        log(tag, msg, LOG_W);
    }

    public static void e(String tag, String msg) {
        log(tag, msg, LOG_E);
    }


    public static void log(String tagName, String msg, int type) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAX_LENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    switchLog(tagName, i, msg, start, end, type);
                    start = end;
                    end = end + LOG_MAX_LENGTH;
                } else {
                    switchLog(tagName, i, msg, start, strLength, type);
                    break;
                }
            }
        }
    }

    private static void switchLog(String tagName, int i, String msg, int start, int end, int type) {
        String clsInfo = getFunctionName();
        switch (type) {
            case LOG_V:
                Log.v(tagName + i, clsInfo + " - " + msg.substring(start, end));
                break;
            case LOG_D:
                Log.d(tagName + i, clsInfo + " - " + msg.substring(start, end));
                break;
            case LOG_I:
                Log.i(tagName + i, clsInfo + " - " + msg.substring(start, end));
                break;
            case LOG_W:
                Log.w(tagName + i, clsInfo + " - " + msg.substring(start, end));
                break;
            case LOG_E:
                Log.e(tagName + i, clsInfo + " - " + msg.substring(start, end));
                break;
        }
    }

    /**
     * @return
     * @TODO Get The Current Function Name
     * @author Jinfu.pi
     * @Created May 21, 2015 6:09:08 PM
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(LogUtils.class.getName())) {
                continue;
            }
            return "[ClassName---" + st.getFileName() + ";  LineNumber---" + st.getLineNumber() + ";  MethodName---" + st.getMethodName() + "()]";
        }
        return null;
    }

}

package src.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by z on 2018/9/11.
 */
public class StringUtils {
    public static boolean isBlank(String str) {
        return str == null || str.trim().length()<1;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    public static String getInfo(HttpServletRequest request,String key) {
        Object obj = request.getAttribute("info");
        if (obj != null && obj instanceof Map) {
            Map<String,String> map = (Map<String,String>)obj;
            if (map.containsKey(key))
                return map.get(key);
        }
        return "";
    }

    public static String getName(HttpServletRequest request,String str) {
        String name = request.getParameter(str);
        if(name!=null)
            return name;
        return "";
    }

}

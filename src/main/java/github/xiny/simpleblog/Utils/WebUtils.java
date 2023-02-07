package github.xiny.simpleblog.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class WebUtils {
    public static String getRequestIP(HttpServletRequest request) {
        String UNKNOWN = "unknown";
        String LOCALHOST = "127.0.0.1";
        String SEPARATOR = ",";
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (LOCALHOST.equals(ipAddress)) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(SEPARATOR) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
    /**  将一个request请求所携带的参数封装成map返回 ，带集合的 */
    public static Map<String, Object> getParamsMap2(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String[]> parameterMap = request.getParameterMap();	// 获取所有参数
        for (String key : parameterMap.keySet()) {
            try {
                String[] values = parameterMap.get(key); // 获得values
                if(values.length == 1) {
                    map.put(key, values[0]);
                } else {
                    List<String> list = new ArrayList<String>();
                    for (String v : values) {
                        list.add(v);
                    }
                    map.put(key, list);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    /**
     * 将一个request请求Header所携带的参数封装成map返回
     */
    public static Map<String,String>getHeaderMap(HttpServletRequest request){
        Map<String,String>map=new HashMap<String, String>();
        try {
            Enumeration<String> paramNames = request.getHeaderNames();//获得K列表
            request.setCharacterEncoding("UTF-8");
            while (paramNames.hasMoreElements()) {
                try {
                    String key =paramNames.nextElement();	//获得k
                    String value=request.getHeader(key);	//获得v
                    if(request.getMethod().equals("GET")){
                        new String(value.getBytes("ISO-8859-1"),"UTF-8");
                    }
                    map.put(key,value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return map;
    }

}

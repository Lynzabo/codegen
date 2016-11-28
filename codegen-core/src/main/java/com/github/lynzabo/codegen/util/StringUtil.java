package com.github.lynzabo.codegen.util;

import org.apache.commons.lang.StringUtils;

/**
 *  字符串操作工具类
 * @author zxwu
 */
public final class StringUtil {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public static String toJoin(String[] strs,String joinStr,String connStr,String headStr,String endStr){
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<strs.length;i++){
            if(!org.apache.commons.lang.StringUtils.isEmpty(headStr))
                if(0 == i)
                    sb.append(headStr);
            if(!StringUtils.isEmpty(joinStr))
                sb.append(joinStr);
            sb.append(strs[i]);
            if(!StringUtils.isEmpty(joinStr))
                sb.append(joinStr);
            if(strs.length-1 > i)
                sb.append(connStr);
            if(!org.apache.commons.lang.StringUtils.isEmpty(headStr))
                if(strs.length-1 == i)
                    sb.append(endStr);
        }
        return sb.toString();
    }

    /**
     * 转换下划线格式字符串为驼峰命名
     * @param _name
     * @param firstNameIsUpper  首字母是否大写
     * @return
     */
    public static String underline2Camel(String _name,boolean firstNameIsUpper){
        String camelStr = "";
        _name = _name.toLowerCase();
        if (!StringUtils.contains(_name, "_")) {
            return _name;
        }
        StringBuilder sb = new StringBuilder();
        char[] cArr = _name.trim().toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            if (c == '_') {
                i++;
                sb.append(Character.toUpperCase(cArr[i]));
            } else {
                sb.append(c);
            }
        }
        camelStr = (firstNameIsUpper ? sb.substring(0, 1).toUpperCase():sb.substring(0, 1).toLowerCase()) + sb.substring(1);
        return camelStr;
    }

    /**
     * 修改字符串第一个字母为大写
     * @param string
     * @return
     */
    public static String firstUpper(String string) {
        String str = string.substring(0, 1).toUpperCase() + string.substring(1);
        return str;
    }

    /**
     * 字符串截断1，并修改第一个字符为小写
     * 如字符串aSDfg，经该接口后变成sDfg。
     * @param string
     * @return
     */
    public static String sub1Upper(String string) {
        String sr = string.substring(1);
        sr = sr.substring(0,1).toLowerCase() + sr.substring(1);
        return sr;
    }

    /**
     * 字符串首字符小写
     * @param sr
     * @return
     */
    public static String firstLower(String sr) {
        sr = sr.substring(0,1).toLowerCase() + sr.substring(1);
        return sr;
    }
    /*public static final char UNDERLINE='_';
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }*/
}

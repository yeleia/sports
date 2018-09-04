package org.wingstudio.sports.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
       String s="xgei-1/2/1";
       int count=pattern(s);
       if (count==0){
           System.out.println("没有");
       }else if (count==1||count==2){
           int index=s.indexOf("/");
           System.out.println(s.substring(index-1,index));
       }else {
           int index=s.indexOf("/");
           System.out.println(s.substring(s.indexOf("/")+1,s.indexOf("/",index+1)));
       }

    }
    public static int pattern(String text) {
        // 根据指定的字符构建正则
        Pattern pattern = Pattern.compile("/");
        // 构建字符串和正则的匹配
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        // 循环依次往下匹配
        while (matcher.find()){ // 如果匹配,则数量+1
            count++;
        }
        return  count;
    }
}

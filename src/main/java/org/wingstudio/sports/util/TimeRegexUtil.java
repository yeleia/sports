package org.wingstudio.sports.util;

public class TimeRegexUtil {
    //如果是时间排序
    public static boolean TimeRegex(String inMax,String inMin,String record,String twoLevel){
         if ((inMax==null||inMax!=null&&regex(inMax))||(inMin==null||inMin!=null&&regex(inMin))||
                 (record==null||record!=null&&regex(record))||(twoLevel==null||twoLevel!=null&&regex(twoLevel))){
             return true;
         }else {
             return false;
         }
    }
    //时间正则表达式
    public static boolean regex(String str){
        String regex="\\d+[:]\\d+[.]\\d+";
        String regex1="\\d+[.]\\d+";
        return str.matches(regex)||str.matches(regex1);
    }
}

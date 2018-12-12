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
        //分，秒，毫秒
        String regex="\\d+[:]\\d+[.]\\d+";
        //秒，毫秒
        String regex1="\\d+[.]\\d+";
        //时，分，秒
        String regex2="\\d+[:]\\d+[:]\\d+";
        return str.matches(regex)||str.matches(regex1)||str.matches(regex2);
    }

    public static void main(String[] args) {
        //System.out.println(regex("2:25:13"));
        System.out.println(TimeRegex("12.7","9.6","9.7","9.8"));
    }
}

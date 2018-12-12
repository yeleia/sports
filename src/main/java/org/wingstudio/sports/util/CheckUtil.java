package org.wingstudio.sports.util;

import org.wingstudio.sports.constant.Common;

/**
 * 输入成绩的大小限制
 */
public class CheckUtil {
    /**
     * @param sortrule 排序方式，0：距离排序，1：时间排序
     * @param inmax 最好成绩
     * @param inmin 最差成绩
     * @return
     */
    public static boolean incheck(int sortrule,String inmax,String inmin){
        double max=convert(inmax);
        double min=convert(inmin);
        //时间排序
        if (sortrule==1){
            if (TimeRegexUtil.regex(inmax)&&TimeRegexUtil.regex(inmin)&&max>min){
                return false;
            }else{
                return true;
            }

        }else {
            //距离排序
            if (max<min){
                return false;
            }else {
                return true;
            }
        }
    }
    public static double convert(String string){
        String str=string;
        if (string.contains(":")){
           str=string.replace(":","");
        }
        return Double.valueOf(str);
    }

    /**
     * 检查成绩录入时的输入
     * @param sortrule 0：距离排序，1：时间排序
     * @param inMax
     * @param inMin
     * @return
     */
    public static boolean checkScore(int sortrule,String inScore,String inMax,String inMin){
        double scores=convert(inScore);
        double max=convert(inMax);
        double min=convert(inMin);
        if (sortrule==1){
            if (TimeRegexUtil.regex(inScore)&&max<=scores&&min>=scores){
                return true;
            }else {
                return false;
            }
        }else {
            if (scores>min&&scores<max){
                return true;
            }else {
                return false;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(incheck(1,"30:25:35","30:25:36"));
        System.out.println(checkScore(0,"12.3","13.7","9.4"));
    }


}

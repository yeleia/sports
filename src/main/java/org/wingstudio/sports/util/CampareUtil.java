package org.wingstudio.sports.util;

import org.wingstudio.sports.constant.Common;

public class CampareUtil {
    /**
     * 比较二级运动员标准
     * @param score
     * @param twoLevel
     * @param method
     * @return
     */
    public static boolean eqTwoLevel(String score,String twoLevel,int method){
        if (method==1) {
            if (convert(score) <= convert(twoLevel)) {
                return true;
            } else {
                return false;
            }
        }else {
            if (Double.valueOf(score)>=Double.valueOf(twoLevel)){
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * 比较是否破纪录
     * @param score
     * @param record
     * @param method
     * @return
     */
    public static boolean eqRecord(String score,String record,int method){
        if (method==Common.TIMESORT){
            if (convert(score)<convert(record)){
                return true;
            }else {
                return false;
            }
        }else {
            if (Double.valueOf(score)>Double.valueOf(record)){
                return true;
            }else {
                return false;
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
}

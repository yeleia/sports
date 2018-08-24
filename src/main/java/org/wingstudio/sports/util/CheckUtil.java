package org.wingstudio.sports.util;

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
            if (max>min){
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

    public static void main(String[] args) {
        System.out.println(incheck(0,"30.4","30.5"));
    }

}

package org.wingstudio.sports.util;

import org.wingstudio.sports.VO.PreSoloVO;
import org.wingstudio.sports.VO.SoloVO;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Solo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowCountUtil {
    /**
     * 查询预赛单项排序
     * @param preSolos
     * @param sortrule   0 距离排序，1 时间排序
     * @return
     */
    public static List<PreSoloVO> countPreSolo(List<PreSolo> preSolos, Integer sortrule) {
        List<PreSoloVO> preSoloVOS=new ArrayList<>();
        //先排序后复制
        if (sortrule==0){

            Collections.sort(preSolos, new Comparator<PreSolo>() {
                @Override
                public int compare(PreSolo o1, PreSolo o2) {
                    if (convert(o1.getScore())<=convert(o2.getScore())){
                        return 1;
                    }else {
                        return -1;
                    }

                }
            });
        }else {
            Collections.sort(preSolos, new Comparator<PreSolo>() {
                @Override
                public int compare(PreSolo o1, PreSolo o2) {
                    if (convert(o1.getScore())>=convert(o2.getScore())){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            });
        }
        //计算名次
        int n=1;
        for (int i = 0; i <preSolos.size() ; i++) {
            if (i>0&&preSolos.get(i).getScore().equals(preSolos.get(i-1).getScore())){
                n--;
                preSolos.get(i).setTemp(n);
            }else {
                preSolos.get(i).setTemp(n);
                n++;
            }
        }
        for (int i = 0; i <preSolos.size() ; i++) {
            PreSoloVO preSoloVO=new PreSoloVO();
            preSoloVO.setScore(preSolos.get(i).getScore());
            preSoloVO.setRemark(preSolos.get(i).getRemark());
            preSoloVO.setGroups(preSolos.get(i).getGroups());
            preSoloVO.setGate(preSolos.get(i).getGate());
            preSoloVO.setRank(preSolos.get(i).getTemp());
            preSoloVO.setContestantid(preSolos.get(i).getContestantid());
            preSoloVOS.add(preSoloVO);
            if (preSolos.get(i).getTemp()==10){
                break;
            }
        }
        return preSoloVOS;
    }
    public static double convert(String string){
        String str=string;
        if (string.contains(":")){
            str=string.replace(":","");
        }
        return Double.valueOf(str);
    }
    /**
     * 查询决赛单项排序
     * @param solos
     * @param sortrule   0 距离排序，1 时间排序
     * @return
     */
    public static List<SoloVO> countSolo(List<Solo> solos, Integer sortrule) {
        List<SoloVO> soloVOS=new ArrayList<>();
        //先排序后复制
        if (sortrule==0){
            Collections.sort(solos, new Comparator<Solo>() {
                @Override
                public int compare(Solo o1, Solo o2) {
                    if (convert(o1.getScore())<=convert(o2.getScore())){
                        return 1;
                    }else {
                        return -1;
                    }

                }
            });
        }else {
            Collections.sort(solos, new Comparator<Solo>() {
                @Override
                public int compare(Solo o1, Solo o2) {
                    if (convert(o1.getScore())>=convert(o2.getScore())){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            });
        }
        //计算名次
        int n=1;
        for (int i = 0; i <solos.size() ; i++) {
            if (i>0&&solos.get(i).getScore().equals(solos.get(i-1).getScore())){
                n--;
                solos.get(i).setTemp(n);
            }else {
                solos.get(i).setTemp(n);
                n++;
            }
        }
        for (int i = 0; i <solos.size() ; i++) {
            SoloVO SoloVO=new SoloVO();
            SoloVO.setScore(solos.get(i).getScore());
            SoloVO.setRemark(solos.get(i).getRemark());
            SoloVO.setGate(solos.get(i).getGate());
            SoloVO.setRank(solos.get(i).getTemp());
            SoloVO.setContestantid(solos.get(i).getContestantid());
            soloVOS.add(SoloVO);
            if (solos.get(i).getTemp()==10){
                break;
            }
        }
        return soloVOS;
    }
}

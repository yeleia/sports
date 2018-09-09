package org.wingstudio.sports.util;

import org.wingstudio.sports.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountUtil {


    public static double convert(String string){
        String str=string;
        if (string.contains(":")){
            str=string.replace(":","");
        }
        return Double.valueOf(str);
    }

    public static void main(String[] args) {
        List<PreSolo> solos=new ArrayList<>();
        List<PreRole> roles=new ArrayList<>();
        PreSolo solo=new PreSolo();
        solo.setSportid(4);
        solo.setContestantid(2);
        solo.setTaketime("23");
        solo.setScore("1:22");
        solos.add(solo);
        PreSolo solo1=new PreSolo();
        solo1.setScore("1:21");
        solo1.setSportid(4);
        solo1.setContestantid(2);
        solo1.setTaketime("23");
        solos.add(solo1);
        PreSolo solo2=new PreSolo();
        solo2.setScore("1:21");
        solo2.setSportid(4);
        solo2.setContestantid(2);
        solo2.setTaketime("23");
        solos.add(solo2);
        PreSolo solo3=new PreSolo();
        solo3.setScore("1:21");
        solo3.setSportid(4);
        solo3.setContestantid(2);
        solo3.setTaketime("23");
        solos.add(solo3);
        PreRole role=new PreRole();
        role.setAddscore(5.0);
        role.setRank(0);
        roles.add(role);
        PreRole role1=new PreRole();
        role1.setAddscore(4.0);
        role1.setRank(1);
        roles.add(role1);

        System.out.println(count(solos,roles).get(3).toString());

    }
    /**
     * 计算预赛加分,注意分数相等的情况，如果两个成绩相等，则两个成绩的加分是两个等级加分的一半
     * @param solos 为成绩
     * @param roles 为加分规则
     * @return
     */
    public static List<Score> count(List<PreSolo> solos, List<PreRole> roles) {
        int n=0;
        /**
         * 设置临时变量为temp=对应名次
         */
        for (int i=0;i<solos.size();i++){
            if (i>0&&solos.get(i).getScore().equals(solos.get(i-1).getScore())){
                    solos.get(i).setTemp(n);
            }else {
                solos.get(i).setTemp(i);
                n=i;
            }
        }
        List<PreSolo> preSolos=new ArrayList<>();//在加分范围内
        List<PreSolo> preSolos1=new ArrayList<>();//不在加分范围内
        Score score[]=new Score[solos.size()];
        for (int i=0;i<solos.size();i++){
            score[i]=new Score();
        }
        for (int i=preSolos.size();i<solos.size();i++){
            score[i].setPresoloscore(0.0);
        }
        int roleSize=roles.size();
        for (int j=0;j<solos.size();j++){
            if (solos.get(j).getTemp()<=roleSize){
                preSolos.add(solos.get(j));
            }else {
                preSolos1.add(solos.get(j));
            }
        }
        //确保presolos的个数和role的个数一样
        if (preSolos.size()>roleSize){
            for (int i=roleSize;i<preSolos.size();i++){
                PreRole preRole=new PreRole();
                preRole.setRank(i+1);
                preRole.setAddscore(0.0);
                roles.add(preRole);
            }
        }
        int count=0;
        double grade=0;
        double temp=0;
        for (int j=0;j<preSolos.size();j++){
            if (j>0&&preSolos.get(j).getTemp().equals(preSolos.get(j-1).getTemp())){
                grade=grade+roles.get(j).getAddscore();
                count=count+1;
                if (j==preSolos.size()-1){
                    for (int m=j-count+1;m<=j;m++){
                        score[m].setPresoloscore(grade/(count));
                    }
                }
            }else {
                if (count!=0){
                    temp=grade/(count);

                    for (int i=j-count;i<j;i++){
                        score[i].setPresoloscore(temp);
                    }
                    count=0;
                    grade=0;
                }else {
                    if (j!=0){
                        score[j-1].setPresoloscore(roles.get(j-1).getAddscore());
                    }
                    //如果是最后一个
                    if (j==preSolos.size()-1){
                        score[j].setPresoloscore(roles.get(j).getAddscore());
                    }
                }
                grade=grade+roles.get(j).getAddscore();
                count=count+1;
            }
        }

        for (int j=0;j<solos.size();j++){
            score[j].setTaketime(solos.get(j).getTaketime());
            score[j].setSportid(solos.get(j).getSportid());
            score[j].setContestantid(solos.get(j).getContestantid());
        }
        return Arrays.asList(score);
    }
    public static List<Score> countSolo(List<Solo> solos, List<Role> roles) {
        int n=0;
        /**
         * 设置临时变量为temp=对应名次
         */
        for (int i=0;i<solos.size();i++){
            if (i>0&&solos.get(i).getScore().equals(solos.get(i-1).getScore())){
                solos.get(i).setTemp(n);
            }else {
                solos.get(i).setTemp(i);
                n=i;
            }
        }
        List<Solo> preSolos=new ArrayList<>();//在加分范围内
        List<Solo> preSolos1=new ArrayList<>();//不在加分范围内

        Score score[]=new Score[solos.size()];
        for (int i=0;i<solos.size();i++){
            score[i]=new Score();
        }
        for (int i=preSolos.size();i<solos.size();i++){
            score[i].setSoloscore(0.0);
        }
        int roleSize=roles.size();
        for (int j=0;j<solos.size();j++){
            if (solos.get(j).getTemp()<=roleSize){
                preSolos.add(solos.get(j));
            }else {
                preSolos1.add(solos.get(j));
            }
        }
        //确保presolos的个数和role的个数一样
        if (preSolos.size()>roleSize){
            for (int i=roleSize;i<preSolos.size();i++){
                Role preRole=new Role();
                preRole.setRank(i+1);
                preRole.setAddscore(0.0);
                roles.add(preRole);
            }
        }
        int count=0;
        double grade=0;
        double temp=0;
        for (int j=0;j<preSolos.size();j++){
            if (j>0&&preSolos.get(j).getTemp().equals(preSolos.get(j-1).getTemp())){
                grade=grade+roles.get(j).getAddscore();
                count=count+1;
                if (j==preSolos.size()-1){
                    for (int m=j-count+1;m<=j;m++){
                        score[m].setSoloscore(grade/(count));
                    }
                }
            }else {
                if (count!=0){
                    temp=grade/(count);

                    for (int i=j-count;i<j;i++){
                        score[i].setSoloscore(temp);
                    }
                    count=0;
                    grade=0;
                }else {
                    if (j!=0){
                        score[j-1].setSoloscore(roles.get(j-1).getAddscore());
                    }
                    //如果是最后一个
                    if (j==preSolos.size()-1){
                        score[j].setSoloscore(roles.get(j).getAddscore());
                    }
                }
                grade=grade+roles.get(j).getAddscore();
                count=count+1;
            }
        }

        for (int j=0;j<solos.size();j++){
            score[j].setTaketime(solos.get(j).getTaketime());
            score[j].setSportid(solos.get(j).getSportid());
            score[j].setContestantid(solos.get(j).getContestantid());
        }
        return Arrays.asList(score);
    }
    public static List<TeamScore> countTeam(List<TeamScore> solos, List<TeamRole> roles) {
        for (int i = 0; i < solos.size(); i++) {
            for(int j=0;j<roles.size();j++){
                if (solos.get(i).getScore().equals(roles.get(j).getRank())){
                    solos.get(i).setFinalscore(Double.valueOf(roles.get(j).getScore()));
                }
            }
        }
        return solos;
    }
}



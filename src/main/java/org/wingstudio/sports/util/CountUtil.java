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
        List<Solo> solos=new ArrayList<>();
        List<Role> roles=new ArrayList<>();
        Solo solo=new Solo();
        solo.setSportid(4);
        solo.setContestantid(1);
        solo.setTaketime("23");
        solo.setScore("2:1:5");
        solos.add(solo);
        Solo solo1=new Solo();
        solo1.setScore("2:1:6");
        solo1.setSportid(4);
        solo1.setContestantid(2);
        solo1.setTaketime("23");
        solos.add(solo1);
        Solo solo2=new Solo();
        solo2.setScore("2:1:7");
        solo2.setSportid(4);
        solo2.setContestantid(3);
        solo2.setTaketime("23");
        solos.add(solo2);
        Solo solo3=new Solo();
        solo3.setScore("2:1:8");
        solo3.setSportid(4);
        solo3.setContestantid(4);
        solo3.setTaketime("23");
        solos.add(solo3);
        Role role=new Role();
        role.setAddscore(5.0);
        role.setRank(1);
        roles.add(role);
        Role role1=new Role();
        role1.setAddscore(4.0);
        role1.setRank(2);
        roles.add(role1);
        List<Score> scores=countSolo(solos,roles);
        for (int i = 0; i <scores.size() ; i++) {
            System.out.println(scores.get(i).getContestantid()+"...."+scores.get(i).getPresoloscore());
        }
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
    public static List<TeamScore> countTeam(List<TeamScore> solos, List<TeamRole> roles) {
        /*for (int i = 0; i < solos.size(); i++) {
            for(int j=0;j<roles.size();j++){
                if (solos.get(i).getScore().equals(roles.get(j).getRank())){
                    solos.get(i).setFinalscore(Double.valueOf(roles.get(j).getScore()));
                }
            }
        }*/
        for (int i = 0; i <roles.size() ; i++) {
            for (int j = 0; j <solos.size() ; j++) {
                System.out.println(solos.get(j).getScore()+"---"+roles.get(i).getRank());
                if (solos.get(j).getScore().equals(String.valueOf(roles.get(i).getRank()))){
                    System.out.println("j");
                    solos.get(j).setFinalscore(Double.valueOf(roles.get(i).getScore()));
                }
            }
        }
        return solos;
    }

  /*  public static void main(String[] args) {
        List<TeamScore> scores=new ArrayList<>();
        TeamScore teamScore=new TeamScore();
        teamScore.setTeamid(1);
        teamScore.setScore("1");
        TeamScore teamScore2=new TeamScore();
        teamScore2.setTeamid(2);
        teamScore2.setScore("2");
        TeamScore teamScore3=new TeamScore();
        teamScore3.setTeamid(2);
        teamScore3.setScore("2");
        scores.add(teamScore);
        scores.add(teamScore2);
        scores.add(teamScore3);
        List<TeamRole> teamRoles=new ArrayList<>();
        TeamRole teamRole=new TeamRole();
        teamRole.setRank(1);
        teamRole.setScore(5);
        teamRoles.add(teamRole);
        TeamRole teamRole1=new TeamRole();
        teamRole1.setRank(2);
        teamRole1.setScore(3);
        teamRoles.add(teamRole1);
        List<TeamScore> teamScores=countTeam(scores,teamRoles);
        for (int i = 0; i <teamScores.size() ; i++) {
            System.out.println(teamScores.get(i).getTeamid()+"-----"+teamScores.get(i).getFinalscore());
        }

    }*/
}



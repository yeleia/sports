package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModelProperty;

public class Score {
    private Integer id;
    @ApiModelProperty("体育项目id")
    private Integer sportid;
    @ApiModelProperty("参赛人员id")
    private Integer contestantid;
    @ApiModelProperty("预赛成绩")
    private Double presoloscore;
    @ApiModelProperty("决赛成绩")
    private Double soloscore;
    @ApiModelProperty("最终成绩")
    private Double finalscore;
    @ApiModelProperty("届数")
    private String taketime;

    public Score(Integer id, Integer sportid, Integer contestantid, Double presoloscore, Double soloscore, Double finalscore, String taketime) {
        this.id = id;
        this.sportid = sportid;
        this.contestantid = contestantid;
        this.presoloscore = presoloscore;
        this.soloscore = soloscore;
        this.finalscore = finalscore;
        this.taketime = taketime;
    }

    public Score() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSportid() {
        return sportid;
    }

    public void setSportid(Integer sportid) {
        this.sportid = sportid;
    }

    public Integer getContestantid() {
        return contestantid;
    }

    public void setContestantid(Integer contestantid) {
        this.contestantid = contestantid;
    }

    public Double getPresoloscore() {
        return presoloscore;
    }

    public void setPresoloscore(Double presoloscore) {
        this.presoloscore = presoloscore;
    }

    public Double getSoloscore() {
        return soloscore;
    }

    public void setSoloscore(Double soloscore) {
        this.soloscore = soloscore;
    }

    public Double getFinalscore() {
        return finalscore;
    }

    public void setFinalscore(Double finalscore) {
        this.finalscore = finalscore;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime == null ? null : taketime.trim();
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", sportid=" + sportid +
                ", contestantid=" + contestantid +
                ", presoloscore=" + presoloscore +
                ", soloscore=" + soloscore +
                ", finalscore=" + finalscore +
                ", taketime='" + taketime + '\'' +
                '}';
    }
}
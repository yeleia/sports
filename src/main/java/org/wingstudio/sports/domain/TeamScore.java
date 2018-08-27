package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("集体项目成绩")
public class TeamScore {
    private Integer id;
    @ApiModelProperty("体育项目id")
    private Integer sportid;
    @ApiModelProperty("集体信息")
    private Integer teamid;
    @ApiModelProperty("成绩")
    private String score;
    @ApiModelProperty("加分")
    private Double finalscore;
    @ApiModelProperty("届数")
    private String taketime;
    @ApiModelProperty("是否审核")
    private Integer checked;

    public TeamScore(Integer id, Integer sportid, Integer teamid, String score, Double finalscore, String taketime, Integer checked) {
        this.id = id;
        this.sportid = sportid;
        this.teamid = teamid;
        this.score = score;
        this.finalscore = finalscore;
        this.taketime = taketime;
        this.checked = checked;
    }

    public TeamScore() {
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

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
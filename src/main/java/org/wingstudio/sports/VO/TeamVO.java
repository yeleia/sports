package org.wingstudio.sports.VO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("集体成绩视图")
public class TeamVO {
    @ApiModelProperty("teamScoreId")
    private Integer id;
    @ApiModelProperty("项目名")
    private String sportname;
    @ApiModelProperty("学院")
    private String classes;
    @ApiModelProperty("专业")
    private String profession;
    @ApiModelProperty("成绩")
    private String score;
    @ApiModelProperty("奖项")
    private String rank;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TeamVO{" +
                "id=" + id +
                ", sportname='" + sportname + '\'' +
                ", classes='" + classes + '\'' +
                ", profession='" + profession + '\'' +
                ", score='" + score + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}

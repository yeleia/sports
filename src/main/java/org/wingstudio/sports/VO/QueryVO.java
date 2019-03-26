package org.wingstudio.sports.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询单项VO")
public class QueryVO {
    @ApiModelProperty("姓名")
    private String stuName;
    @ApiModelProperty("体育项目")
    private String sportname;
    @ApiModelProperty("成绩")
    private String score;
    @ApiModelProperty("校区")
    private String campus;
    @ApiModelProperty("学院")
    private String classes;
    //赛制
    @ApiModelProperty("预赛还是决赛")
    private String nature;
    @ApiModelProperty("排名")
    private String rank;

    public String getStuName() {
        return stuName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }



    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getNature() {
        return nature;
    }

    @Override
    public String toString() {
        return "QueryVO{" +
                "stuName='" + stuName + '\'' +
                ", sportname='" + sportname + '\'' +
                ", score='" + score + '\'' +
                ", campus='" + campus + '\'' +
                ", classes='" + classes + '\'' +
                ", nature='" + nature + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}

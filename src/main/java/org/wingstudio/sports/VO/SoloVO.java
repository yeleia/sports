package org.wingstudio.sports.VO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("决赛单项视图")
public class SoloVO {
    @ApiModelProperty("soloId")
    private Integer id;
    @ApiModelProperty("项目名")
    private String sportname;
    @ApiModelProperty("参赛人id")
    private Integer contestantid;
    @ApiModelProperty("参赛人员姓名")
    private String stuname;
    @ApiModelProperty("参赛人员学号")
    private String stunumber;
    @ApiModelProperty("学院")
    private String classes;
    @ApiModelProperty("专业")
    private String profession;
    @ApiModelProperty("道次")
    private Integer gate;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("名次")
    private Integer rank;

    @ApiModelProperty("成绩")
    private String score;

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

    public Integer getContestantid() {
        return contestantid;
    }

    public void setContestantid(Integer contestantid) {
        this.contestantid = contestantid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
    }

    public Integer getGate() {
        return gate;
    }

    public void setGate(Integer gate) {
        this.gate = gate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "SoloVO{" +
                "id=" + id +
                ", sportname='" + sportname + '\'' +
                ", contestantid=" + contestantid +
                ", stuname='" + stuname + '\'' +
                ", stunumber='" + stunumber + '\'' +
                ", classes='" + classes + '\'' +
                ", profession='" + profession + '\'' +
                ", gate=" + gate +
                ", remark='" + remark + '\'' +
                ", rank=" + rank +
                ", score='" + score + '\'' +
                '}';
    }
}

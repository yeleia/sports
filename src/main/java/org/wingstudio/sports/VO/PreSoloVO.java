package org.wingstudio.sports.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("预赛成绩视图")
public class PreSoloVO {
    @ApiModelProperty("preSoloId")
    private Integer id;
    @ApiModelProperty("项目名")
    private String sportname;
    @ApiModelProperty("参赛人id")
    private Integer contestantid;
    @ApiModelProperty("参赛人员姓名")
    private String stuname;
    @ApiModelProperty("参赛人员学号")
    private String stunumber;
    @ApiModelProperty("道次")
    private Integer gate;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("小组")
    private Integer group;
    @ApiModelProperty("成绩")
    private String score;

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

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

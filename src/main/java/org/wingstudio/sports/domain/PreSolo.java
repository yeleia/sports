package org.wingstudio.sports.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("添加预赛成绩")
public class PreSolo {
    private Integer id;
    @ApiModelProperty("体育项目id")
    private Integer sportid;
    @ApiModelProperty("参赛人id")
    private Integer contestantid;
    @ApiModelProperty("道次")
    private Integer gate;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("小组")
    private Integer groups;
    @ApiModelProperty("成绩")
    private String score;
    @ApiModelProperty("届数")
    private String taketime;
    @ApiModelProperty("审核")
    private Integer checked;
    @ApiModelProperty("校区")
    private String campus;
    @ApiModelProperty("学院")
    private String classes;
    @ApiModelProperty("计算临时属性")
    private Integer temp;

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

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "PreSolo{" +
                "id=" + id +
                ", sportid=" + sportid +
                ", contestantid=" + contestantid +
                ", gate=" + gate +
                ", remark='" + remark + '\'' +
                ", groups=" + groups +
                ", score='" + score + '\'' +
                ", taketime='" + taketime + '\'' +
                ", checked=" + checked +
                ", campus='" + campus + '\'' +
                ", classes='" + classes + '\'' +
                ", temp=" + temp +
                '}';
    }
}
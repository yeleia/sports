package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("决赛单项成绩")
public class Solo {
    private Integer id;
    @ApiModelProperty("体育项目id")
    private Integer sportid;
    @ApiModelProperty("参赛人员")
    private Integer contestantid;
    @ApiModelProperty("道次")
    private Integer gate;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("成绩")
    private String score;
    @ApiModelProperty("届数")
    private String taketime;
    @ApiModelProperty("是否审核")
    private Integer checked;
    @ApiModelProperty("校区")
    private String campus;
    @ApiModelProperty("学院")
    private String classes;
    @ApiModelProperty("计算临时变量")
    private Integer temp;

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Solo() {
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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

    @Override
    public String toString() {
        return "Solo{" +
                "id=" + id +
                ", sportid=" + sportid +
                ", contestantid=" + contestantid +
                ", gate=" + gate +
                ", remark='" + remark + '\'' +
                ", score='" + score + '\'' +
                ", taketime='" + taketime + '\'' +
                ", checked=" + checked +
                ", campus='" + campus + '\'' +
                ", classes='" + classes + '\'' +
                ", temp=" + temp +
                '}';
    }
}
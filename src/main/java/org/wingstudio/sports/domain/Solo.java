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

    public Solo(Integer id, Integer sportid, Integer contestantid, Integer gate, String remark, String score, String taketime, Integer checked) {
        this.id = id;
        this.sportid = sportid;
        this.contestantid = contestantid;
        this.gate = gate;
        this.remark = remark;
        this.score = score;
        this.taketime = taketime;
        this.checked = checked;
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
}
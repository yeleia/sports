package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNullApi;

@ApiModel("校记录")
public class Record {
    private Integer id;
    @ApiModelProperty("参赛者信息")
    private Integer contestantid;
    @ApiModelProperty("体育项目信息")
    private Integer sportid;
    @ApiModelProperty("预赛或是决赛")
    private Integer mark;
    @ApiModelProperty("届数")
    private String taketime;

    public Record(Integer id, Integer contestantid, Integer sportid, Integer mark, String taketime) {
        this.id = id;
        this.contestantid = contestantid;
        this.sportid = sportid;
        this.mark = mark;
        this.taketime = taketime;
    }

    public Record() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContestantid() {
        return contestantid;
    }

    public void setContestantid(Integer contestantid) {
        this.contestantid = contestantid;
    }

    public Integer getSportid() {
        return sportid;
    }

    public void setSportid(Integer sportid) {
        this.sportid = sportid;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime == null ? null : taketime.trim();
    }
}
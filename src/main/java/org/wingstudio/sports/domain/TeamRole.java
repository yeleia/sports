package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.ModelAttribute;

@ApiModel("集体项目加分")
public class TeamRole {
    private Integer id;

    private Integer sportid;

    private String sportname;
    @ApiModelProperty ("几等奖")
    private Integer rank;
    @ApiModelProperty("加分")
    private Integer score;
    @ApiModelProperty("校区")
    private String campus;

    public TeamRole(Integer id, Integer sportid, String sportname, Integer rank, Integer score, String campus) {
        this.id = id;
        this.sportid = sportid;
        this.sportname = sportname;
        this.rank = rank;
        this.score = score;
        this.campus = campus;
    }

    public TeamRole() {
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

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname == null ? null : sportname.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus == null ? null : campus.trim();
    }
}
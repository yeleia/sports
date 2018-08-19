package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("决赛加分规则")
public class Role {
    private Integer id;

    private Integer sportid;
    @ApiModelProperty("项目名")
    private String sportname;
    @ApiModelProperty("名次")
    private Integer rank;
    @ApiModelProperty("加分")
    private Double addscore;

    public Role(Integer id, Integer sportid, String sportname, Integer rank, Double addscore) {
        this.id = id;
        this.sportid = sportid;
        this.sportname = sportname;
        this.rank = rank;
        this.addscore = addscore;
    }

    public Role() {
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

    public Double getAddscore() {
        return addscore;
    }

    public void setAddscore(Double addscore) {
        this.addscore = addscore;
    }
}
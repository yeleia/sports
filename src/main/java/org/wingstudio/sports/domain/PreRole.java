package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.ModelAttribute;

@ApiModel("预赛加分规则")
public class PreRole {
    private Integer id;
    @ApiModelProperty("体育项目id")
    private Integer sportid;
    @ApiModelProperty("名次")
    private Integer rank;
    @ApiModelProperty("加分")
    private Double addscore;

    public PreRole(Integer id, Integer sportid, Integer rank, Double addscore) {
        this.id = id;
        this.sportid = sportid;
        this.rank = rank;
        this.addscore = addscore;
    }

    public PreRole() {
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
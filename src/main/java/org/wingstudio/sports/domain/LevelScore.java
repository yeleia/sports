package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LevelScore {
    private Integer id;
    @ApiModelProperty("二级运动员标准加分")
    private Double twolevel;
    @ApiModelProperty("创记录加分")
    private Double recordlevel;

    public LevelScore(Integer id, Double twolevel, Double recordlevel) {
        this.id = id;
        this.twolevel = twolevel;
        this.recordlevel = recordlevel;
    }

    public LevelScore() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTwolevel() {
        return twolevel;
    }

    public void setTwolevel(Double twolevel) {
        this.twolevel = twolevel;
    }

    public Double getRecordlevel() {
        return recordlevel;
    }

    public void setRecordlevel(Double recordlevel) {
        this.recordlevel = recordlevel;
    }
}
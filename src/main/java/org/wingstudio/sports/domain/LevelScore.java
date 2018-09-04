package org.wingstudio.sports.domain;

public class LevelScore {
    private Integer id;

    private Double twolevel;

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
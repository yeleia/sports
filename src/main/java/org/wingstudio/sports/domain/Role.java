package org.wingstudio.sports.domain;

public class Role {
    private Integer id;

    private Integer sportid;

    private Integer rank;

    private Double addscore;

    public Role(Integer id, Integer sportid, Integer rank, Double addscore) {
        this.id = id;
        this.sportid = sportid;
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
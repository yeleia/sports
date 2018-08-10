package org.wingstudio.sports.domain;

public class PreRole {
    private Integer id;

    private Integer sportid;

    private Integer rank;

    private Double addscore;

    private Double others;

    public PreRole(Integer id, Integer sportid, Integer rank, Double addscore, Double others) {
        this.id = id;
        this.sportid = sportid;
        this.rank = rank;
        this.addscore = addscore;
        this.others = others;
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

    public Double getOthers() {
        return others;
    }

    public void setOthers(Double others) {
        this.others = others;
    }
}
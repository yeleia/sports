package org.wingstudio.sports.domain;

public class Classes {
    private Integer id;

    private Integer sportid;

    private String classes;

    private Double score;

    private String teketime;

    public Classes(Integer id, Integer sportid, String classes, Double score, String teketime) {
        this.id = id;
        this.sportid = sportid;
        this.classes = classes;
        this.score = score;
        this.teketime = teketime;
    }

    public Classes() {
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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTeketime() {
        return teketime;
    }

    public void setTeketime(String teketime) {
        this.teketime = teketime == null ? null : teketime.trim();
    }
}
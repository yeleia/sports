package org.wingstudio.sports.domain;

public class Solo {
    private Integer id;

    private Integer sportid;

    private Integer contestantid;

    private Integer gate;

    private String remark;

    private String score;

    private String taketime;

    public Solo(Integer id, Integer sportid, Integer contestantid, Integer gate, String remark, String score, String taketime) {
        this.id = id;
        this.sportid = sportid;
        this.contestantid = contestantid;
        this.gate = gate;
        this.remark = remark;
        this.score = score;
        this.taketime = taketime;
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
}
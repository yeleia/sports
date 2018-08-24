package org.wingstudio.sports.domain;

public class TeamScore {
    private Integer id;

    private Integer sportid;

    private Integer teamid;

    private String score;

    private Double finalscore;

    private String taketime;

    private Integer checked;

    public TeamScore(Integer id, Integer sportid, Integer teamid, String score, Double finalscore, String taketime, Integer checked) {
        this.id = id;
        this.sportid = sportid;
        this.teamid = teamid;
        this.score = score;
        this.finalscore = finalscore;
        this.taketime = taketime;
        this.checked = checked;
    }

    public TeamScore() {
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

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public Double getFinalscore() {
        return finalscore;
    }

    public void setFinalscore(Double finalscore) {
        this.finalscore = finalscore;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime == null ? null : taketime.trim();
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
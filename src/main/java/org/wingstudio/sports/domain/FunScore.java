package org.wingstudio.sports.domain;

public class FunScore {
    private Integer id;

    private Integer funnameid;

    private String campus;

    private String classes;

    private String profession;

    private String classmate;

    private String taketime;

    public FunScore(Integer id, Integer funnameid, String campus, String classes, String profession, String classmate, String taketime) {
        this.id = id;
        this.funnameid = funnameid;
        this.campus = campus;
        this.classes = classes;
        this.profession = profession;
        this.classmate = classmate;
        this.taketime = taketime;
    }

    public FunScore() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFunnameid() {
        return funnameid;
    }

    public void setFunnameid(Integer funnameid) {
        this.funnameid = funnameid;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus == null ? null : campus.trim();
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    public String getClassmate() {
        return classmate;
    }

    public void setClassmate(String classmate) {
        this.classmate = classmate == null ? null : classmate.trim();
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime == null ? null : taketime.trim();
    }
}
package org.wingstudio.sports.domain;

public class Contestant {
    private Integer id;

    private Integer sportid;

    private String campus;

    private String classes;

    private String profession;

    private String stunumber;

    private String stuname;

    public Contestant(Integer id, Integer sportid, String campus, String classes, String profession, String stunumber, String stuname) {
        this.id = id;
        this.sportid = sportid;
        this.campus = campus;
        this.classes = classes;
        this.profession = profession;
        this.stunumber = stunumber;
        this.stuname = stuname;
    }

    public Contestant() {
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

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber == null ? null : stunumber.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }
}
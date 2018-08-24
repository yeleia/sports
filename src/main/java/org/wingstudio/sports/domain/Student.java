package org.wingstudio.sports.domain;

public class Student {
    private Integer id;

    private String stunumber;

    private String stuname;

    private String stusex;

    private String stuclasses;

    private String stuprofession;

    private String stucampus;

    public Student(Integer id, String stunumber, String stuname, String stusex, String stuclasses, String stuprofession, String stucampus) {
        this.id = id;
        this.stunumber = stunumber;
        this.stuname = stuname;
        this.stusex = stusex;
        this.stuclasses = stuclasses;
        this.stuprofession = stuprofession;
        this.stucampus = stucampus;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex == null ? null : stusex.trim();
    }

    public String getStuclasses() {
        return stuclasses;
    }

    public void setStuclasses(String stuclasses) {
        this.stuclasses = stuclasses == null ? null : stuclasses.trim();
    }

    public String getStuprofession() {
        return stuprofession;
    }

    public void setStuprofession(String stuprofession) {
        this.stuprofession = stuprofession == null ? null : stuprofession.trim();
    }

    public String getStucampus() {
        return stucampus;
    }

    public void setStucampus(String stucampus) {
        this.stucampus = stucampus == null ? null : stucampus.trim();
    }
}
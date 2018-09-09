package org.wingstudio.sports.VO;

public class QueryVO {
    private String stuName;
    private String sportname;
    private String score;
    private String campus;
    private String classes;
    //赛制
    private String nature;

    public String getStuName() {
        return stuName;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }



    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}

package org.wingstudio.sports.VO;

public class RecordVO {
    private String sportname;
    private String stuname;
    private String profession;
    private String stunumber;
    private String sex;
    private String score;
    private String classes;
    //赛制
    private String nature;

    public String getProfession() {
        return profession;
    }

    @Override
    public String toString() {
        return "RecordVO{" +
                "sportname='" + sportname + '\'' +
                ", stuname='" + stuname + '\'' +
                ", profession='" + profession + '\'' +
                ", stunumber='" + stunumber + '\'' +
                ", sex='" + sex + '\'' +
                ", score='" + score + '\'' +
                ", classes='" + classes + '\'' +
                ", nature='" + nature + '\'' +
                '}';
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
    }

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

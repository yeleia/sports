package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.ModelAttribute;

@ApiModel("参赛人员信息")
public class Contestant {
    private Integer id;

    private Integer sportid;
    @ApiModelProperty("项目名")
    private String sportname;
    @ApiModelProperty("校区")
    private String campus;
    @ApiModelProperty("学院")
    private String classes;
    @ApiModelProperty("专业")
    private String profession;
    @ApiModelProperty("学号")
    private String stunumber;
    @ApiModelProperty("姓名")
    private String stuname;
    @ApiModelProperty("第几届")
    private String currentime;
    @ApiModelProperty("审核")
    private Integer cheched;

    public Contestant(Integer id, Integer sportid, String sportname, String campus, String classes, String profession, String stunumber, String stuname, String currentime, Integer cheched) {
        this.id = id;
        this.sportid = sportid;
        this.sportname = sportname;
        this.campus = campus;
        this.classes = classes;
        this.profession = profession;
        this.stunumber = stunumber;
        this.stuname = stuname;
        this.currentime = currentime;
        this.cheched = cheched;
    }

    public Integer getCheched() {
        return cheched;
    }

    public void setCheched(Integer cheched) {
        this.cheched = cheched;
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

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname == null ? null : sportname.trim();
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

    public String getCurrentime() {
        return currentime;
    }

    public void setCurrentime(String currentime) {
        this.currentime = currentime == null ? null : currentime.trim();
    }
}
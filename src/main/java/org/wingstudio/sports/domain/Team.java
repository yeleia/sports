package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("集体项目报名信息")
public class Team {
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
    @ApiModelProperty("第几届")
    private String currentime;

    public Team(Integer id, Integer sportid, String sportname, String campus, String classes, String profession, String currentime) {
        this.id = id;
        this.sportid = sportid;
        this.sportname = sportname;
        this.campus = campus;
        this.classes = classes;
        this.profession = profession;
        this.currentime = currentime;
    }

    public Team() {
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

    public String getCurrentime() {
        return currentime;
    }

    public void setCurrentime(String currentime) {
        this.currentime = currentime == null ? null : currentime.trim();
    }
}
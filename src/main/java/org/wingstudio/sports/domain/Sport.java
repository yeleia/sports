package org.wingstudio.sports.domain;

public class Sport {
    private Integer id;

    private String project;

    private Integer sex;

    private Integer sortrule;

    private String inmax;

    private String inmin;

    private String record;

    private String twolevel;

    public Sport(Integer id, String project, Integer sex, Integer sortrule, String inmax, String inmin, String record, String twolevel) {
        this.id = id;
        this.project = project;
        this.sex = sex;
        this.sortrule = sortrule;
        this.inmax = inmax;
        this.inmin = inmin;
        this.record = record;
        this.twolevel = twolevel;
    }

    public Sport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSortrule() {
        return sortrule;
    }

    public void setSortrule(Integer sortrule) {
        this.sortrule = sortrule;
    }

    public String getInmax() {
        return inmax;
    }

    public void setInmax(String inmax) {
        this.inmax = inmax == null ? null : inmax.trim();
    }

    public String getInmin() {
        return inmin;
    }

    public void setInmin(String inmin) {
        this.inmin = inmin == null ? null : inmin.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getTwolevel() {
        return twolevel;
    }

    public void setTwolevel(String twolevel) {
        this.twolevel = twolevel == null ? null : twolevel.trim();
    }
}
package org.wingstudio.sports.domain;

public class Message {
    private Integer id;

    private String message;

    private Integer checked;

    private String stunum;

    private String stuname;

    public Message(Integer id, String message, Integer checked, String stunum, String stuname) {
        this.id = id;
        this.message = message;
        this.checked = checked;
        this.stunum = stunum;
        this.stuname = stuname;
    }

    public Message() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum == null ? null : stunum.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }
}
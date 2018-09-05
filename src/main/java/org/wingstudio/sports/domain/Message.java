package org.wingstudio.sports.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("留言实体类")
public class Message {
    private Integer id;
@ApiModelProperty("留言内容")
    private String message;

    private Integer checked;
@ApiModelProperty("学生学号")
    private String stunum;
    @ApiModelProperty("学生姓名")
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
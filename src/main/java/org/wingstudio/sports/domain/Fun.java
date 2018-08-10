package org.wingstudio.sports.domain;

public class Fun {
    private Integer id;

    private String funname;

    public Fun(Integer id, String funname) {
        this.id = id;
        this.funname = funname;
    }

    public Fun() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFunname() {
        return funname;
    }

    public void setFunname(String funname) {
        this.funname = funname == null ? null : funname.trim();
    }
}
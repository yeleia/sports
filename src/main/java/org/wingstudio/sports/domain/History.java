package org.wingstudio.sports.domain;

public class History {
    private Integer id;

    private String taketime;

    private String theme;

    public History(Integer id, String taketime, String theme) {
        this.id = id;
        this.taketime = taketime;
        this.theme = theme;
    }

    public History() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime == null ? null : taketime.trim();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }
}
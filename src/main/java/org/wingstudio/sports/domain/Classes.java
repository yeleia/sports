package org.wingstudio.sports.domain;

public class Classes {
    private Integer id;

    private String classes;

    private Double score;

    private String teketime;

    public Classes(Integer id, String classes, Double score, String teketime) {
        this.id = id;
        this.classes = classes;
        this.score = score;
        this.teketime = teketime;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", classes='" + classes + '\'' +
                ", score=" + score +
                ", teketime='" + teketime + '\'' +
                '}';
    }

    public Classes() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTeketime() {
        return teketime;
    }

    public void setTeketime(String teketime) {
        this.teketime = teketime == null ? null : teketime.trim();
    }
}
package graduatesystem.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/9/12.
 */
@Entity
public class TS {
    @Id
    @GeneratedValue
    private Integer tsid;

    private String Gtname;

    private String studentname;

    private String teachername1;

    private String teachername2;

    private String comment;

    private String score;

    private String filepath;

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public String getGtname() {
        return Gtname;
    }

    public void setGtname(String gtname) {
        Gtname = gtname;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getTeachername1() {
        return teachername1;
    }

    public void setTeachername1(String teachername1) {
        this.teachername1 = teachername1;
    }

    public String getTeachername2() {
        return teachername2;
    }

    public void setTeachername2(String teachername2) {
        this.teachername2 = teachername2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public TS() {
    }
}



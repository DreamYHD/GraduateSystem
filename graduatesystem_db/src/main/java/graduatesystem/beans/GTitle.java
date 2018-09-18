package graduatesystem.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/9/12.
 */
@Entity
public class GTitle {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    
    private String startime;

    private String endtime;

    private String description;

    private String teachername;

    private String studentname;

    private String isthrough;

    private String isselect;

    private String teacher1name;

    private String teacher2name;

    private String discuss;

    private String filepath;

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTeacher1name() {
        return teacher1name;
    }

    public void setTeacher1name(String teacher1name) {
        this.teacher1name = teacher1name;
    }

    public String getTeacher2name() {
        return teacher2name;
    }

    public void setTeacher2name(String teacher2name) {
        this.teacher2name = teacher2name;
    }

    public GTitle() {

    }

    public String getIsthrough() {
        return isthrough;
    }

    public void setIsthrough(String isthrough) {
        this.isthrough = isthrough;
    }

    public String getIsselect() {
        return isselect;
    }

    public void setIsselect(String isselect) {
        this.isselect = isselect;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }






    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

}

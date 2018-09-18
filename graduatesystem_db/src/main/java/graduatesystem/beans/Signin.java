package graduatesystem.beans;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
@Entity
public class Signin {
    @Id
    @GeneratedValue
    private Integer id;
    private String  teachername;
    private String time;
    private String studentarray;
    private String isstart;
    private String keyword;

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getStudentarray() {
        return studentarray;
    }

    public void setStudentarray(String studentarray) {
        this.studentarray = studentarray;
    }

    public String getIsstart() {
        return isstart;
    }

    public void setIsstart(String isstart) {
        this.isstart = isstart;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Signin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}

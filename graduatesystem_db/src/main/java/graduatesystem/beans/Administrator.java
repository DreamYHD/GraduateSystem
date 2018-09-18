package graduatesystem.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/9/12.
 */
@Entity
public class Administrator {

    @Id
    private Integer id;
    private String name;
    private String password;

    public Administrator() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

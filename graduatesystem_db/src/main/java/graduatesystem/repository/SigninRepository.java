package graduatesystem.repository;

import graduatesystem.beans.Signin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
public interface SigninRepository extends JpaRepository<Signin,Integer> {
    public List<Signin> findByTeachernameAndIsstart(String teachername,String isstart);
    public List<Signin> findByTeachername(String teachername);

}

package graduatesystem.repository;

import graduatesystem.beans.TS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
public interface TSRepository extends JpaRepository<TS,Integer> {
    public List<TS>findByStudentname(String studentname);
    public List<TS>findByTeachername1(String teachername1);
    public List<TS>findByTeachername2(String teachername2);
}

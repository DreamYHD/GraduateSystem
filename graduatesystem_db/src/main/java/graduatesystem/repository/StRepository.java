package graduatesystem.repository;

import graduatesystem.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
public interface StRepository extends JpaRepository<Student,Integer> {
    public List<Student>findByName(String studentname);
}

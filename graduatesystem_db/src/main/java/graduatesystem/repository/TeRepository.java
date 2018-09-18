package graduatesystem.repository;

import graduatesystem.beans.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/9/12.
 */
public interface TeRepository  extends JpaRepository<Teacher,Integer>{
}

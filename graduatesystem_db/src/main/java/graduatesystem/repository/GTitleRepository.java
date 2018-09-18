package graduatesystem.repository;

import graduatesystem.beans.GTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
public interface GTitleRepository extends JpaRepository<GTitle,Integer> {
    public List<GTitle>findByStudentname(String studentName);
    public List<GTitle>findByTeachername(String teacherName);
    public List<GTitle>findByIsthrough(String isThrough);
    public List<GTitle>findByTeachernameAndIsselect(String student,String isSelcet);
    public List<GTitle>findByIsthroughAndIsselect(String isThrough,String isSelect);
}

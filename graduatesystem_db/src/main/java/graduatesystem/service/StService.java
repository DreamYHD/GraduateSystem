package graduatesystem.service;

import graduatesystem.beans.Student;
import graduatesystem.beans.Teacher;
import graduatesystem.repository.StRepository;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
@Service
public class StService {
   @Autowired
    private StRepository stRepository;
    public Boolean verifyPassword(Integer id, String password) {
        Student s = null;
        try{
            s = stRepository.findById(id).get();

        }catch (Exception e){

        }
        if (s.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
    public Result<Student> studentAdd(Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(stRepository.save(student));
    }
    public Student findById(Integer id) {
        return stRepository.findById(id).get();

    }
    public List<Student> findByStudentname(String Studentname) {
        return stRepository.findByName(Studentname);

    }
}

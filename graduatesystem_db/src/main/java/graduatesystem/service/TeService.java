package graduatesystem.service;

import graduatesystem.beans.Teacher;
import graduatesystem.repository.TeRepository;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * Created by Administrator on 2018/9/12.
 */
@Service
public class TeService {
   @Autowired
    private TeRepository teRepository;
    public Boolean verifyPassword(Integer id, String password) {
        Teacher s = null;
        try{
            s = teRepository.findById(id).get();

        }catch (Exception e){

        }
        if (s.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public Result<Teacher> teacherAdd(Teacher teacher, BindingResult bindingResult) {
                 if (bindingResult.hasErrors()){
                     return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
                 }
                 return ResultUtil.success(teRepository.save(teacher));
    }
    public Teacher findById(Integer id) {
        return teRepository.findById(id).get();
    }

}

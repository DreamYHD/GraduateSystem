package graduatesystem.service;

import graduatesystem.beans.GTitle;
import graduatesystem.beans.Signin;
import graduatesystem.beans.Teacher;
import graduatesystem.repository.SigninRepository;
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
public class SiginService {

    @Autowired
    private SigninRepository signinRepository;
    public Result<Signin> siginAdd(Signin signin, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(signinRepository.save(signin));
    }
    public Signin findById(Integer id) {
        return signinRepository.findById(id).get();
    }


    public List<Signin> findByTeachernameAndIsstart(String teachername, String isstart) {
        return signinRepository.findByTeachernameAndIsstart(teachername,isstart);
    }

    public List<Signin> findByTeachername(String teachername) {
         return signinRepository.findByTeachername(teachername);
    }
    public Signin signinUpdate(Integer id,
                               String isstart,
                               String keyword,
                               String studentarray,
                               String teachername,
                               String time){
       Signin signin = new Signin();
       signin.setTeachername(teachername);
       signin.setId(id);
       signin.setIsstart(isstart);
       signin.setStudentarray(studentarray);
       signin.setTime(time);
       signin.setKeyword(keyword);
       return signinRepository.save(signin);
    }
}

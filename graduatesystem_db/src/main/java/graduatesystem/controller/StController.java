package graduatesystem.controller;

import graduatesystem.beans.Administrator;
import graduatesystem.beans.Signin;
import graduatesystem.beans.Student;
import graduatesystem.beans.Teacher;
import graduatesystem.service.StService;
import graduatesystem.utils.Password;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
@RestController
@Configuration
public class StController {
    @Autowired
    private StService stService;
    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    @PostMapping(value = "/student/verity" )
    public Result verifyPassword(@Valid Password password, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        logger.info(password.getId()+"--------------"+password.getPassword());
        return ResultUtil.success(stService.verifyPassword(password.getId(),password.getPassword()));
    }
    @PostMapping(value = "/student/add")
    public Result<Student> studentAdd(Student student, BindingResult bindingResult){
        return stService.studentAdd(student,bindingResult);
    }
    @GetMapping(value = "/student/findone/{id}")
    public Result<Student> findOne(@PathVariable("id") Integer id){
        return ResultUtil.success(stService.findById(id));
    }
    @GetMapping(value = "/student/findbyname/{studentname}")
    public Result<List<Student>>findByTeachername(@PathVariable("studentname")String studentname){
        return ResultUtil.success(stService.findByStudentname(studentname));
    }

}

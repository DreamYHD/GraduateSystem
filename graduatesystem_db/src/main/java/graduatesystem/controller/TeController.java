
package graduatesystem.controller;

import graduatesystem.beans.Teacher;
import graduatesystem.service.TeService;
import graduatesystem.utils.Password;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Configuration
public class TeController {
    private static final Logger logger = LoggerFactory.getLogger(Teacher.class);

    @Autowired
    private TeService teService;
    @PostMapping(value = "/teacher/add")
    public Result<Teacher> teacherAdd(Teacher teacher, BindingResult bindingResult){
        return teService.teacherAdd(teacher,bindingResult);
    }
    @PostMapping(value = "/teacher/verity" )
    public Result verifyPassword(@Valid Password password, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        logger.info(password.getId()+"--------------"+password.getPassword());
        return ResultUtil.success(teService.verifyPassword(password.getId(),password.getPassword()));
    }
    @GetMapping(value = "/teacher/findone/{id}")
    public Result<Teacher> findOne(@PathVariable("id") Integer id){
        return ResultUtil.success(teService.findById(id));
    }
}


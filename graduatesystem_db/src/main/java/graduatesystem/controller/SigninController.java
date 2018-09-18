package graduatesystem.controller;

import graduatesystem.beans.GTitle;
import graduatesystem.beans.Signin;
import graduatesystem.beans.Student;
import graduatesystem.service.SiginService;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
@RestController
@Configuration
public class SigninController {
    @Autowired
    private SiginService siginService;
    @PostMapping(value = "/signin/add")
    public Result<Signin> signinAdd(Signin signin, BindingResult bindingResult){
        return siginService.siginAdd(signin,bindingResult);
    }
    @GetMapping(value = "/signin/findbyti/{teachername}/{isstart}")
    public Result<List<Signin>>findByTeachernameAndIsstart(@PathVariable("teachername")String teachername, @PathVariable("isstart")String isstart){
        return ResultUtil.success(siginService.findByTeachernameAndIsstart(teachername, isstart));
    }
    @GetMapping(value = "/signin/findbyt/{teachername}")
    public Result<List<Signin>>findByTeachername(@PathVariable("teachername")String teachername){
        return ResultUtil.success(siginService.findByTeachername(teachername));
    }
    @PostMapping(value = "/signin/update/{id}")
    public Result gtitleUpdate(@PathVariable("id") Integer id,
                               @RequestParam("isstart")String isstart,
                               @RequestParam("keyword") String keyword,
                               @RequestParam("studentarray") String studentarray,
                               @RequestParam("teachername") String teachername,
                               @RequestParam("time") String time
    ){
        return ResultUtil.success(siginService.signinUpdate(
                id,isstart,keyword,studentarray,teachername,time
        ));
    }
}

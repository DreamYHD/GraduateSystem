package graduatesystem.controller;

import graduatesystem.beans.Student;
import graduatesystem.beans.TS;
import graduatesystem.beans.Teacher;
import graduatesystem.service.TSService;
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
public class TSController {
    @Autowired
    private TSService tsService;
    @PostMapping(value = "/ts/add")
    public Result<TS> tsAdd(TS ts, BindingResult bindingResult){
        return tsService.tsAdd(ts,bindingResult);
    }
    @PostMapping(value = "/ts/update/{studentname}")
    public Result tsUpdate(@RequestParam("tsid") Integer tsid,
                               @RequestParam("gtname")String gtname,
                               @PathVariable("studentname")String studentname,
                               @RequestParam("comment")String comment,
                               @RequestParam("teachername1") String teachername1,
                               @RequestParam("teachername2") String teachername2,
                               @RequestParam("score") String score,
                               @RequestParam("filepath") String filepath

    ){
        return ResultUtil.success(tsService.tsUpdate(tsid,gtname,studentname,comment,teachername1,teachername2,score,filepath)
        );
    }
    @GetMapping(value = "/ts/findbyteachername/{teachername}")
    public Result<List<TS>>findByTeachername(@PathVariable("teachername")String teachername){
        return ResultUtil.success(tsService.selectByteachername(teachername));
    }
    @GetMapping(value = "/ts/findbystudentname/{studentname}")
    public Result<List<TS>>findByStudentname(@PathVariable("studentname")String studentname){
        return ResultUtil.success(tsService.selectByStudentName(studentname));
    }
}

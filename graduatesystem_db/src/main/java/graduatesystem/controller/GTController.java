package graduatesystem.controller;

import graduatesystem.beans.GTitle;
import graduatesystem.service.GTService;
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
public class GTController {
    @Autowired
    private GTService service;
    @PostMapping(value = "/gtitle/add")
    public Result<GTitle> studentAdd(GTitle gTitle, BindingResult bindingResult){
        return service.gtAdd(gTitle,bindingResult);
    }
    @GetMapping(value = "/gtitle/findall")
    public Result<List<GTitle>>findAll(){
        return ResultUtil.success(service.findAll());
    }

    @GetMapping(value = "/gtitle/findbyteachername/{teachername}")
    public Result<List<GTitle>>findByTeacherName(@PathVariable("teachername")String teachername){
        return ResultUtil.success(service.findByTeacherName(teachername));
    }
    @GetMapping(value = "/gtitle/findbystudentname/{studentname}")
    public Result<List<GTitle>>findByStudentName(@PathVariable("studentname")String studentname){
        return ResultUtil.success(service.findByStudentName(studentname));
    }
    @GetMapping(value = "/gtitle/findbyis/{isthrough}/{isselect}")
    public Result<List<GTitle>>findByIsThroughAndInSelect(@PathVariable("isthrough")String isThough, @PathVariable("isselect")String isSelect){
        return ResultUtil.success(service.findByIsThroughAndInSelect(isThough, isSelect));
    }
    @GetMapping(value = "/gtitle/findbyts/{teachername}/{isselect}")
    public Result<List<GTitle>>findByTeacherAndInSelect(@PathVariable("teachername")String teachername, @PathVariable("isselect")String isSelect){
        return ResultUtil.success(service.findByTeachernameAndInSelect(teachername, isSelect));
    }
    @GetMapping(value = "/gtitle/findbyis/{isthrough}")
    public Result<List<GTitle>>findByIsThroughAndInSelect(@PathVariable("isthrough")String isThough){
        return ResultUtil.success(service.findByIsThrough(isThough));
    }
    @DeleteMapping(value = "/gtitle/delete/{id}")
    public Result studentDlelete(@PathVariable("id") Integer id){
        service.gTileFelete(id);
        return ResultUtil.success();
    }
    @PostMapping(value = "/gtitle/update/{id}")
    public Result gtitleUpdate(@PathVariable("id") Integer id,
                               @RequestParam("description")String description,
                               @RequestParam("endtime") String endtime,
                               @RequestParam("startime") String startime,
                               @RequestParam("isselect") String isselect,
                               @RequestParam("isthrough") String isthrough,
                               @RequestParam("studentname") String studentname,
                               @RequestParam("teacher1name") String teacher1name,
                               @RequestParam("teacher2name") String teacher2name,
                               @RequestParam("title") String title,
                               @RequestParam("teachername") String teachername,
                               @RequestParam("filepath")String filepath,
                               @RequestParam("discuss")String discuss

    ){
        return ResultUtil.success(service.studentUpdate(
                id,description,endtime,startime,isselect,isthrough,studentname,teacher1name,teacher2name,title,teachername,filepath,discuss
        ));
    }

}

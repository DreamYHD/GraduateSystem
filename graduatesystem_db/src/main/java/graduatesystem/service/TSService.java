package graduatesystem.service;

import graduatesystem.beans.Signin;
import graduatesystem.beans.TS;
import graduatesystem.beans.Teacher;
import graduatesystem.repository.TSRepository;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */
@Service
public class TSService {
    @Autowired
    private TSRepository tsRepository;
    public Result<TS> tsAdd(TS ts, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(tsRepository.save(ts));
    }
    public TS tsUpdate(Integer tsid, String gtname, String studentname, String comment, String teachername1, String teachername2, String score, String filepath){
        TS ts = new TS();
        ts.setTsid(tsid);
        ts.setFilepath(filepath);
        ts.setGtname(gtname);
        ts.setStudentname(studentname);
        ts.setComment(comment);
        ts.setScore(score);
        ts.setTeachername1(teachername1);
        ts.setTeachername2(teachername2);
        return tsRepository.save(ts);
    }
    public List<TS>selectByteachername(String teachername){
        List<TS>list = new ArrayList<>();
        list.addAll(tsRepository.findByTeachername1(teachername));
        list.addAll(tsRepository.findByTeachername2(teachername));
        return list;
    }
    public List<TS>selectByStudentName(String studentname){
        return tsRepository.findByStudentname(studentname);
    }

}

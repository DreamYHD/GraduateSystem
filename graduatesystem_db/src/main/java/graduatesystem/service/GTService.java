package graduatesystem.service;

import graduatesystem.beans.GTitle;
import graduatesystem.repository.GTitleRepository;
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
public class GTService {
   @Autowired
    private GTitleRepository gTitleRepository;

   public List<GTitle> findAll() {
        return gTitleRepository.findAll();
    }

    public Result<GTitle> gtAdd(GTitle gTitle, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(gTitleRepository.save(gTitle));
    }
    public void gtDelete(Integer id){
        gTitleRepository.deleteById(id);
    }
    public List<GTitle> findByTeacherName(String teachername){
        return gTitleRepository.findByTeachername(teachername);
    }
    public List<GTitle> findByStudentName(String studentname){
        return gTitleRepository.findByStudentname(studentname);
    }
    public List<GTitle> findByIsThroughAndInSelect(String isThrough,String isSelect){
        return gTitleRepository.findByIsthroughAndIsselect(isThrough, isSelect);
    }
    public List<GTitle> findByTeachernameAndInSelect(String teachername,String isSelect){
        return gTitleRepository.findByTeachernameAndIsselect(teachername, isSelect);
    }
    public void gTileFelete(Integer id) {
        gTitleRepository.deleteById(id);
    }

    public GTitle studentUpdate(Integer id,
                                String description,
                                String endtime,
                                String startime,
                                String isselect,
                                String isthrough,
                                String studentname,
                                String teacher1name,
                                String teacher2name,
                                String title,
                                String teachername,
                                String filepath,
                                String discuss) {
         GTitle gTitle = new GTitle();
         gTitle.setId(id);
         gTitle.setDescription(description);
         gTitle.setEndtime(endtime);
         gTitle.setStartime(startime);
         gTitle.setIsselect(isselect);
         gTitle.setIsthrough(isthrough);
         gTitle.setTeacher1name(teacher1name);
         gTitle.setTeacher2name(teacher2name);
         gTitle.setTeachername(teachername);
         gTitle.setTitle(title);
         gTitle.setStudentname(studentname);
         gTitle.setFilepath(filepath);
         gTitle.setDiscuss(discuss);
         return gTitleRepository.save(gTitle);
    }
    public Object findByIsThrough(String isThough) {
    return gTitleRepository.findByIsthrough(isThough);
}
}

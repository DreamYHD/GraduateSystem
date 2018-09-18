package graduatesystem.controller;

import graduatesystem.beans.Administrator;
import graduatesystem.repository.AdRepository;
import graduatesystem.service.AdService;
import graduatesystem.utils.Password;
import graduatesystem.utils.Result;
import graduatesystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

/**
 * Created by Administrator on 2018/9/12.
 */
@RestController
@Configuration
public class AdController {
    @Autowired
    private AdService adService;

    private static final Logger logger = LoggerFactory.getLogger(Administrator.class);
    @PostMapping(value = "/administrator/verity" )
    public Result verifyPassword(@Valid Password password, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        logger.info(password.getId()+"--------------"+password.getPassword());
        return ResultUtil.success(adService.verifyPassword(password.getId(),password.getPassword()));
    }
}

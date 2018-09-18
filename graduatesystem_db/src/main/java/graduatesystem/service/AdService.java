package graduatesystem.service;

import graduatesystem.beans.Administrator;
import graduatesystem.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/9/12.
 */
@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;
    public Boolean verifyPassword(Integer id, String password) {
        Administrator s = null;
        try{
            s = adRepository.findById(id).get();
        }catch (Exception e){

        }
        if (s.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}

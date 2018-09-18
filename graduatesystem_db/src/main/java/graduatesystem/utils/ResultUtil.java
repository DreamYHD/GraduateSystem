package graduatesystem.utils;

/**
 * Created by Administrator on 2018/9/12.
 */
public class ResultUtil {

    public static Result success(Object o){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(o);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer integer,String msg){
        Result result = new Result();
        result.setCode(integer);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}

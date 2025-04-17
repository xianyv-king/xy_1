package system.service;


import system.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ActivityService extends IService<Activity> {


    void create(Activity activity);

    List<Activity> listActivity();

    Activity details(String id);
}

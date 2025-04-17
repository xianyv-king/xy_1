package system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import system.entity.Option;
import system.entity.UserOption;

import java.util.List;

public interface OptionService extends IService<Option> {
    void vote(List<UserOption> userOptionList);

    void vote(String activityId, List<String> optionIds);
}

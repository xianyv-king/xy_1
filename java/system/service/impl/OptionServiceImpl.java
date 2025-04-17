package system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.common.LoginContext;
import system.entity.Option;
import system.entity.UserOption;
import system.mapper.OptionMapper;
import system.mapper.UserOptionMapper;
import system.service.OptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {

    private final UserOptionMapper userOptionMapper;

    @Override
    public void vote(List<UserOption> userOptionList) {
        userOptionList.forEach(userOption -> {
            userOption.setUserId(LoginContext.getLoginUserId());
            userOption.setSelected(true);
            userOptionMapper.insert(userOption);

            Option option = baseMapper.selectById(userOption.getOptionId());
            option.setQuantity(option.getQuantity()+1);
            baseMapper.updateById(option);
        });
    }

    @Override
    public void vote(String activityId, List<String> optionIds) {
        optionIds.forEach(optionId -> {
            UserOption userOption = new UserOption();
            userOption.setActivityId(activityId);
            userOption.setOptionId(optionId);
            userOption.setUserId(LoginContext.getLoginUserId());
            userOption.setSelected(true);
            userOptionMapper.insert(userOption);

            Option option = baseMapper.selectById(userOption.getOptionId());
            option.setQuantity(option.getQuantity()+1);
            baseMapper.updateById(option);
        });
    }
}

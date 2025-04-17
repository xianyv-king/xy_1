package system.service.impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import system.common.LoginContext;
import system.entity.Activity;
import system.entity.Option;
import system.entity.UserOption;
import system.mapper.ActivityMapper;
import system.mapper.OptionMapper;
import system.mapper.UserOptionMapper;
import system.service.ActivityService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    private final OptionMapper optionMapper;
    private final UserOptionMapper userOptionMapper;

    @Override
    public void create(Activity activity) {
        activity.setId(String.valueOf(IdWorker.getId()));
        activity.setStartTime(LocalDateTime.now());
        activity.setCreateBy(LoginContext.getLoginUserId());

        activity.options.forEach(text -> {
            Option option = new Option();
            option.setText(text);
            option.setActivityId(activity.getId());
            optionMapper.insert(option);
        });

        this.save(activity);
    }

    @Override
    public List<Activity> listActivity() {
        return this.list().stream().map(activity -> details(activity.getId())).toList();
    }

    @Override
    public Activity details(String id) {

        Activity activity = baseMapper.selectById(id);
        activity.setOptionList(optionMapper.selectList(Wrappers.lambdaQuery(Option.class).eq(Option::getActivityId, id)));
        activity.setStatus(activity.getEndTime().isBefore(LocalDateTime.now()) ? 1 : 0);


        Long count = userOptionMapper.selectCount(Wrappers.lambdaQuery(UserOption.class)
                .eq(UserOption::getActivityId, id)
                .eq(UserOption::getUserId, LoginContext.getLoginUserId()));
        activity.setVoted(count > 0);


        int total = 0;
        for (Option option : activity.getOptionList()) {
            total+=option.getQuantity();
        }
        activity.setTotal(total);

        activity.setStatus(activity.getEndTime().isBefore(LocalDateTime.now()) ? 1 : 0);

        return activity;
    }
}





package system.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.entity.Activity;
import system.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;


    @GetMapping
    public Result list() {
        List<Activity> list = activityService.listActivity();
        return Result.success().data(list);
    }

    @GetMapping("/{id}")
    public Result details(@PathVariable String id) {
        Activity activity = activityService.details(id);
        return Result.success().data(activity);
    }

    @PostMapping
    public Result create(@RequestBody Activity activity) {
        activityService.create(activity);
        return Result.success();
    }


}


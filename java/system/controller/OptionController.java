package system.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.entity.Option;
import system.service.OptionService;

import java.util.List;

@RestController
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService service;

    @GetMapping
    public Result list() {
        List<Option> list = service.list();
        return Result.success().data(list);
    }

    @PostMapping
    public Result create(@RequestBody Option entity) {
        service.save(entity);
        return Result.success();
    }


    @PostMapping("/vote/{activityId}")
    public Result vote(@RequestBody List<String> optionIds, @PathVariable String activityId) {
        service.vote(activityId,optionIds);
        return Result.success();
    }


}


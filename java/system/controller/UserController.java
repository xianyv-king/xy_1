package system.controller;


import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.entity.User;
import system.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        boolean save = userService.save(user);
        return Result.success().data(save);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {

        User one = userService.getOne(Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));

        return one == null ? Result.fail().message("用户名或密码错误") : Result.success().data(one);
    }

    @GetMapping("/exist")
    public Result exist(@RequestParam("username") String username) {
        User one = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username));
        return Result.success().data(one != null);
    }

}


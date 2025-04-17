package system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import system.interceptor.TokenInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths = new ArrayList<>();

        // 1. 用户相关接口放行
        excludePaths.add("/api/user/login");
        excludePaths.add("/api/user/register");
        excludePaths.add("/api/user/exist/*");
        excludePaths.add("/api/image/upload");

        // 2. 静态资源放行（按需调整路径）
        excludePaths.add("/static/**");
        excludePaths.add("/public/**");
        excludePaths.add("/resources/**");
        excludePaths.add("/css/**");
        excludePaths.add("/js/**");
        excludePaths.add("/images/**");
        excludePaths.add("/favicon.ico");
        excludePaths.add("/error");

        // 注册拦截器，并设置拦截规则
        registry.addInterceptor(new TokenInterceptor())
                .excludePathPatterns(excludePaths)
                .addPathPatterns("/api/**")
                .order(1);
    }
}
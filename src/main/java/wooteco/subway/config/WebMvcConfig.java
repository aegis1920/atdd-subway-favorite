package wooteco.subway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wooteco.subway.web.member.LoginMemberMethodArgumentResolver;
import wooteco.subway.web.member.interceptor.BasicAuthInterceptor;
import wooteco.subway.web.member.interceptor.BearerAuthInterceptor;
import wooteco.subway.web.member.interceptor.SessionInterceptor;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final BasicAuthInterceptor basicAuthInterceptor;
    private final SessionInterceptor sessionInterceptor;
    private final BearerAuthInterceptor bearerAuthInterceptor;
    private final LoginMemberMethodArgumentResolver loginMemberArgumentResolver;

    public WebMvcConfig(BasicAuthInterceptor basicAuthInterceptor,
                        SessionInterceptor sessionInterceptor,
                        BearerAuthInterceptor bearerAuthInterceptor,
                        LoginMemberMethodArgumentResolver loginMemberArgumentResolver) {
        this.basicAuthInterceptor = basicAuthInterceptor;
        this.sessionInterceptor = sessionInterceptor;
        this.bearerAuthInterceptor = bearerAuthInterceptor;
        this.loginMemberArgumentResolver = loginMemberArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthInterceptor).addPathPatterns("/me/basic");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/me/session");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/me/bearer");
    }

    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.add(loginMemberArgumentResolver);
    }
}

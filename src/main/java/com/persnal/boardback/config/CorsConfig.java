package com.persnal.boardback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //@Configuration 어노테이션 --> 설정과 관련된 어노테이션으로 프로젝트 실행시 설정을 구성하고 클래스 이하의 메서드를 1회 실행시킴.
    // Cross-Origin Resource Sharing 관련 설정 >> HTTPS의 보안과 관련이 있음.(요건 상세히 찾아봐야 겠다.)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}

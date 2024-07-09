package com.persnal.boardback.config;

import com.google.gson.Gson;
import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.WorkLogger;
import com.persnal.boardback.etc.ApiResponse;
import com.persnal.boardback.filter.JwtAuthenticationFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final Logger logger = WorkLogger.getInstance().getLogger();
    private final JwtAuthenticationFilter filter;


    // @Bean --> 메서드가 반환하는 SecurityFilterChain 객체를 Bean으로 등록함.
    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        SecurityFilterChain chain = null;


        // 1) csrf, httpBasic off , 2) session 정책설정.
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(managementConfigurer -> {
            managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        httpSecurity.authorizeHttpRequests(authorizeRequests -> {// 인증이 필요없는 URL에 대한 권한 허용
            authorizeRequests
                    .requestMatchers("/","/api/v1/auth/**").permitAll()  // 특정 URL에 대해서 접근을 허용
                    .requestMatchers(HttpMethod.GET, "/api/v1/board/**","/api/v1/user/*","/file/*","/api/v1/search/**").permitAll() // 특정 http method & URL에 대해 허용.
                    .requestMatchers(HttpMethod.POST, "file/upload").permitAll()
                    .anyRequest().authenticated();

        });


        // 인증 예외 발생히 처리할 클래스 등록. AuthenticationEntryPoint 를 구현한 클래스여야 한다.
        httpSecurity.exceptionHandling(handler ->{
           handler.authenticationEntryPoint(new FailedAuthenticationEntryPoint());
           //handler.accessDeniedHandler(new FailedAuthorizationEntryPoint());// 권한이 없을시에 처리할 헨들러 등록.
        });

        //UsernamePasswordAuthenticationFilter는 폼기반의 로그인과 관련된 필터이므로, api를 사용한 동작에는 필요가 없다.
        // 이렇게 UsernamePasswordAuthenticationFilter 이전에 커스텀 필터를 넣어주게되면 UsernamePasswordAuthenticationFilter를 우회하므로 비활성화가 처리가 된다.
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


        chain = httpSecurity.build();

        return chain;
    }

    static class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

        Logger logger = WorkLogger.getInstance().getLogger();

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            logger.error(GlobalVariable.logPattern,getClass().getName(), Utils.getStackTrace(authException));

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ApiResponse rsp = new ApiResponse();
            rsp.setCode("AF");
            rsp.setMessage("Authentication Failed");
            response.getWriter().write(new Gson().toJson(rsp));
        }
    }


//    static class FailedAuthorizationEntryPoint implements AccessDeniedHandler {
//
//        @Override
//        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//
//        }
//    }





}

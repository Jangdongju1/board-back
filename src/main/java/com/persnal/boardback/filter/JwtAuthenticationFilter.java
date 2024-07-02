package com.persnal.boardback.filter;

import com.persnal.boardback.JwtProvider;
import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.WorkLogger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private Logger logger = WorkLogger.getInstance().getLogger();
    private final JwtProvider jwtProvider;


    // 필터에 대해서 찾아보기
    // 컨트롤러에 도달하기 전 필터를 거친다고 함 >> 요청을 인터셉트
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);

            if (token == null){
                // 인증실패
                filterChain.doFilter(request,response);
                return;
            }

            String email = jwtProvider.validate(token);

            if (email == null){
                filterChain.doFilter(request, response);
                return;
            }
            // 유저의 이메일을  UsernamePasswordAuthenticationToken에 넣는다
            //2024.5.15 org.springframework.security.authentication.InsufficientAuthenticationException발생.
            // UsernamePasswordAuthenticationToken 의 두번째 인자로 비밀번호를 전달했어야하고, 이메일만으로 인증한다 하더라도 null값으로 전달했어야 했다.
            // 결론은 두번째 인자로 비밀번호에 대한 아무런 값도 전달해주지 않아서 계속 인증에 실패함.
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email,null, AuthorityUtils.NO_AUTHORITIES);

            // 웹인증 세부정보 소스.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 필터에서 인증을 거친 데이터를  컨텍스트를 통해서 컨트롤러로 전달한다고함.
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(securityContext);
        }catch (Exception e){
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
        }
        // 다음 필터로 넘김.
        filterChain.doFilter(request,response);
    }


    // Http request 헤더에 있는 Authorization 필드에 대한 값  즉 토큰이 Bearer인지 확인
    private String parseBearerToken(HttpServletRequest req){
        String token = "";
        try {
            String authorization = req.getHeader("Authorization");  // header에 Autorization에 저장된 토큰을 가져옴.
            boolean hasAuthorization = StringUtils.hasText(authorization);

            if (!hasAuthorization) return null;

            boolean isBearer = authorization.startsWith("Bearer");

            if (!isBearer) return null;

            token = authorization.substring(7); // Bearer token본문 형태로 헤더에 세팅됨.


        }catch (Exception e){
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
        }

        return token;
    }
}

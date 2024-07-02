package com.persnal.boardback;

import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.WorkLogger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@PropertySource("classpath:application.properties")
public class JwtProvider {
    // 1) 토큰발급 2) 인증  담당 클래스.
    Logger logger = WorkLogger.getInstance().getLogger();

    // 스프링 Bean 으로 이 클래스가 등록이 되려면 생성자가 한번 호출되어야 하는데,초기화 순서에 따르면
    // 1) 초기화 블럭 2) 전역변수의 기본값 3) 생성자 순이므로 항상 null이 나올 수 밖에 없는 구조임.
    @Value("${secret.key}")
    private String key;
    private SecretKey SECRET_KEY;


    //private SecretKey SECRET_KEY = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());


    //PostConstruct 어노테이션은 클래스가 스프링 Bean으로 등록된 이후에 무조껀 1번호출을 보장한다.
    //따라서 key값에 대한 프로퍼티를 불러오고나서 필요한 전역 변수를 초기화 할 수 잇다.
    @PostConstruct
    public void init() {
        SECRET_KEY = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String create(String email) {
        String key = "";
        try {
            Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

            key = Jwts.builder()
                   // .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .setSubject(email)
                    .setIssuedAt(Date.from(Instant.now())).setExpiration(expireDate)
                    .signWith(SECRET_KEY)
                    .compact();
        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
        }


        return key;
    }


    public String validate(String jwt) {
        String subject = "";
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            subject = claims.getSubject();
        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
        }
        return subject;
    }


}

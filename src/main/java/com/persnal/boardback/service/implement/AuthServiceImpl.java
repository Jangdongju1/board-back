package com.persnal.boardback.service.implement;

import com.persnal.boardback.JwtProvider;
import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.DBLogger;
import com.persnal.boardback.dto.request.auth.SignInRequest;
import com.persnal.boardback.dto.request.auth.SignUpRequest;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.dto.response.auth.SignInResponse;
import com.persnal.boardback.dto.response.auth.SignUpResponse;
import com.persnal.boardback.entity.UserEntity;
import com.persnal.boardback.repository.UserRepository;
import com.persnal.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


// service 의 구현체
@Service
@RequiredArgsConstructor  // fianl필드와  @Notnull 어노테이션이 붙은 필드를 초기화 하는 생성자를 생성한다.
public class AuthServiceImpl implements AuthService {
    // Logger
    Logger logger = DBLogger.getInstance().getLogger();
    // @RequiredArgsConstructor 에 의한 생성자 주입
    private final UserRepository userRepository;
    private final JwtProvider provider;
    // password Encoder
    private final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponse> signUp(SignUpRequest req) {
        try {
//            //email,nickname, telNum에 대한 중복 체크
            String email = req.getUserEmail();
            if (userRepository.existsByUserEmail(email)) return SignUpResponse.duplicateEmail();

            String nickname = req.getNickname();
            if (userRepository.existsByNickname(nickname)) return SignUpResponse.duplicateNickname();

            String telNum = req.getTelNumber();
            if(userRepository.existsByTelNumber(telNum)) return SignUpResponse.duplicateTelNum();

            // 비밀번호 인코딩(암호화) >> 다시 셋팅
            String encodedPassword = PASSWORD_ENCODER.encode(req.getPassword());
            req.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(req);

            // Entity == DB상의 1개의 레코드를 객체화 해놓은 것.
            userRepository.save(userEntity);


        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }

        return SignUpResponse.success();
    }

    @Override
    public ResponseEntity<? super SignInResponse> signIn(SignInRequest req) {
        String token = null;
        try {
            String email = req.getUserEmail();
           UserEntity entity =  userRepository.findAllByUserEmail(email);
           if (entity == null) return SignInResponse.failure();

           boolean isMatched = PASSWORD_ENCODER.matches(req.getPassword(),entity.getPassword());

           // 비밀번호 미스매치
           if (!isMatched) return SignInResponse.failure();

           // 비밀번호 일치시 JWT토큰 발급
            token = provider.create(email);

        }catch (Exception e){
            logger.error(GlobalVariable.logPattern, getClass().getName(),Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }

        return SignInResponse.success(token);
    }
}

package com.persnal.boardback.service.implement;

import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.DBLogger;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import com.persnal.boardback.entity.UserEntity;
import com.persnal.boardback.repository.UserRepository;
import com.persnal.boardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = DBLogger.getInstance().getLogger();
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponse> getSignInUser(String email) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findAllByUserEmail(email);

            if (userEntity == null) return GetSignInUserResponse.notExist();

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetSignInUserResponse.success(userEntity);
    }
}

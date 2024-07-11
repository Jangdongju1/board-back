package com.persnal.boardback.service.implement;

import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.DBLogger;
import com.persnal.boardback.dto.request.user.PatchNicknameRequest;
import com.persnal.boardback.dto.request.user.PatchProfileImageRequest;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import com.persnal.boardback.dto.response.user.GetUserResponse;
import com.persnal.boardback.dto.response.user.PatchProfileImageResponse;
import com.persnal.boardback.dto.response.user.PatchUserNicknameResponse;
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

    @Override
    public ResponseEntity<? super GetUserResponse> getUser(String email) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserEmail(email);
            if (userEntity == null) return GetUserResponse.notExistUser();

        }catch (Exception e){
            logger.error(GlobalVariable.logPattern, getClass().getName(),Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetUserResponse.success(userEntity);
    }

    @Override
    public ResponseEntity<? super PatchUserNicknameResponse> patchNickname(PatchNicknameRequest req, String email) {
        try {
            UserEntity userEntity = userRepository.findByUserEmail(email);
            if (userEntity == null) return PatchUserNicknameResponse.notExistUser();

            boolean existedNickname = userRepository.existsByNickname(req.getNickname());
            if (existedNickname) return PatchUserNicknameResponse.duplicateNickName();

            userEntity.setNickname(req.getNickname());

            userRepository.save(userEntity);

        }catch (Exception e){
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return PatchUserNicknameResponse.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponse> patchProfileImage(PatchProfileImageRequest req, String email) {
        try {
            UserEntity userEntity = userRepository.findByUserEmail(email);
            if (userEntity == null) return PatchProfileImageResponse.notExistUser();

            userEntity.setProfileImg(req.getProfileImg());

            userRepository.save(userEntity);

        }catch (Exception e){
            logger.error(GlobalVariable.logPattern,getClass().getName(),Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return PatchProfileImageResponse.success();
    }
}

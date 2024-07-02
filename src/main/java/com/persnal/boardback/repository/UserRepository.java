package com.persnal.boardback.repository;

import com.persnal.boardback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    //JPA repository 제네릭타입은  <entity,primary key Type>
    // 쿼리메서드 정의
    /*
    * 네이밍 규칙
    * 1.자동으로 쿼리를 생성하므로. 선언된 필드네임을 카멜케이스로 일치하도록 표기해야함.
    * */

    boolean existsByUserEmail(String userEmail);
    boolean existsByNickname(String nickname);
    boolean existsByTelNumber(String telNumber);
    UserEntity findAllByUserEmail(String userEmail);

}

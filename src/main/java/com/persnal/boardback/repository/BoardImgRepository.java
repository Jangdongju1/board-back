package com.persnal.boardback.repository;

import com.persnal.boardback.entity.BoardImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardImgRepository  extends JpaRepository<BoardImgEntity, Integer> {
    //Entity란 DB상의 하나의 레코드에 해당하므로 결과가 여러개인 경우 당연히List로 반환하도록 지정할 수도 있음.
    List<BoardImgEntity>findByBoardNum(int boarNum);
    // @Transactional 하나의 작업(트랜젝션)으로 묶기 위한 어노테이션.
    @Transactional
    void deleteByBoardNum(int boardNum);
}

package com.persnal.boardback.repository;

import com.persnal.boardback.entity.BoardEntity;
import com.persnal.boardback.repository.reusltSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    // JPA상의 네이티브 쿼리문법중 ?숫자 ex)?1 은 해당자리에 첫번째 매개변수를 대입한다는 의미이다.
    @Query(value = "SELECT "+
            "B.boardNum, "+
            "B.title, "+
            "B.content, "+
            "B.writeDate, "+
            "B.userEmail AS writerEmail, "+
            "U.nickname AS writerNickname, "+
            "U.profileImg AS writerProfileImg "+
            "FROM board AS B INNER JOIN user AS U " +
            "ON B.userEmail = U.userEmail " +
            "WHERE boardNum = ?1", nativeQuery = true)
    GetBoardResultSet getBoard(int boardNum);
    boolean existsByBoardNum(int boardNum);
    BoardEntity findByBoardNum(int boardNum);
    List<BoardEntity> findByOrderByWriteDateDesc();
    List<BoardEntity> findTop3ByWriteDateGreaterThanOrderByFavoriteCntDescCommentCntDescViewCntDescWriteDateDesc(String writeDate);
    List<BoardEntity> findByTitleContainsOrContentContainsOrderByWriteDateDesc(String title, String content);
    List<BoardEntity> findAllByUserEmailOrderByWriteDateDesc(String userEmail);
}

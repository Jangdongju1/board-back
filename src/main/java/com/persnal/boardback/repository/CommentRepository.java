package com.persnal.boardback.repository;

import com.persnal.boardback.entity.CommentEntity;
import com.persnal.boardback.repository.reusltSet.GetCommentListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(value =
            "SELECT " +
                    "U.nickname, " +
                    "U.profileImg, " +
                    "C.writeDate, " +
                    "C.content " +
                    "FROM comment AS C " +
                    "INNER JOIN user AS U " +
                    "ON C.userEmail = U.userEmail " +
                    "WHERE C.boardNum = ?1 " +
                    "ORDER BY C.writeDate DESC"
            , nativeQuery = true)
    List<GetCommentListResultSet> getCommentList(int boardNum);

    @Transactional
    void deleteByBoardNum(int boardNum);

}

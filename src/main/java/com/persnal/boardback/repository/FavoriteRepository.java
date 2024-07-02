package com.persnal.boardback.repository;

import com.persnal.boardback.entity.FavoriteEntity;
import com.persnal.boardback.entity.primaryKey.FavoritePk;
import com.persnal.boardback.repository.reusltSet.GetFavoriteListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {
    FavoriteEntity findByBoardNumAndUserEmail(int boardNum, String userEmail);
    @Query(value = "" +
            "SELECT " +
            "U.userEmail, " +
            "U.nickname, " +
            "U.profileImg " +
            "FROM " +
            "USER AS U " +
            "INNER JOIN favorite AS F " +
            "ON U.userEmail = F.userEmail " +
            "WHERE F.boardNum = ?1", nativeQuery = true)
    List<GetFavoriteListResultSet> getFavoriteList(int boardNum);

    @Transactional
    void deleteByBoardNum(int boardNum);

}

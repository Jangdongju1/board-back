package com.persnal.boardback.repository;

import com.persnal.boardback.entity.SearchWordEntity;
import com.persnal.boardback.repository.reusltSet.GetPopularSearchWordResultSet;
import com.persnal.boardback.repository.reusltSet.GetRelationListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchWordRepository extends JpaRepository<SearchWordEntity, Integer> {
    @Query(value =
            "SELECT " +
                    "searchword, " +
                    "count(searchword) AS count " +
                    "FROM searchword " +
                    "GROUP BY searchWord " +
                    "ORDER BY count DESC", nativeQuery = true)
    List<GetPopularSearchWordResultSet> getPopularSearchWord();

    @Query(value =
            "SELECT " +
                    "relationWord AS word, " +
                    "COUNT(relationWord) AS count " +
                    "FROM searchWord " +
                    "WHERE searchWord = ?1 AND " +
                    "relationWord IS NOT NULL "+
                    "GROUP BY word " +
                    "ORDER BY count DESC " +
                    "LIMIT 15", nativeQuery = true)
    List<GetRelationListResultSet> getRelationSearchWord(String searchWord);
}

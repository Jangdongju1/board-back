package com.persnal.boardback.repository;

import com.persnal.boardback.entity.SearchWordEntity;
import com.persnal.boardback.repository.reusltSet.GetPopularSearchWordResultSet;
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
                    "ORDER BY count DESC",nativeQuery = true)
    List<GetPopularSearchWordResultSet> getPopularSearchWord();
}

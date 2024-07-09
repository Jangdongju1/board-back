package com.persnal.boardback.service;

import com.persnal.boardback.dto.response.search.GetPopularListResponse;
import com.persnal.boardback.dto.response.search.GetRelationListResponse;
import org.springframework.http.ResponseEntity;


public interface SearchService {
    ResponseEntity<? super GetPopularListResponse> getPopularList();
    ResponseEntity<? super GetRelationListResponse> getRelationList(String searchWord);
}

package com.persnal.boardback.service;

import com.persnal.boardback.dto.response.search.GetPopularListResponse;
import org.springframework.http.ResponseEntity;


public interface SearchService {
    ResponseEntity<? super GetPopularListResponse> getPopularList();
}

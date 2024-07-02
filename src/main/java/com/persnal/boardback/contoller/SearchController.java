package com.persnal.boardback.contoller;

import com.persnal.boardback.dto.response.search.GetPopularListResponse;
import com.persnal.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponse> getPopularList() {
        ResponseEntity<? super GetPopularListResponse>  response = searchService.getPopularList();
        return response;
    }


}

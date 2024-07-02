package com.persnal.boardback.dto.response.search;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.repository.reusltSet.GetFavoriteListResultSet;
import com.persnal.boardback.repository.reusltSet.GetPopularSearchWordResultSet;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetPopularListResponse extends ResponseDto {
    private List<String> popularWordList;

    private GetPopularListResponse(List<GetPopularSearchWordResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        popularWordList = new ArrayList<>();
        for (GetPopularSearchWordResultSet resultSet : resultSets) {
            popularWordList.add(resultSet.getSearchWord());
        }
    }

    public static ResponseEntity<GetPopularListResponse> success(List<GetPopularSearchWordResultSet> resultSets) {
        GetPopularListResponse response = new GetPopularListResponse(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

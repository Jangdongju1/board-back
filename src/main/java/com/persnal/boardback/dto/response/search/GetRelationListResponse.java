package com.persnal.boardback.dto.response.search;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.repository.reusltSet.GetRelationListResultSet;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetRelationListResponse extends ResponseDto {
    List<String> relativeWordList;

    private GetRelationListResponse(List<GetRelationListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        relativeWordList = new ArrayList<>();
        for (GetRelationListResultSet resultSet : resultSets) {
            relativeWordList.add(resultSet.getWord());
        }
    }

    public static ResponseEntity<GetRelationListResponse> success(List<GetRelationListResultSet> resultSets){
        GetRelationListResponse response = new GetRelationListResponse(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

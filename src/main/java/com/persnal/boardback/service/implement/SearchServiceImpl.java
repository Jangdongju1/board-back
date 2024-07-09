package com.persnal.boardback.service.implement;
import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.DBLogger;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.dto.response.search.GetPopularListResponse;
import com.persnal.boardback.dto.response.search.GetRelationListResponse;
import com.persnal.boardback.repository.SearchWordRepository;
import com.persnal.boardback.repository.reusltSet.GetPopularSearchWordResultSet;
import com.persnal.boardback.repository.reusltSet.GetRelationListResultSet;
import com.persnal.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final Logger logger = DBLogger.getInstance().getLogger();
    private final SearchWordRepository searchWordRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponse> getPopularList() {
        List<GetPopularSearchWordResultSet> resultSets = new ArrayList<>();
        try {
            resultSets = searchWordRepository.getPopularSearchWord();

        }catch (Exception e){
            logger.error(GlobalVariable.logPattern,getClass().getName(), Utils.getStackTrace(e));
            ResponseDto.databaseError();
        }
        return GetPopularListResponse.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponse> getRelationList(String searchWord) {
        List<GetRelationListResultSet> resultSets = new ArrayList<>();
        try {
            resultSets = searchWordRepository.getRelationSearchWord(searchWord);
        }catch (Exception e){
            logger.error(GlobalVariable.logPattern,getClass().getName(), Utils.getStackTrace(e));
            ResponseDto.databaseError();
        }
        return GetRelationListResponse.success(resultSets);
    }
}

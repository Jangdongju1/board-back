package com.persnal.boardback.service;

import com.persnal.boardback.dto.request.board.PatchBoardUpdateRequest;
import com.persnal.boardback.dto.request.board.PostBoardRequest;
import com.persnal.boardback.dto.request.board.PostCommentRequest;
import com.persnal.boardback.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponse> getBoard(int boardNum);
    ResponseEntity<? super GetFavoriteListResponse> getFavoriteList(int boardNum);
    ResponseEntity<? super GetCommentListResponse> getCommentList(int boardNum);
    ResponseEntity<? super GetLatestBoardListResponse> getLatestBoardListItem();
    ResponseEntity<? super GetTop3BoardListResponse> getTop3BoardList();
    ResponseEntity<? super PostBoardResponse> postBoard(PostBoardRequest req, String email);
    ResponseEntity<? super PostCommentResponse> postComment(PostCommentRequest req, String email, int boardNum);
    ResponseEntity<? super PutFavoriteResponse> putFavorite(int boardNum, String email);
    ResponseEntity<? super GetIncreaseViewCountResponse> increaseViewCount(int boardNum);
    ResponseEntity<? super DeleteBoardResponse> deleteBoard(int boardNum, String email);
    ResponseEntity<? super PatchBoardUpdateResponse> patchBoard(int boardNum, PatchBoardUpdateRequest req, String email);
}

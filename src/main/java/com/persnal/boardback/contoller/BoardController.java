package com.persnal.boardback.contoller;

import com.persnal.boardback.dto.request.board.PatchBoardUpdateRequest;
import com.persnal.boardback.dto.request.board.PostBoardRequest;
import com.persnal.boardback.dto.request.board.PostCommentRequest;
import com.persnal.boardback.dto.response.board.*;
import com.persnal.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{boardNum}")
    public ResponseEntity<? super GetBoardResponse> getBoard(@PathVariable Integer boardNum) {
        ResponseEntity<? super GetBoardResponse> response = boardService.getBoard(boardNum);
        return response;
    }

    @GetMapping("user-board-list/{email}")
    public ResponseEntity<? super GetUserBoardResponse> getUserBoard(@PathVariable("email") String email) {
        ResponseEntity<? super GetUserBoardResponse> response = boardService.getUserBoard(email);
        return response;

    }


    @PostMapping("")
    public ResponseEntity<? super PostBoardResponse> postBoard(@Validated @RequestBody PostBoardRequest postBoardRequest,
                                                               @AuthenticationPrincipal String email) {
        ResponseEntity<? super PostBoardResponse> response = boardService.postBoard(postBoardRequest, email);
        return response;
    }

    @PostMapping("/{boardNum}/comment")
    public ResponseEntity<? super PostCommentResponse> postComment(@PathVariable("boardNum") int boardNum,
                                                                   @AuthenticationPrincipal String email,
                                                                   @Validated @RequestBody PostCommentRequest req) {
        ResponseEntity<? super PostCommentResponse> response = boardService.postComment(req, email, boardNum);
        return response;
    }

    @GetMapping("/{boardNum}/comment-list")
    public ResponseEntity<? super GetCommentListResponse> getCommentList(@PathVariable("boardNum") int boardNum) {
        ResponseEntity<? super GetCommentListResponse> response = boardService.getCommentList(boardNum);
        return response;
    }

    @PutMapping("/{boardNum}/favorite")
    public ResponseEntity<? super PutFavoriteResponse> putFavorite(@PathVariable Integer boardNum,
                                                                   @AuthenticationPrincipal String email) {
        ResponseEntity<? super PutFavoriteResponse> response = boardService.putFavorite(boardNum, email);
        return response;
    }

    @GetMapping("/{boardNum}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponse> getFavoriteList(@PathVariable("boardNum") int boardNum) {
        ResponseEntity<? super GetFavoriteListResponse> response = boardService.getFavoriteList(boardNum);
        return response;
    }


    @GetMapping("/{boardNum}/increase-view-count")
    public ResponseEntity<? super GetIncreaseViewCountResponse> increaseViewCount(@PathVariable("boardNum") int boardNum) {
        ResponseEntity<? super GetIncreaseViewCountResponse> response = boardService.increaseViewCount(boardNum);
        return response;
    }

    @GetMapping("latest-list")
    public ResponseEntity<? super GetLatestBoardListResponse> getLatestBoardList() {
        ResponseEntity<? super GetLatestBoardListResponse> response = boardService.getLatestBoardListItem();

        return response;
    }

    @GetMapping("/top-3")
    public ResponseEntity<? super GetTop3BoardListResponse> getTop3BoardList() {
        ResponseEntity<? super GetTop3BoardListResponse> response = boardService.getTop3BoardList();
        return response;
    }

    @GetMapping(value = {"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
    public ResponseEntity<? super GetSearchBoardListResponse> getSearchBoardList(
            @PathVariable(value = "searchWord") String searchWord,
            @PathVariable(value = "preSearchWord", required = false) String preSearchWord) {
        ResponseEntity<? super GetSearchBoardListResponse> response = boardService.getSearchBoardList(searchWord, preSearchWord);
        return response;
    }

    @DeleteMapping("/{boardNum}")
    public ResponseEntity<? super DeleteBoardResponse> deleteBoard(@PathVariable("boardNum") int boardNum,
                                                                   @AuthenticationPrincipal String email) {
        ResponseEntity<? super DeleteBoardResponse> response = boardService.deleteBoard(boardNum, email);
        return response;
    }

    @PatchMapping("/{boardNum}")
    public ResponseEntity<? super PatchBoardUpdateResponse> patchBoard(@PathVariable("boardNum") int boardNum,
                                                                       @Validated @RequestBody PatchBoardUpdateRequest req,
                                                                       @AuthenticationPrincipal String email) {
        ResponseEntity<? super PatchBoardUpdateResponse> resposne = boardService.patchBoard(boardNum, req, email);
        return resposne;
    }

}

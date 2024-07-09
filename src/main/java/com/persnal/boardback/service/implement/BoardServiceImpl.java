package com.persnal.boardback.service.implement;

import com.persnal.boardback.common.GlobalVariable;
import com.persnal.boardback.common.Utils;
import com.persnal.boardback.common.logger.DBLogger;
import com.persnal.boardback.dto.request.board.PatchBoardUpdateRequest;
import com.persnal.boardback.dto.request.board.PostBoardRequest;
import com.persnal.boardback.dto.request.board.PostCommentRequest;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.dto.response.board.*;
import com.persnal.boardback.entity.*;
import com.persnal.boardback.repository.*;
import com.persnal.boardback.repository.reusltSet.GetBoardResultSet;
import com.persnal.boardback.repository.reusltSet.GetCommentListResultSet;
import com.persnal.boardback.repository.reusltSet.GetFavoriteListResultSet;
import com.persnal.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final Logger logger = DBLogger.getInstance().getLogger();
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardImgRepository boardImgRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final SearchWordRepository searchWordRepository;
    private final BoardListViewRepository boardListViewRepository;


    // 특정 게시물의 정보를 가져오는 작업.  1) 특정게시물의 정보 join 유저정보 2) 게시물 조회시 조회수 UP
    @Override
    public ResponseEntity<? super GetBoardResponse> getBoard(int boardNum) {
        GetBoardResultSet resultSet = null;
        List<BoardImgEntity> imageEntities = new ArrayList<>();
        try {
            resultSet = boardRepository.getBoard(boardNum);
            if (resultSet == null) return GetBoardResponse.notExistBoard();

            imageEntities = boardImgRepository.findByBoardNum(boardNum);

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetBoardResponse.success(resultSet, imageEntities);
    }

    @Override
    public ResponseEntity<? super GetUserBoardResponse> getUserBoard(String email) {
        List<BoardEntity> userBoardEntities = null;
        try {
            boolean existedUser = userRepository.existsByUserEmail(email);
            if (!existedUser) return GetUserBoardResponse.notExistUser();
            userBoardEntities = boardRepository.findAllByUserEmailOrderByWriteDateDesc(email);

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetUserBoardResponse.success(userBoardEntities);
    }

    // 게시물의 게시를 담당하는 작업. 1)게시글의 내용을 저장 2) 이미지 리스트를 저장.
    @Override
    public ResponseEntity<? super PostBoardResponse> postBoard(PostBoardRequest req, String email) {
        try {
            UserEntity userEntity = userRepository.findAllByUserEmail(email);
            if (userEntity == null) return PostBoardResponse.notExistUser();

            BoardEntity boardEntity = new BoardEntity(req, email, userEntity);
            boardRepository.save(boardEntity);

            int boardNum = boardEntity.getBoardNum();

            // 이미지 리스트를 저장하는 작업.
            List<BoardImgEntity> imgEntities = new ArrayList<>();

            for (String imgName : req.getBoardImgList()) {
                BoardImgEntity imgEntity = new BoardImgEntity(boardNum, imgName);
                imgEntities.add(imgEntity);
            }

            boardImgRepository.saveAll(imgEntities);


        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return PostBoardResponse.success();
    }

    @Override
    public ResponseEntity<? super PostCommentResponse> postComment(PostCommentRequest req, String email, int boardNum) {
        try {
            // 존재하는 게시물인지 확인.
            BoardEntity boardEntity = boardRepository.findByBoardNum(boardNum);
            if (boardEntity == null) return PostCommentResponse.notExistBoard();
            // 존재하는 유저인지 확인.
            boolean existedUser = userRepository.existsByUserEmail(email);
            if (!existedUser) return PostCommentResponse.notExistUser();

            CommentEntity commentEntity = new CommentEntity(req, email, boardNum);

            // 댓글저장.
            commentRepository.save(commentEntity);

            // 게시글에서 댓글수 +1 저장.
            boardEntity.increaseCommentCnt();
            boardRepository.save(boardEntity);


        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return PostCommentResponse.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponse> putFavorite(int boardNum, String email) {
        try {
            // 토큰 확인후 존재하지 않는 유저인경우 응답처리
            boolean existedEmail = userRepository.existsByUserEmail(email);
            if (!existedEmail) return PutFavoriteResponse.notExistUser();

            // 존재하지 않는 게시물인 경우의  응답처리.
            BoardEntity boardEntity = boardRepository.findByBoardNum(boardNum);
            if (boardEntity == null) return PutFavoriteResponse.notExistBoard();

            // 좋아요는 1개의 개시물에 1번만 할 수 잇다.
            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumAndUserEmail(boardNum, email);
            if (favoriteEntity == null) { //좋아요를 클릭하지 않은경우.
                favoriteEntity = new FavoriteEntity(email, boardNum);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCnt();     // 게시물상의 좋아요 +1

            } else {// 좋아요를 클릭한 경우.
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCnt();        // 게시물상의 좋아요 -1
            }
            boardRepository.save(boardEntity);  // 좋아요 적용 후 다시 저장.

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponse.success();
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponse> getFavoriteList(int boardNum) {
        List<GetFavoriteListResultSet> resultSets = null;
        try {
            //게시물이  없는 경우에 관한 처리.
            boolean existedBoard = boardRepository.existsByBoardNum(boardNum);
            if (!existedBoard) return GetFavoriteListResponse.notExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardNum);

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponse.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponse> getCommentList(int boardNum) {
        List<GetCommentListResultSet> resultSets = null;
        try {
            boolean existedBoard = boardRepository.existsByBoardNum(boardNum);
            if (!existedBoard) return GetCommentListResponse.notExistBoard();

            resultSets = commentRepository.getCommentList(boardNum);
        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetCommentListResponse.success(resultSets);
    }

    // 게시물의 조회수 업데이트
    @Override
    public ResponseEntity<? super GetIncreaseViewCountResponse> increaseViewCount(int boardNum) {
        try {
            // 게시물의 조회수 +1
            BoardEntity boardEntity = boardRepository.findByBoardNum(boardNum);
            if (boardEntity == null) return GetIncreaseViewCountResponse.notExistBoard();

            boardEntity.increaseViewCnt();
            boardRepository.save(boardEntity);
        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }

        return GetIncreaseViewCountResponse.success();
    }

    // 게시물 삭제.
    @Override
    public ResponseEntity<? super DeleteBoardResponse> deleteBoard(int boardNum, String email) {
        try {
            boolean existedUser = userRepository.existsByUserEmail(email);
            if (!existedUser) return DeleteBoardResponse.notExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNum(boardNum);
            if (boardEntity == null) return DeleteBoardResponse.notExistBoard();

            boolean isWriter = boardEntity.getUserEmail().equals(email);

            // 토큰 서브젝트와의 검증.
            if (!isWriter) return DeleteBoardResponse.notPermitted();

            //1) 게시물정보 2)업로드된 사진, 3)댓글, 4)좋아요 모두 지워주어야 함.
            boardImgRepository.deleteByBoardNum(boardNum);
            commentRepository.deleteByBoardNum(boardNum);
            favoriteRepository.deleteByBoardNum(boardNum);
            boardRepository.delete(boardEntity);


        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }

        return DeleteBoardResponse.sucess();
    }

    // 게시물 수정.
    @Override
    public ResponseEntity<? super PatchBoardUpdateResponse> patchBoard(int boardNum, PatchBoardUpdateRequest req, String email) {
        try {
            boolean isExistedUser = userRepository.existsByUserEmail(email);
            if (!isExistedUser) return PatchBoardUpdateResponse.notExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNum(boardNum);
            if (boardEntity == null) return PatchBoardUpdateResponse.notExistBoard();

            boolean isWriter = boardEntity.getUserEmail().equals(email);
            if (!isWriter) return PatchBoardUpdateResponse.noPermission();

            // 게시글 제목 및 내용 수정.
            boardEntity.patchBoard(req);
            boardRepository.save(boardEntity);

            // 업로드된 이미지 수정.
            boardImgRepository.deleteByBoardNum(boardNum);
            List<String> boardImgList = req.getBoardImgList();
            List<BoardImgEntity> boardImgEntities = new ArrayList<>();

            for (String image : boardImgList) {
                BoardImgEntity entity = new BoardImgEntity(boardNum, image);
                boardImgEntities.add(entity);
            }

            boardImgRepository.saveAll(boardImgEntities);


        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return PatchBoardUpdateResponse.success();
    }

    // 최신 게시글에 대한 작업
    @Override
    public ResponseEntity<? super GetLatestBoardListResponse> getLatestBoardListItem() {
        List<BoardEntity> boardEntityList = new ArrayList<>();
        try {
            boardEntityList = boardRepository.findByOrderByWriteDateDesc();
        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetLatestBoardListResponse.success(boardEntityList);
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponse> getTop3BoardList() {
        List<BoardEntity> list = new ArrayList<>();
        try {
            LocalDateTime beforeOneWeek = LocalDateTime.now().minusDays(7);
            String time = beforeOneWeek.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            list = boardRepository.findTop3ByWriteDateGreaterThanOrderByFavoriteCntDescCommentCntDescViewCntDescWriteDateDesc(time);
        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetTop3BoardListResponse.success(list);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponse> getSearchBoardList(String searchWord, String preSearchWord) {
        List<BoardEntity> entities = new ArrayList<>();
        try {
            entities = boardRepository.findByTitleContainsOrContentContainsOrderByWriteDateDesc(searchWord, searchWord);
            // 검색 로그 남기기.
            SearchWordEntity searchWordEntity = new SearchWordEntity(searchWord, preSearchWord, false);
            searchWordRepository.save(searchWordEntity);
            // 이전 검색어가 존재하는가?
            boolean relation = preSearchWord != null;
            if (relation) {
                searchWordEntity = new SearchWordEntity(preSearchWord, searchWord, relation);
                searchWordRepository.save(searchWordEntity);
            }

        } catch (Exception e) {
            logger.error(GlobalVariable.logPattern, getClass().getName(), Utils.getStackTrace(e));
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponse.success(entities);
    }
}

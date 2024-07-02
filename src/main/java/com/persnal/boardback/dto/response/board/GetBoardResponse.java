package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.entity.BoardImgEntity;
import com.persnal.boardback.repository.reusltSet.GetBoardResultSet;
import lombok.Getter;
import org.hibernate.query.sqm.function.JdbcEscapeFunctionDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBoardResponse extends ResponseDto {
    // 게시물 상세 페이지의 게시글 정보에 대한 응답객체.
    private int boardNum = 0;
    private String title = "";
    private String content = "";
    private List<String> boardImgList;
    private String writeDate = "";
    private String writerEmail = "";
    private String writerNickname = "";
    private String writerProfileImg = "";



    private GetBoardResponse(GetBoardResultSet resultSet, List<BoardImgEntity> imageEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        List<String> boardImgList = new ArrayList<>();
        for(BoardImgEntity entity : imageEntities){
            String img = entity.getImage();
            boardImgList.add(img);
        }
        this.boardNum =  resultSet.getBoardNum();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.writeDate = resultSet.getWriteDate();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImg = resultSet.getWriterProfileImg();
        this.boardImgList = boardImgList;
    }

    public static ResponseEntity<GetBoardResponse> success(GetBoardResultSet resultSet, List<BoardImgEntity> imageEntities){
        GetBoardResponse result = new GetBoardResponse(resultSet, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}

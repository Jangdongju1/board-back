package com.persnal.boardback.dto.request.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thymeleaf.spring6.processor.SpringObjectTagProcessor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequest { // 글쓰기 관련 Dto
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull  // null일 수는 없으나 빈값은 허용함.
    private List<String> boardImgList;

    // 요3개 누락됨. 
//    private String nickname  = "";
//    private String boardTitmeImg = "";
//    private String writerProfileImg = "";
}

package com.persnal.boardback.common;

import lombok.Getter;

public class Constants {


    public enum ApiEndPoint{
        SIGN_IN("/api/v1/auth/sign-in"),  // POST
        SIGN_UP("/api/v1/auth/sign-up"), // POST
        TOP3("/api/v1/board/top-3"), //GET
        CURRENT_LIST("/api/v1/board/current-list/{pageNumber}"), //GET
        POPULAR_WORDS("/api/v1/search/popular-list"), //GET
        SEARCH_LIST("/api/v1/board/search-list/{searchWord}"),//GET
        RELATED_SEARCH_KEYWORD("/api/v1/search/{searchWord}/relation-list"), //GET
        BOARD_DETAIL("/api/v1/board/{boardNumber}"), //GET
        FAVORITE_LIST("/api/v1/board/{boardNumber}/favorite-list"), //GET
        FAVORITE("/api/v1/board/{boardNumber}/favorite"), //PUT
        COMMENT("/api/v1/board/{boardNumber}/comment-list"), //GET
        DELETE("/api/v1/board/{boardNumber}"),  //DELTE
        UPDATE("/api/v1/board/{boardNumber}"), // PATCH
        GET_USER_INFO(" /api/v1/user/{email}"), //GET
        GET_USER_BOARD_LIST("/api/v1/board/user-board-list/{email}"),//GET
        BOARD_WRITE("/api/board"), //POST
        PATCH_NICK(" /api/v1/user/nickname"),//PACTH
        PATCH_PROFILEIMG("/api/v1/user/profile-image"); //PATCH



        private String endPoint;

        private ApiEndPoint(String point){
            this.endPoint = point;
        }

    }
}



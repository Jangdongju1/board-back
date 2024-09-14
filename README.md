# Bulletin Board BackEnd 
##### 📺 개발환경
* <img src="https://img.shields.io/badge/Framework-%23121011?style=plastic"/>
     <div>
         <img src="https://img.shields.io/badge/springboot-6DB33F?style=float-square&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/3.2.5-515151?style=float-square">, <img/ src="https://img.shields.io/badge/Spring Security-6DB33F?style=float-square&logo=springsecurity&logoColor=white"><img src="https://img.shields.io/badge/3.2.5-515151?style=float-square">
     </div>

* <img src="https://img.shields.io/badge/Language-%23121011?style=plastic">
     <div>
          <img src="https://img.shields.io/badge/java-%23ED8B00?style=float-square&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=float-square">
     </div>
* <img src="https://img.shields.io/badge/Database%20&%20ORM-%23121011?style=plastic"/>
     <div>
          <img src="https://img.shields.io/badge/MySQL-4479A1?style=float-square&logo=MySql&logoColor=white"><img src="https://img.shields.io/badge/8.0-515151?style=float-square">, <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=float-square&logo=Spring&logoColor=white"/><img src="https://img.shields.io/badge/3.2.5-515151?style=float-square">
     </div>

* <img src="https://img.shields.io/badge/Build-%23121011?style=plastic">
     <div>
          <img src="https://img.shields.io/badge/Gradle-02303A?style=float-square&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/8.7-515151?style=float-square">
     </div>

----------------------------------
##### 📖 ERD
----------------------------------
##### 📖 API 명세

* 게시물 관련(board)
  
| Method | Url | RequestBody | ResponseBody |
|:---:|:---:|---|---|
| GET | /api/v1/board/{boardNum} | |{<br/> &ensp;"boardNum" : 0,<br/> &ensp;"title" :"example",<br/> &ensp;"content" : "example",<br/> &ensp;"boardImgList" : "exampleUrl",<br/> &ensp;"writeDate":"2024-06-27 23:02:53",<br/> &ensp;"writerEmail":"example@example.com",<br/> &ensp;"writernickname":"example",<br/> &ensp;"writerProfileImg":"exampleUrl"<br/> }|
| GET | /api/v1/user-board-list/{email} | |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"userBoardList":[<br/>&emsp;{<br/> &emsp;&ensp;"boardNum" : 0,<br/> &emsp;&ensp;"title" :"example",<br/> &emsp;&ensp;"content" : "example",<br/> &emsp;&ensp;"writeDate":"2024-06-27 23:02:53",<br/> &emsp;&ensp;"nickname":"example",<br/> &emsp;&ensp;"boardTitleImg":"url",<br/> &emsp;&ensp;"favoriteCnt":0,<br/> &emsp;&ensp;"commentCnt":0,<br/> &emsp;&ensp;"viewCnt":0,<br/> &emsp;&ensp;"writerProfileImg":"url"<br/> &emsp;}<br/>&ensp;]<br/>} |
| GET | /api/v1/board/comment-list |  |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"example",<br/> &ensp;"commentList":[<br/>&emsp;{<br/> &emsp;&ensp;"nickname":"example",<br/> &emsp;&ensp;"profileImg":"url",<br/> &emsp;&ensp;"writeDate":"2024-06-27 23:02:53",<br/> &emsp;&ensp;"content":"example"<br/>&emsp;}<br/>&ensp;] <br/>}|
| GET | /api/v1/board/{boardNum}/favoite-list | |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"favoriteList":[<br/>&emsp;{<br/> &emsp;&ensp;"userEmail":"example@example.com",<br/> &emsp;&ensp;"nickname":"example",<br/> &emsp;&ensp;"profileImg":"url"<br/> &emsp;}<br/>&ensp;]<br/> } |  
| GET | /api/v1/board/{boardNum}/increase-view-count | |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success"<br/>}|
| GET | /api/v1/board/latest-list | | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"latestBoardList":[<br/>&emsp;{<br/> &emsp;&ensp;"boardNum" : 0,<br/> &emsp;&ensp;"title" :"example",<br/> &emsp;&ensp;"content" : "example",<br/> &emsp;&ensp;"writeDate":"2024-06-27 23:02:53",<br/> &emsp;&ensp;"nickname":"example",<br/> &emsp;&ensp;"boardTitleImg":"url",<br/> &emsp;&ensp;"favoriteCnt":0,<br/> &emsp;&ensp;"commentCnt":0,<br/> &emsp;&ensp;"viewCnt":0,<br/> &emsp;&ensp;"writerProfileImg":"url"<br/> &emsp;}<br/>&ensp;]<br/>} | 
| GET | /api/v1/board/top-3 | | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"top3BoardList":[<br/>&emsp;{<br/> &emsp;&ensp;"boardNum" : 0,<br/> &emsp;&ensp;"title" :"example",<br/> &emsp;&ensp;"content" : "example",<br/> &emsp;&ensp;"writeDate":"2024-06-27 23:02:53",<br/> &emsp;&ensp;"nickname":"example",<br/> &emsp;&ensp;"boardTitleImg":"url",<br/> &emsp;&ensp;"favoriteCnt":0,<br/> &emsp;&ensp;"commentCnt":0,<br/> &emsp;&ensp;"viewCnt":0,<br/> &emsp;&ensp;"writerProfileImg":"url"<br/> &emsp;}<br/>&ensp;]<br/>} | 
| POST | /api/v1/board |{<br/> &ensp;"title":"exmple",<br/> &ensp;"content":"example",<br/> &ensp;"boardImgList":["url1", "url2"]<br/> }| {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"suceess"<br/> }|
| POST | /api/v1/board/{boardNum}/comment | {<br/> &ensp;"comment":"example"<br/> } | {<br/> &ensp;"code":"SU",<br/> &ensp;"message" :"success"<br/> } | 
| PUT | /api/v1/board/{boardNum}/favorite | |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> }|
| PATCH | /api/v1/board/{boardNum} | {<br/> &ensp;"title":"example",<br/> &ensp;"content":"example",<br/> &ensp;"boardImgList":["url1","url2"]<br/> } | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"suceess"<br/> |  
| DELETE | /api/v1/board/{boardNum} | | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> } |


* 검색어 관련(search)

| Method | Url | RequestBody | ResponseBody | 
|:---:|:---:|---|---|
| GET | /api/v1/search/popular-list |  | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"popularWordList":["example1","example2"]<br/> } |
| GET | /api/v1/{searchWord}/relation-list |  | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"relativeWordList":["example1","example2"]<br/> } | 



* 회원가입 및 로그인(auth)
  
| Method | Url | RequestBody | ResponseBody |
|:---:|:---:|---|---|
| POST | /api/v1/auth/sign-up | {<br/> &ensp;"userEmail":"example@example.com",<br/> &ensp;"password":"test1234",<br/> &ensp;"nickname":"test",<br/> &ensp;"telNumber":"123-1234-1234",<br/> &ensp;"address":"example",<br/> &ensp;"addressDetail:"example",<br/> &ensp;"profileImg":"imageUrl",<br/> &ensp;"agreedPersonal": false<br/> }| {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success"<br/> } |
| POST | /api/v1/auth/sign-in | {<br/> &ensp;"userEmail":"example@example.com",<br/> &ensp;"password":"example"<br/> } | {<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"aceessToken":"JWT Token",<br/> &ensp;"expireTime": 3600<br/> }|


* 유저정보(user) 

| Method | Url | RequestBody | ResponseBody |
|:---:|:---:|---|---|
| GET | /api/v1/user |  | {<br/>&ensp;"code":"SU",<br/> &ensp;"message":"sucess",<br/> &ensp;"userEmail":"example@example.com",<br/> &ensp;"nickname":"thisis",<br/> &ensp;"profileImg":"imageUrl"<br/>} |
| GET | /api/v1/user/{email} | |{<br/>&ensp;"code":"SU",<br/> &ensp;"message":"sucess",<br/> &ensp;"userEmail":"example@example.com",<br/> &ensp;"nickname":"thisis",<br/> &ensp;"profileImg":"imageUrl"<br/>} |
| PATCH | /api/v1/user/nickname |{<br/>&ensp;"nickname":"test"<br/>} | {<br/> &ensp;"code" : "SU",<br/> &ensp;"message" : "success" <br/>} |
| PATCH | /api/v1/user/profile-image | {<br/> &ensp;"profileImg":"imageUrl" <br/>} | {<br/> &ensp;"code" : "SU",<br/> &ensp;"message" : "success" <br/>} |


* 파일업로드(file)

| Method | Url | RequestBody | ResponseBody | 
|:---:|:---:|---|---|
| POST | /api/v1/file/upload | Multipart Form Data | FileUrl(String) |

----------------------------------
##### 📖 응답코드 

| Code | Message | HttpStatus |
|:---:|:---:|:---:|
| SU | success | 200 | 
| VF | vaildation failure | 400 | 
| DE | duplicated email | 400 | 
| DN | duplicated nickname | 400 | 
| DT | duplicated telnumber | 400 | 
| NU | not exist user | 400 | 
| NB | not exist board | 400 |
| SF | sign in failed | 401 | 
| AF | authentication failed | 401 | 
| DE | database error | 500 | 



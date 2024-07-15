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

* <img src="https://img.shields.io/badge/Build-%23121011?style=plastic">
     <div>
          <img src="https://img.shields.io/badge/Gradle-02303A?style=float-square&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/8.7-515151?style=float-square">
     </div>

----------------------------------
##### 📖 API 명세


* 회원가입 및 로그인(auth)
  
| Method | Url | RequestBody | ResponseBody |
|:---:|:---:|---|---|
| POST | /api/v1/auth/sign-up | {<br/> "userEmail":"example@example.com",<br/> "password":"test1234",<br/> "nickname":"test",<br/> "telNumber":"123-1234-1234",<br/> "address":"example",<br/> "addressDetail:"example",<br/> "profileImg":"imageUrl",<br/> "agreedPersonal": false<br/> }| {<br/> "code":"SU",<br/> "message":"success"<br/> } |
| POST | /api/v1/auth/sign-in | {<br/> "userEmail":"example@example.com",<br/> "password":"example"<br/> } | {<br/> "code":"SU",<br/> "message":"success",<br/> "aceessToken":"JWT Token",<br/> "expireTime": 3600<br/> }|


* 게시물 관련(board)
  
| Method | Url | RequestBody | ResponseBody |
|:---:|:---:|---|---|
| GET | /api/v1/board/{boardNum} | |{<br/> "boardNum" : 0,<br/> "title" :"example",<br/> "content" : "example",<br/> "boardImgList" : "exampleUrl",<br/> "writeDate":"2024-06-27 23:02:53",<br/> "writerEmail":"example@example.com",<br/> "writernickname":"example",<br/> "writerProfileImg":"exampleUrl"<br/> }|
| GET | /api/v1/user-board-list/{email} | |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"success",<br/> &ensp;"userBoardList":[<br/>&emsp;{<br/> &emsp;&ensp;"boardNum" : 0,<br/> &emsp;&ensp;"title" :"example",<br/> &emsp;&ensp;"content" : "example",<br/> &emsp;&ensp;"boardImgList":["url1","url2"],<br/> &emsp;&ensp;"writeDate":"2024-06-27 23:02:53",<br/> &emsp;&ensp;"writerEmail":"example@example.com",<br/> &emsp;&ensp;"writernickname":"example",<br/> &emsp;&ensp;"writerProfileImg":"exampleUrl"<br/>&emsp;}<br/>&ensp;]<br/>} |
| POST | /api/v1/board |{<br/> &ensp;"title":"exmple",<br/> &ensp;"content":"example",<br/> &ensp;"boardImgList":["url1", "url2"]<br/> }| {<br/> "code":"SU",<br/> "message":"suceess"<br/> }|
| POST | /api/v1/board/{boardNum}/comment | {<br/> "comment":"example"<br/> } | {<br/> &ensp;"code":"SU",<br/> &ensp;"message" :"success"<br/> } | 
| GET | /api/v1/board/comment-list |  |{<br/> &ensp;"code":"SU",<br/> &ensp;"message":"example",<br/> &ensp;"commentList":[<br/>&emsp;{<br/> &emsp;&ensp;"nickname":"example",<br/> &emsp;&ensp;"profileImg":"url",<br/> &emsp;&ensp;"writeDate":"2024-06-27 23:02:53",<br/> &emsp;&ensp;"content":"example"<br/>&emsp;}<br/>&ensp;] <br/>}|


* 유저정보(user) 

| Method | Url | RequestBody | ResponseBody |
|:---:|:---:|---|---|
| GET | /api/v1/user |  | {<br/>"code":"SU",<br/> "message":"sucess",<br/> "userEmail":"example@example.com",<br/> "nickname":"thisis",<br/> "profileImg":"imageUrl"<br/>} |
| GET | /api/v1/user/{email} | |{<br/>"code":"SU",<br/> "message":"sucess",<br/> "userEmail":"example@example.com",<br/> "nickname":"thisis",<br/> "profileImg":"imageUrl"<br/>} |
| PATCH | /api/v1/user/nickname |{<br/>"nickname":"test"<br/>} | {<br/> "code" : "SU",<br/> "message" : "success" <br/>} |
| PATCH | /api/v1/user/profile-image | {<br/> "profileImg":"imageUrl" <br/>} | {<br/> "code" : "SU",<br/> "message" : "success" <br/>} |


* 검색어 관련(search)

| Method | Url | RequestBody | ResponseBody | 
|:---:|:---:|---|---|
| GET | /api/v1/search/popular-list |  | {<br/> "code":"SU",<br/> "message":"success",<br/> "popularWordList":["example1","example2"]<br/> } |
| GET | /api/v1/{searchWord}/relation-list |  | {<br/> "code":"SU",<br/> "message":"success",<br/> "relativeWordList":["example1","example2"]<br/> } | 


* 파일업로드(file)

| Method | Url | RequestBody | ResponseBody | 
|:---:|:---:|---|---|
| POST | /api/v1/file/upload | Multipart Form Data | FileUrl(String) |


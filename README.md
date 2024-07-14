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
##### 📖API 명세


* Auth

| Method | Url | RequestBody | ResponseBody | description |  
|:--------:|:---:|---------|----------|-------------|
| POST | /api/v1/auth/sign-up | {<br/> "userEmail":"example@example.com",<br/> "password":"test1234",<br/> "nickname":"test",<br/> "telNumber":"123-1234-1234",<br/> "address":"example",<br/> "addressDetail:"example",<br/> "profileImg":"imageUrl",<br/> "agreedPersonal": false<br/> }| {<br/> "code":"SU",<br/> "message":"success"<br/> } | 회원가입 요청.|
| POST | /api/v1/auth/sign-in | {<br/> "userEmail":"example@example.com",<br/> "password":"example"<br/> } | {<br/> "code":"SU",<br/> "message":"success",<br/> "aceessToken":"JWT Token",<br/> "expireTime": 3600<br/> }| 로그인 요청.<br/> 성공시accessToken 발급.<br/> 실패시 Error코드 응답. | 


* User 

| Method | Url | RequestBody | ResponseBody | description |  
|:--------:|:---:|---------|----------|-------------|
| GET | /api/v1/user |  | {<br/>"code":"SU",<br/> "message":"sucess",<br/> "userEmail":"example@example.com",<br/> "nickname":"thisis",<br/> "profileImg":"imageUrl"<br/>} | 로그인된 유저의 정보를 요청 |
| GET | /api/v1/user/{email} | |  {<br/>"code":"SU",<br/> "message":"sucess",<br/> "userEmail":"example@example.com",<br/> "nickname":"thisis",<br/> "profileImg":"imageUrl"<br/>} | '특정' 유저의 데이터 요청 <br/> Path Variable : 유저이메일|
| PATCH | /api/v1/user/nickname | {<br/>"nickname":"test"<br/>} | {<br/> "code" : "SU",<br/> "message" : "success" <br/>} | 유저의 닉네임 수정. | 
| PATCH |/api/v1/user/profile-image| {<br/> "profileImg":"imageUrl" <br/>} | {<br/> "code" : "SU",<br/> "message" : "success" <br/>} | 유저의 프로필 이미지 수정.|


* Search

| Method | Url | RequestBody | ResponseBody | description |
|:--------:|:---:|---------|----------|-------------|
| GET | /api/v1/search/popular-list |  | {<br/> "code":"SU",<br/> "message":"success",<br/> "popularWordList":[]<br/> } | 인기 검색어 리스트 요청.|


* File

| Method | Url | RequestBody | ResponseBody | description |
|:--------:|:---:|---------|----------|-------------|
| POST | /api/v1/file/upload | Multipart Form Data | FileUrl(String) | 파일 업로드 요청.<br/> 파일의 리소스 URL반환|


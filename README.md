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

* User 

| Method | Url | RequestBody | ResponseBody | description |  
|:--------:|:---:|:---------|:----------|:-------------:|
| GET | /api/v1/user |  | {<br/>"code" : "SU",<br/> "message" : "sucess",<br/> "userEmail" : "jdj881204@naver.com",<br/> "nickname" : "thisis",<br/> "profileImg" : "http://localhost:4000/file/10924cbe-1c92-4c93-89c9-6b5d7f771d39.png"<br/>} | 로그인된 유저의 정보를 요청 |
| GET | /api/v1/user/{email} | |  {<br/>"code" : "SU",<br/> "message" : "sucess",<br/> "userEmail" : "jdj881204@naver.com",<br/> "nickname" : "thisis",<br/> "profileImg" : "http://localhost:4000/file/10924cbe-1c92-4c93-89c9-6b5d7f771d39.png"<br/>} | '특정' 유저의 유저 데이터 요청 <br/> Path Variable : 유저이메일|
| PATCH | api/v1/user/nickname | {<br/> "nickname" : "changedNickname" <br/>} | {<br/> "code" : "SU",<br/> "message" : "success" <br/>} | 유저의 닉네임 수정 | 

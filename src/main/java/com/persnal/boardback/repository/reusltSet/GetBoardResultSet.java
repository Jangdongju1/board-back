package com.persnal.boardback.repository.reusltSet;

public interface GetBoardResultSet {
    // entity형태로 반환되는 레코드를 메서드를 사용해서 특정 필드의 값만을 가져올 수 있도록하는 인터페이스
    // 아마도 명명 규칙이 있을 것으로 판단됨 (필드이름을 카멜케이스로 표기한다던가..)
    int getBoardNum();
    String getTitle();
    String getContent();
    String getWriteDate();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImg();
}

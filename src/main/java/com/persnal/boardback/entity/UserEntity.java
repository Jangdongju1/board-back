package com.persnal.boardback.entity;


import com.persnal.boardback.dto.request.auth.SignUpRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

    @Id
    private String userEmail;
    private String password;
    private String nickname;
    private String telNumber;
    private String address;
    private String addressDetail;
    private String profileImg;
    private boolean agreedPersonal;
    //

    public UserEntity(SignUpRequest req) {
        this.userEmail = req.getUserEmail();
        this.password = req.getPassword();
        this.nickname = req.getNickname();
        this.telNumber = req.getTelNumber();
        this.address = req.getAddress();
        this.addressDetail = req.getAddressDetail();
        //this.profileImg = req.getProfileImg();
        this.agreedPersonal = req.getAgreedPersonal();
    }
}

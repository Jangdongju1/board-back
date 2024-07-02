package com.persnal.boardback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "boardImg")
@Table(name = "boardimg")
public class BoardImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence = 0;
    private String image = "";
    private int boardNum = 0;

    public BoardImgEntity(int boardNum, String image){
        this.boardNum = boardNum;
        this.image = image;
    }

}

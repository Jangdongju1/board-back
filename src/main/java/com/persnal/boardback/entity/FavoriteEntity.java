package com.persnal.boardback.entity;

import com.persnal.boardback.entity.primaryKey.FavoritePk;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favorite")
@Table(name = "favorite")
@IdClass(FavoritePk.class)  // primary key가 존재하지 않는경우 새로이 정의해서 지정해줘야 함.
public class FavoriteEntity {
    @Id
    private String userEmail = "";
    @Id
    private int boardNum = 0;
}

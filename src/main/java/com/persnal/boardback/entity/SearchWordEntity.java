package com.persnal.boardback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "searchword")
@Table(name = "searchword")
public class SearchWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence = 0;
    private String searchWord = "";
    private String relationWord = "";
    private boolean relation = false;
}

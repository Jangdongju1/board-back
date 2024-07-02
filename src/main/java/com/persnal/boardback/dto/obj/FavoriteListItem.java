package com.persnal.boardback.dto.obj;

import com.persnal.boardback.repository.reusltSet.GetBoardResultSet;
import com.persnal.boardback.repository.reusltSet.GetFavoriteListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteListItem {
    private String userEmail;
    private String nickname;
    private String profileImg;

    public FavoriteListItem(GetFavoriteListResultSet resultSet) {
        this.userEmail = resultSet.getUserEmail();
        this.nickname = resultSet.getNickname();
        this.profileImg = resultSet.getProfileImg();
    }

    public static List<FavoriteListItem> setList(List<GetFavoriteListResultSet> list) {
        ArrayList<FavoriteListItem> favoriteListItems = new ArrayList<FavoriteListItem>();

        for (GetFavoriteListResultSet resultSet : list) {
            FavoriteListItem item = new FavoriteListItem(resultSet);
            favoriteListItems.add(item);
        }

        return favoriteListItems;
    }
}

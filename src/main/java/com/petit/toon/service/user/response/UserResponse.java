package com.petit.toon.service.user.response;

import com.petit.toon.entity.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {
    private long id;
    private String nickname;
    private String tag;
    private String profileImagePath;

    @Builder
    private UserResponse(long id, String nickname, String tag, String profileImagePath) {
        this.id = id;
        this.nickname = nickname;
        this.tag = tag;
        this.profileImagePath = profileImagePath;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .tag(user.getTag())
                .profileImagePath(user.getProfileImage().getPath())
                .build();
    }
}

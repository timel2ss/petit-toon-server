package com.petit.toon.entity.cartoon;

public enum LikeStatus {
    LIKE("LIKE"),
    DISLIKE("DISLIKE"),
    NONE("NONE");

    public String description;

    LikeStatus(String description) {
        this.description = description;
    }
}

package com.petit.toon.entity.cartoon;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cartoon cartoon;

    private String fileName;

    private String originalFileName;

    private String path;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @Builder
    private Image(Cartoon cartoon, String fileName, String originalFileName, String path) {
        this.cartoon = cartoon;
        this.fileName = fileName;
        this.originalFileName = originalFileName;
        this.path = path;
    }
}

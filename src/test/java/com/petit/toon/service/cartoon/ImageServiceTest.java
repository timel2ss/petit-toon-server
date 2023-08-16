package com.petit.toon.service.cartoon;

import com.petit.toon.entity.cartoon.Cartoon;
import com.petit.toon.entity.cartoon.Image;
import com.petit.toon.entity.user.User;
import com.petit.toon.repository.cartoon.CartoonRepository;
import com.petit.toon.repository.cartoon.ImageRepository;
import com.petit.toon.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createDirectory;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ImageServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartoonRepository cartoonRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @TempDir
    Path tempDir;
    String absolutePath;
    @BeforeEach
    void setUp() throws IOException {
        String path = "src/test/resources/sample-toons";
        absolutePath = new File(path).getAbsolutePath();
        System.out.println("tempDirPath = " + tempDir);
        createDirectory(Path.of(tempDir + "/toons"));
    }

    @AfterEach
    void deleteFile() {

    }

    @Test
    @Transactional
    void 이미지저장() throws IOException {
        //given
        User user = createUser("KIM");
        Cartoon mockCartoon = Cartoon.builder()
                .user(user)
                .title("title")
                .description("sample")
                .viewCount(0)
                .build();
        cartoonRepository.save(mockCartoon);
        createToonDirectory(mockCartoon.getId());

        MultipartFile file = new MockMultipartFile("sample1.png", "sample1.png", "multipart/form-data",
                new FileInputStream(absolutePath + "/sample1.png"));

        //when
        Image img1 = imageService.storeImage(file, mockCartoon, 0, tempDir + "/toons");
        Image img2 = imageService.storeImage(file, mockCartoon, 1, tempDir + "/toons");

        //then
        assertThat(img1.getFileName()).isEqualTo(img1.getCartoon().getId() + "-0.png");
        assertThat(img1.getPath()).isEqualTo("toons\\" + mockCartoon.getId() + "\\" + img1.getCartoon().getId() + "-0.png");
        assertThat(img1.getOriginalFileName()).isEqualTo("sample1.png");
        assertThat(img1.getCartoon()).isEqualTo(mockCartoon);

        assertThat(img2.getFileName()).isEqualTo(img1.getCartoon().getId() + "-1.png");
        assertThat(img2.getPath()).isEqualTo("toons\\" + mockCartoon.getId() + "\\" + img1.getCartoon().getId() + "-1.png");
        assertThat(img2.getOriginalFileName()).isEqualTo("sample1.png");
        assertThat(img2.getCartoon()).isEqualTo(mockCartoon);
    }

    private User createUser(String name) {
        return userRepository.save(
                User.builder()
                        .name(name)
                        .build());
    }

    private void createToonDirectory(Long toonId) throws IOException {
        String absolutePath = tempDir + "/toons/" + toonId;
        String toonDirectoryPath = new File(absolutePath).getAbsolutePath();
        createDirectory(Path.of(toonDirectoryPath));
    }
}

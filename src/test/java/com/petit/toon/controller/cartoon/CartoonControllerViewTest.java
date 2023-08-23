package com.petit.toon.controller.cartoon;

import com.petit.toon.controller.RestDocsSupport;
import com.petit.toon.entity.cartoon.LikeStatus;
import com.petit.toon.entity.user.User;
import com.petit.toon.exception.notfound.CartoonNotFoundException;
import com.petit.toon.repository.user.UserRepository;
import com.petit.toon.service.cartoon.CartoonService;
import com.petit.toon.service.cartoon.response.CartoonResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties(H2ConsoleProperties.class)
@WithUserDetails(value = "sample@email.com", userDetailsServiceBeanName = "customUserDetailsService", setupBefore = TestExecutionEvent.TEST_EXECUTION)
public class CartoonControllerViewTest extends RestDocsSupport {
    @MockBean
    CartoonService cartoonService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.save(
                User.builder()
                        .email("sample@email.com")
                        .password("SamplePW123!@#")
                        .build());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("웹툰 단건 조회 API")
    void getToon() throws Exception {
        // given
        CartoonResponse response = CartoonResponse.builder()
                .id(1L)
                .title("sample-title")
                .description("sample-description")
                .author("sample-author")
                .profileImageUrl("profileImages/1.png")
                .viewCount(0)
                .imagePaths(List.of("toons/1/1-0.png", "toons/1/1-1.png"))
                .thumbnailUrl("toons/1/1-thumb.png")
                .likeCount(0)
                .likeStatus(LikeStatus.NONE.description)
                .build();
        given(cartoonService.findOne(anyLong(), anyLong())).willReturn(response);

        // when // then
        mockMvc.perform(get("/api/v1/toon/{toonId}", 1))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("toon-get",
                        pathParameters(
                                parameterWithName("toonId").description("웹툰 ID")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER)
                                        .description("만화 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("만화 제목"),
                                fieldWithPath("description").type(JsonFieldType.STRING)
                                        .description("만화 설명"),
                                fieldWithPath("author").type(JsonFieldType.STRING)
                                        .description("작가 이름"),
                                fieldWithPath("profileImageUrl").type(JsonFieldType.STRING)
                                        .description("작가 프로필 이미지 경로"),
                                fieldWithPath("thumbnailUrl").type(JsonFieldType.STRING)
                                        .description("만화 썸네일 url"),
                                fieldWithPath("imagePaths").type(JsonFieldType.ARRAY)
                                        .description("만화 이미지 경로"),
                                fieldWithPath("viewCount").type(JsonFieldType.NUMBER)
                                        .description("만화 조회수"),
                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER)
                                        .description("만화 좋아요수"),
                                fieldWithPath("likeStatus").optional().type(JsonFieldType.STRING)
                                        .description("만화 좋아요 상태 (LIKE/DISLIKE/NONE)")
                        )
                ));
    }

    @Test
    @DisplayName("웹툰 단건 조회 API - CartoonNotFound")
    void getToon2() throws Exception {
        // given
        given(cartoonService.findOne(anyLong(), anyLong())).willThrow(new CartoonNotFoundException());

        // when // then
        mockMvc.perform(get("/api/v1/toon/{toonId}", 99999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("404"))
                .andExpect(jsonPath("$.message").value(CartoonNotFoundException.MESSAGE))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("exception-cartoon-not-found",
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("HTTP 상태 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("예외 메시지")
                        )
                ));
    }

    @Test
    @DisplayName("웹툰 조회수 증가 API")
    void view() throws Exception {
        // when // then
        mockMvc.perform(post("/api/v1/toon/{toonId}/view", 1))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("toon-view",
                        pathParameters(
                                parameterWithName("toonId").description("웹툰 ID")
                        )));
    }
}

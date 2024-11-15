package kusitms.exihibition.guestbook;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.exihibition.configuration.ControllerTestConfig;
import kusitms.exihibition.guestbook.application.GuestBookService;
import kusitms.exihibition.guestbook.dto.request.RegisterGuestBookRequest;
import kusitms.exihibition.guestbook.dto.response.GetGuestBooksResponse;
import kusitms.exihibition.guestbook.presentation.GuestBookController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GuestBookController.class)
public class GuestBookControllerTest extends ControllerTestConfig {

    @MockBean
    private GuestBookService guestBookService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("방명록을 등록한다.")
    public void registerGuestBookTest() throws Exception {
        // given
        RegisterGuestBookRequest request = new RegisterGuestBookRequest("큐시즘 전시회 화이팅!");
        String requestContent = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/v1/guestbooks")
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("201"))
                .andExpect(jsonPath("$.message").value("방명록 등록에 성공했습니다."))
                .andDo(MockMvcRestDocumentationWrapper.document("guestbook/register",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("GuestBook API")
                                        .description("방명록을 등록한다.")
                                        .requestFields(
                                                fieldWithPath("content").description("큐시즘 전시회 화이팅!")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지")
                                        )
                                        .requestSchema(Schema.schema("RegisterGuestBookRequest"))
                                        .build()
                        )
                ));
    }

    @Test
    @DisplayName("방명록을 페이지 별로 조회한다.")
    public void getGuestBooksTest() throws Exception {
        // given
        List<GetGuestBooksResponse> responses = List.of(
                new GetGuestBooksResponse("방명록 내용1", "2024년 06월 08일 10:10"),
                new GetGuestBooksResponse("방명록 내용2", "2024년 06월 07일 15:30")
        );

        Mockito.when(guestBookService.getGuestBooks(any(PageRequest.class))).thenReturn(responses);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/guestbooks/{page}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("페이지 별 방명록 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload[0].content").value("방명록 내용1"))
                .andExpect(jsonPath("$.payload[0].createdDate").value("2024년 06월 08일 10:10"))
                .andDo(MockMvcRestDocumentationWrapper.document("guestbook/list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("GuestBook API")
                                        .description("방명록을 페이지 별로 조회한다.")
                                        .pathParameters(
                                                parameterWithName("page").description("조회할 페이지 번호 [예시 : 1 (NUMBER Type, 최소 1이상)]")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload[].content").description("방명록 내용"),
                                                fieldWithPath("payload[].createdDate").description("방명록 작성 날짜")
                                        )
                                        .responseSchema(Schema.schema("GetGuestBooksResponse"))
                                        .build()
                        )
                ));
    }
}
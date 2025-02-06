package kusitms.exihibition.comment;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.exihibition.comment.application.CommentService;
import kusitms.exihibition.comment.dto.request.RegisterCommentRequest;
import kusitms.exihibition.comment.dto.response.GetCommentsResponse;
import kusitms.exihibition.comment.presentation.CommentController;
import kusitms.exihibition.configuration.ControllerTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest extends ControllerTestConfig {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("특정 프로덕트에 의견을 등록한다.")
    public void registerCommentTest() throws Exception {
        // given
        RegisterCommentRequest request = new RegisterCommentRequest("A팀 화이팅!");
        String requestContent = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/v1/comments/{product_id}", 1L)
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("201"))
                .andExpect(jsonPath("$.message").value("의견 등록에 성공했습니다."))
                .andDo(MockMvcRestDocumentationWrapper.document("comment/register",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("Archive")
                                        .description("특정 프로덕트에 의견을 등록한다.")
                                        .pathParameters(
                                                parameterWithName("product_id").description("의견을 등록할 프로덕트 ID")
                                        )
                                        .requestFields(
                                                fieldWithPath("content").description("의견 내용 (최대 200자)")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지")
                                        )
                                        .requestSchema(Schema.schema("RegisterCommentRequest"))
                                        .build()
                        )
                ));
    }

    @Test
    @DisplayName("특정 프로덕트에 달린 의견을 페이지 별로 조회한다.")
    public void getCommentsTest() throws Exception {
        // given
        GetCommentsResponse response = GetCommentsResponse.of(
                25,
                3,
                List.of(
                        new GetCommentsResponse.CommentResponse("의견 내용 1", "2024-11-17 10:30"),
                        new GetCommentsResponse.CommentResponse("의견 내용 2", "2024-11-16 14:20")
                )
        );

        Mockito.when(commentService.getComments(Mockito.anyLong(), Mockito.any()))
                .thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/comments/{product_id}/{page}", 1L, 1)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("페이지 별 의견 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload.totalCommentCount").value(25))
                .andExpect(jsonPath("$.payload.totalPageCount").value(3))
                .andExpect(jsonPath("$.payload.comments[0].content").value("의견 내용 1"))
                .andExpect(jsonPath("$.payload.comments[0].createdDate").value("2024-11-17 10:30"))
                .andExpect(jsonPath("$.payload.comments[1].content").value("의견 내용 2"))
                .andExpect(jsonPath("$.payload.comments[1].createdDate").value("2024-11-16 14:20"))
                .andDo(MockMvcRestDocumentationWrapper.document("comment/list",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("Archive")
                                        .description("특정 프로덕트에 달린 의견을 페이지 별로 조회한다.")
                                        .pathParameters(
                                                parameterWithName("product_id").description("의견을 조회할 프로덕트 ID [예시 : 1 (NUMBER Type)]"),
                                                parameterWithName("page").description("조회할 페이지 번호 [예시 : 1 (NUMBER Type, 최소 1이상)]")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload.totalCommentCount").description("총 의견 수"),
                                                fieldWithPath("payload.totalPageCount").description("총 페이지 수"),
                                                fieldWithPath("payload.comments[].content").description("의견 내용"),
                                                fieldWithPath("payload.comments[].createdDate").description("의견 작성 날짜")
                                        )
                                        .responseSchema(Schema.schema("GetCommentsResponse"))
                                        .build()
                        )
                ));
    }
}

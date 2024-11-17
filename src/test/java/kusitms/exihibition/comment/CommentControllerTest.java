package kusitms.exihibition.comment;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.exihibition.comment.application.CommentService;
import kusitms.exihibition.comment.dto.request.RegisterCommentRequest;
import kusitms.exihibition.comment.presentation.CommentController;
import kusitms.exihibition.configuration.ControllerTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

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
}
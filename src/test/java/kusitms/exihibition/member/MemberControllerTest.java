package kusitms.exihibition.member;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.exihibition.configuration.ControllerTestConfig;
import kusitms.exihibition.member.application.MemberService;
import kusitms.exihibition.member.dto.response.GetMembersByTypeResponse;
import kusitms.exihibition.member.presentation.MemberController;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest extends ControllerTestConfig {

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("유형별로 멤버를 조회한다.")
    public void getMembersByTypeTest() throws Exception {
        // given
        List<GetMembersByTypeResponse> responses = List.of(
                new GetMembersByTypeResponse("홍길동", "https://img.url", "Planner", "https://instagram.com/example", "https://linkedin.com/example", null, null),
                new GetMembersByTypeResponse("김철수", "https://img.url", "Designer", null, null, null, "https://behance.net/example")
        );

        Mockito.when(memberService.getMembersByType(anyString())).thenReturn(responses);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/members/{type}", "participants")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("유형별 멤버 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload[0].name").value("홍길동"))
                .andExpect(jsonPath("$.payload[0].part").value("Planner"))
                .andExpect(jsonPath("$.payload[1].name").value("김철수"))
                .andExpect(jsonPath("$.payload[1].part").value("Designer"))
                .andDo(MockMvcRestDocumentationWrapper.document("member/type",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("People")
                                        .description("유형별로 멤버를 조회한다.")
                                        .pathParameters(
                                                parameterWithName("type").description("멤버 유형 [예시 : all, participants, tf, other]")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload[].name").description("멤버 이름"),
                                                fieldWithPath("payload[].imgUrl").description("멤버 이미지 URL"),
                                                fieldWithPath("payload[].part").description("멤버 역할"),
                                                fieldWithPath("payload[].instagramUrl").description("Instagram URL").optional(),
                                                fieldWithPath("payload[].linkedinUrl").description("LinkedIn URL").optional(),
                                                fieldWithPath("payload[].githubUrl").description("GitHub URL").optional(),
                                                fieldWithPath("payload[].behanceUrl").description("Behance URL").optional()
                                        )
                                        .responseSchema(Schema.schema("GetMembersByTypeResponse"))
                                        .build()
                        )
                ));
    }
}
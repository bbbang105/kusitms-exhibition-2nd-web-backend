package kusitms.exihibition.team;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import kusitms.exihibition.configuration.ControllerTestConfig;
import kusitms.exihibition.team.application.TeamService;
import kusitms.exihibition.team.dto.response.GetTeamInfosResponse;
import kusitms.exihibition.team.presentation.TeamController;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
public class TeamControllerTest extends ControllerTestConfig {

    @MockBean
    private TeamService teamService;

    @Test
    @DisplayName("특정 프로덕트의 팀 정보와 멤버 목록을 조회한다.")
    public void getTeamInfosTest() throws Exception {
        // given
        List<GetTeamInfosResponse.MemberInfo> memberInfos = List.of(
                new GetTeamInfosResponse.MemberInfo("홍길동", "https://img.url/1", "Planner", null, "https://linkedin.com/example1", "https://github.com/example1", null),
                new GetTeamInfosResponse.MemberInfo("김철수", "https://img.url/2", "Designer", "https://instagram.com/example2", null, null, "https://behance.net/example2")
        );

        GetTeamInfosResponse response = new GetTeamInfosResponse(
                "A팀", "30th", "MEET-UP", "A팀 설명입니다.", memberInfos
        );

        Mockito.when(teamService.getTeamInfos(anyLong())).thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/teams/{product_id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("특정 팀 정보 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload.teamName").value("A팀"))
                .andExpect(jsonPath("$.payload.generation").value("30th"))
                .andExpect(jsonPath("$.payload.origin").value("MEET-UP"))
                .andExpect(jsonPath("$.payload.description").value("A팀 설명입니다."))
                .andExpect(jsonPath("$.payload.memberInfos[0].name").value("홍길동"))
                .andExpect(jsonPath("$.payload.memberInfos[0].part").value("Planner"))
                .andExpect(jsonPath("$.payload.memberInfos[1].name").value("김철수"))
                .andExpect(jsonPath("$.payload.memberInfos[1].part").value("Designer"))
                .andDo(MockMvcRestDocumentationWrapper.document("team/get-team-infos",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("Archive")
                                        .description("특정 프로덕트의 팀 정보와 멤버 목록을 조회한다.")
                                        .pathParameters(
                                                parameterWithName("product_id").description("조회에 사용할 프로덕트 ID [예시 : 1 (NUMBER Type)]")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload.teamName").description("팀 이름"),
                                                fieldWithPath("payload.generation").description("팀 기수"),
                                                fieldWithPath("payload.origin").description("팀 결성 계기"),
                                                fieldWithPath("payload.description").description("팀 설명"),
                                                fieldWithPath("payload.memberInfos[].name").description("멤버 이름"),
                                                fieldWithPath("payload.memberInfos[].imgUrl").description("멤버 이미지 URL"),
                                                fieldWithPath("payload.memberInfos[].part").description("멤버 파트"),
                                                fieldWithPath("payload.memberInfos[].instagramUrl").description("인스타그램 URL").optional(),
                                                fieldWithPath("payload.memberInfos[].linkedinUrl").description("링크드인 URL").optional(),
                                                fieldWithPath("payload.memberInfos[].githubUrl").description("깃허브 URL").optional(),
                                                fieldWithPath("payload.memberInfos[].behanceUrl").description("비핸스 URL").optional()
                                        )
                                        .responseSchema(Schema.schema("GetTeamInfosResponse"))
                                        .build()
                        )
                ));
    }
}

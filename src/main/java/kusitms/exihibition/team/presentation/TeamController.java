package kusitms.exihibition.team.presentation;

import kusitms.exihibition.global.dto.ApiResponse;
import kusitms.exihibition.team.application.TeamService;
import kusitms.exihibition.team.dto.response.GetTeamInfosResponse;
import kusitms.exihibition.team.status.TeamSuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
@Validated
public class TeamController {

    private final TeamService teamService;

    /**
     * 특정 팀 정보 조회 API
     *
     * 이 API는 특정 팀의 정보를 포함하여 팀에 소속된 멤버들의 데이터를 반환합니다.
     *
     * @param productId 조회할 팀이 제작한 프로덕트 ID
     * @return 팀 정보 및 멤버 목록 응답
     */
    @GetMapping("/{product_id}")
    public ResponseEntity<ApiResponse<GetTeamInfosResponse>> getTeamInfos(
            @PathVariable("product_id") Long productId
    ) {

        GetTeamInfosResponse response = teamService.getTeamInfos(productId);
        return ApiResponse.onSuccess(TeamSuccessStatus._GET_TEAM_INFOS, response);
    }
}
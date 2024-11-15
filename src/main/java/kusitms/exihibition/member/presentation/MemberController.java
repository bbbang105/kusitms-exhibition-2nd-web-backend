package kusitms.exihibition.member.presentation;

import kusitms.exihibition.global.dto.ApiResponse;
import kusitms.exihibition.member.application.MemberService;
import kusitms.exihibition.member.dto.response.GetMembersByTypeResponse;
import kusitms.exihibition.member.status.MemberSuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@Validated
public class MemberController {

    private final MemberService memberService;

    /**
     * 유형별 멤버 조회 API
     *
     * 이 API는 멤버의 유형별 데이터를 조회합니다.
     * type이 null이거나 제공되지 않을 경우 전체 데이터를 반환합니다.
     *
     * @param type 멤버 유형 (예: all, tf, participants, other)
     * @return 멤버 리스트 및 성공 메시지
     */
    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<List<GetMembersByTypeResponse>>> getMembersByType(
            @PathVariable(value = "type") String type
    ) {

        List<GetMembersByTypeResponse> response = memberService.getMembersByType(type);
        return ApiResponse.onSuccess(MemberSuccessStatus._GET_MEMBERS_BY_TYPE, response);
    }
}
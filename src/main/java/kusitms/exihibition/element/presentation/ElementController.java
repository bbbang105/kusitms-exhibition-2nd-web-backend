package kusitms.exihibition.element.presentation;

import jakarta.validation.Valid;
import kusitms.exihibition.element.application.ElementService;
import kusitms.exihibition.element.dto.request.GetElementTestResultRequest;
import kusitms.exihibition.element.dto.response.GetElementTestResultResponse;
import kusitms.exihibition.element.status.ElementSuccessStatus;
import kusitms.exihibition.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/elements")
public class ElementController {

    private final ElementService elementService;

    /**
     * 원소 테스트 결과 생성 및 반환 API
     *
     * 이 API는 특정 프로덕트 대해 유저가 작성한 의견을 저장합니다.
     *
     * @param request 유저 선택 내역
     * @return 성공 여부 및 상태 메시지
     */
    @PostMapping
    public ResponseEntity<ApiResponse<GetElementTestResultResponse>> getElementTestResult(
            @Valid @RequestBody GetElementTestResultRequest request
    ) {

        GetElementTestResultResponse response = elementService.getElementTestResult(request);
        return ApiResponse.onSuccess(ElementSuccessStatus._GET_ELEMENT_TEST_RESULT, response);
    }
}

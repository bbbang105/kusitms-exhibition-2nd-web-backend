package kusitms.exihibition.guestbook.presentation;

import jakarta.validation.Valid;
import kusitms.exihibition.global.dto.ApiResponse;
import kusitms.exihibition.guestbook.application.GuestBookService;
import kusitms.exihibition.guestbook.dto.request.RegisterGuestBookRequest;
import kusitms.exihibition.guestbook.status.GuestBookSuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guestbooks")
@Validated
public class GuestBookController {

    private final GuestBookService guestBookService;

    /**
     * 방명록 작성 API
     *
     * 이 API는 사용자가 작성한 방명록 내용을 저장합니다.
     *
     * @param request 방명록 작성 요청 데이터 (내용 필수)
     * @return 성공 여부 및 상태 메시지
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> registerGuestBook(
            @Valid @RequestBody RegisterGuestBookRequest request
    ) {

        guestBookService.registerGuestBook(request);
        return ApiResponse.onSuccess(GuestBookSuccessStatus._REGISTER_GUESTBOOK);
    }
}
package kusitms.exihibition.guestbook.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kusitms.exihibition.global.dto.ApiResponse;
import kusitms.exihibition.guestbook.application.GuestBookService;
import kusitms.exihibition.guestbook.dto.request.RegisterGuestBookRequest;
import kusitms.exihibition.guestbook.dto.response.GetGuestBooksResponse;
import kusitms.exihibition.guestbook.status.GuestBookSuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 방명록 목록 조회 API
     *
     * 최신순으로 정렬된 방명록 데이터를 반환합니다.
     *
     * @param page 조회할 페이지 번호 (1부터 시작)
     * @return 방명록 데이터 목록 (1페이지당 10개)
     */
    @GetMapping("/{page}")
    public ResponseEntity<ApiResponse<List<GetGuestBooksResponse>>> getGuestBooks(
            @PathVariable("page") @Min(1) int page
    ) {

        List<GetGuestBooksResponse> guestBooks = guestBookService.getGuestBooks(PageRequest.of(page - 1, 10));
        return ApiResponse.onSuccess(GuestBookSuccessStatus._GET_GUESTBOOKS_BY_PAGE, guestBooks);
    }
}
package kusitms.exihibition.comment.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kusitms.exihibition.comment.application.CommentService;
import kusitms.exihibition.comment.dto.request.RegisterCommentRequest;
import kusitms.exihibition.comment.dto.response.GetCommentsResponse;
import kusitms.exihibition.comment.status.CommentSuccessStatus;
import kusitms.exihibition.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
@Validated
public class CommentController {

    private final CommentService commentService;

    /**
     * 의견 등록 API
     *
     * 이 API는 특정 프로덕트 대해 유저가 작성한 의견을 저장합니다.
     *
     * @param productId 프로덕트 ID
     * @param request 의견 등록 내용 (200자 제한)
     * @return 성공 여부 및 상태 메시지
     */
    @PostMapping("/{product_id}")
    public ResponseEntity<ApiResponse<Object>> registerComment(
            @PathVariable("product_id") Long productId,
            @Valid @RequestBody RegisterCommentRequest request
    ) {

        commentService.registerComment(productId, request);
        return ApiResponse.onSuccess(CommentSuccessStatus._REGISTER_COMMENT);
    }

    /**
     * 의견 목록 조회 API
     *
     * 최신순으로 정렬된 의견 목록 데이터를 반환합니다.
     *
     * @param productId 프로덕트 ID
     * @param page 조회할 페이지 번호 (1부터 시작)
     * @return 의견 데이터 목록 (1페이지당 10개)
     */
    @GetMapping("/{product_id}/{page}")
    public ResponseEntity<ApiResponse<GetCommentsResponse>> getComments(
            @PathVariable("product_id") Long productId,
            @PathVariable("page") @Min(1) int page
    ) {

        GetCommentsResponse response = commentService.getComments(productId, PageRequest.of(page - 1, 10));
        return ApiResponse.onSuccess(CommentSuccessStatus._GET_GUESTBOOKS_BY_PAGE, response);
    }
}
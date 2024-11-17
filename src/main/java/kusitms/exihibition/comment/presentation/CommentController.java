package kusitms.exihibition.comment.presentation;

import jakarta.validation.Valid;
import kusitms.exihibition.comment.application.CommentService;
import kusitms.exihibition.comment.dto.request.RegisterCommentRequest;
import kusitms.exihibition.comment.status.CommentSuccessStatus;
import kusitms.exihibition.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
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
}
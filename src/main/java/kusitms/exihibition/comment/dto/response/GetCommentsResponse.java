package kusitms.exihibition.comment.dto.response;

import kusitms.exihibition.comment.entity.Comment;
import kusitms.exihibition.global.util.DateUtil;

import java.util.List;

public record GetCommentsResponse(
        int totalCommentCount,
        int totalPageCount,
        List<CommentResponse> comments
        ){

    public static GetCommentsResponse of(int totalCommentCount, int totalPageCount, List<CommentResponse> comments) {
        return new GetCommentsResponse(
                totalCommentCount,
                totalPageCount,
                comments
        );
    }

    public record CommentResponse(
            String content,
            String createdDate
    ) {
        public static CommentResponse from(Comment comment) {
            return new CommentResponse(
                    comment.getContent(),
                    DateUtil.formatDateTime(comment.getCreatedDate())
            );
        }
    }
}
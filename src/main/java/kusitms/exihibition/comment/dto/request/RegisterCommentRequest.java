package kusitms.exihibition.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kusitms.exihibition.comment.domain.entity.Comment;
import kusitms.exihibition.product.domain.entity.Product;

public record RegisterCommentRequest(
        @NotBlank(message = "의견 내용은 빈 값일 수 없습니다.")
        @Size(max = 200, message = "의견 내용은 200자를 초과할 수 없습니다.")
        String content
) {

    public Comment toEntity(Product product) {
        return Comment.builder()
                .product(product)
                .content(content)
                .build();
    }
}

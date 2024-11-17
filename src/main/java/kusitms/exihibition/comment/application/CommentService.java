package kusitms.exihibition.comment.application;

import kusitms.exihibition.comment.dto.request.RegisterCommentRequest;
import kusitms.exihibition.comment.entity.Comment;
import kusitms.exihibition.comment.entity.repository.CommentRepository;
import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.product.domain.entity.Product;
import kusitms.exihibition.product.domain.repository.ProductRepository;
import kusitms.exihibition.product.status.ProductErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    // 의견 등록 메서드
    @Transactional
    public void registerComment(Long productId, RegisterCommentRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ProductErrorStatus._NOT_FOUND_PRODUCT));
        Comment newComment = request.toEntity(product);
        commentRepository.save(newComment);
    }
}
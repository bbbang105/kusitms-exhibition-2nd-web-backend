package kusitms.exihibition.comment.domain.entity.repository;

import kusitms.exihibition.comment.domain.entity.Comment;
import kusitms.exihibition.product.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByProductOrderByCreatedDateDesc(Product product, Pageable pageable);
}

package kusitms.exihibition.comment.entity.repository;

import kusitms.exihibition.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
package kusitms.exihibition.comment.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.global.dao.BaseEntity;
import kusitms.exihibition.product.domain.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Builder
    public Comment(Product product, String content) {
        this.product = product;
        this.content = content;
    }
}

package kusitms.exihibition.product.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.team.domain.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teams_id", nullable = false)
    private Team team;

    @Column(name = "thumbnail_url", length = 100)
    private String thumbnailUrl;

    @Column(name = "instagram_url", length = 100)
    private String instagramUrl;

    @Column(name = "service_url", length = 100)
    private String serviceUrl;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "introduction", length = 50)
    private String introduction;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "target", length = 200)
    private String target;

    @Column(name = "problem", length = 200)
    private String problem;

    @Column(name = "solution", length = 200)
    private String solution;

    @Column(name = "comments_count")
    private int commentsCount;

    public void increaseCommentsCount() {
        commentsCount++;
    }
}
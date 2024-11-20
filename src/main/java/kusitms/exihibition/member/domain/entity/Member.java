package kusitms.exihibition.member.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.global.dao.BaseEntity;
import kusitms.exihibition.member.domain.enums.MemberType;
import kusitms.exihibition.team.domain.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @Column(name = "members_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teams_id")
    private Team team;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "part", nullable = false, length = 30)
    private String part;

    @Column(name = "img_url", nullable = false, length = 100)
    private String imgUrl;

    @Column(name = "instagram_url", length = 200)
    private String instagramUrl;

    @Column(name = "linkedin_url", length = 200)
    private String linkedinUrl;

    @Column(name = "github_url", length = 200)
    private String githubUrl;

    @Column(name = "behance_url", length = 200)
    private String behanceUrl;

    @Column(name = "site_url", length = 200)
    private String siteUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType type;
}

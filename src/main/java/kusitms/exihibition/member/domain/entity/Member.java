package kusitms.exihibition.member.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.member.domain.enums.MemberType;
import kusitms.exihibition.team.domain.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "members")
public class Member {

    @Id
    @Column(name = "members_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "part", nullable = false, length = 30)
    private String part;

    @Column(name = "img_url", nullable = false, length = 100)
    private String imgUrl;

    @Column(name = "instagram_url", length = 100)
    private String instagramUrl;

    @Column(name = "linkedin_url", length = 100)
    private String linkedinUrl;

    @Column(name = "github_url", length = 100)
    private String githubUrl;

    @Column(name = "behance_url", length = 100)
    private String behanceUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType type;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Team> teams;
}
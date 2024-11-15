package kusitms.exihibition.team.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.member.domain.entity.Member;
import kusitms.exihibition.team.domain.enums.TeamType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teams_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id", nullable = false)
    private Member member;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TeamType type;

    @Column(name = "origin", length = 30)
    private String origin;

    @Column(name = "generation", length = 30)
    private String generation;

    @Column(name = "description", length = 200)
    private String description;
}
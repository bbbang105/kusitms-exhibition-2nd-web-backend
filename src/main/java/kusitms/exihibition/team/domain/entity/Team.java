package kusitms.exihibition.team.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.global.dao.BaseEntity;
import kusitms.exihibition.member.domain.entity.Member;
import kusitms.exihibition.product.domain.entity.Product;
import kusitms.exihibition.team.domain.enums.TeamType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "teams")
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teams_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TeamType type;

    @Column(name = "origin", length = 30)
    private String origin;

    @Column(name = "generation", length = 30)
    private String generation;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members;

    @OneToOne(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;
}

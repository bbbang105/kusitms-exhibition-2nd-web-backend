package kusitms.exihibition.team.domain.repository;

import kusitms.exihibition.team.domain.entity.Team;
import kusitms.exihibition.team.domain.enums.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByType(TeamType type);
}

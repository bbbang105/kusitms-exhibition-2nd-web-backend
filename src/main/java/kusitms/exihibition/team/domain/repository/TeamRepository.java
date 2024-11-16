package kusitms.exihibition.team.domain.repository;

import kusitms.exihibition.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
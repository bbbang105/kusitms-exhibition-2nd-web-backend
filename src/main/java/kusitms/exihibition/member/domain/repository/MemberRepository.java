package kusitms.exihibition.member.domain.repository;

import kusitms.exihibition.member.domain.entity.Member;
import kusitms.exihibition.member.domain.enums.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByType(MemberType type);
}

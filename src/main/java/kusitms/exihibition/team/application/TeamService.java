package kusitms.exihibition.team.application;

import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.product.domain.entity.Product;
import kusitms.exihibition.product.domain.repository.ProductRepository;
import kusitms.exihibition.product.status.ProductErrorStatus;
import kusitms.exihibition.team.domain.entity.Team;
import kusitms.exihibition.team.dto.response.GetTeamInfosResponse;
import kusitms.exihibition.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final ProductRepository productRepository;

    private static final List<String> PART_ORDER = List.of(
            "PM / Planner", "PM / FE Developer", "PM / Designer", "Planner", "Designer", "FE Developer", "BE Developer"
    );

    // 특정 팀 정보 조회 메서드
    @Transactional(readOnly = true)
    public GetTeamInfosResponse getTeamInfos(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ProductErrorStatus._NOT_FOUND_PRODUCT));

        Team team = product.getTeam();
        List<Member> sortedMembers = sortByPart(team.getMembers());

        List<GetTeamInfosResponse.MemberInfo> memberInfos = sortedMembers.stream()
                .map(GetTeamInfosResponse.MemberInfo::from)
                .toList();

        return GetTeamInfosResponse.of(team, memberInfos);
    }

    // 파트 기준 정렬 메서드
    private List<Member> sortByPart(List<Member> members) {
        return members.stream()
                .sorted(Comparator.comparingInt(member -> {
                    String part = member.getPart();
                    return PART_ORDER.contains(part) ? PART_ORDER.indexOf(part) : Integer.MAX_VALUE;
                }))
                .collect(Collectors.toList());
    }
}
package kusitms.exihibition.team.application;

import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.product.domain.entity.Product;
import kusitms.exihibition.product.domain.repository.ProductRepository;
import kusitms.exihibition.product.status.ProductErrorStatus;
import kusitms.exihibition.team.domain.entity.Team;
import kusitms.exihibition.team.dto.response.GetTeamInfosResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final ProductRepository productRepository;

    // 특정 팀 정보 조회 메서드
    @Transactional(readOnly = true)
    public GetTeamInfosResponse getTeamInfos(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ProductErrorStatus._NOT_FOUND_PRODUCT));;

        Team team = product.getTeam();
        List<GetTeamInfosResponse.MemberInfo> memberInfos = team.getMembers().stream()
                .map(GetTeamInfosResponse.MemberInfo::from)
                .toList();

        return GetTeamInfosResponse.of(team, memberInfos);
    }
}
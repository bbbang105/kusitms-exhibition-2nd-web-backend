package kusitms.exihibition.product.application;

import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.product.domain.entity.Product;
import kusitms.exihibition.product.domain.repository.ProductRepository;
import kusitms.exihibition.product.dto.response.GetProductByTypeResponse;
import kusitms.exihibition.product.dto.response.GetProductDetailsResponse;
import kusitms.exihibition.product.status.ProductErrorStatus;
import kusitms.exihibition.team.domain.entity.Team;
import kusitms.exihibition.team.domain.enums.TeamType;
import kusitms.exihibition.team.domain.repository.TeamRepository;
import kusitms.exihibition.team.status.TeamErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final TeamRepository teamRepository;
    private final ProductRepository productRepository;

    // 유형 별 프로덕트 조회 메서드
    @Transactional(readOnly = true)
    public List<GetProductByTypeResponse> getProductsByType(String type) {
        List<Team> teams = switch (type) {
            case "all" -> teamRepository.findAll();
            case "yb" -> teamRepository.findByType(TeamType.YB);
            case "ob" -> teamRepository.findByType(TeamType.OB);
            case "other" -> teamRepository.findByType(TeamType.OTHER);
            default -> throw new CustomException(TeamErrorStatus._INVALID_TEAM_TYPE);
        };

        return teams.stream()
                .map(Team::getProduct)
                .map(GetProductByTypeResponse::from)
                .collect(Collectors.toList());
    }

    // 특정 프로덕트 상세 조회 메서드
    @Transactional(readOnly = true)
    public GetProductDetailsResponse getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ProductErrorStatus._NOT_FOUND_PRODUCT));

        return GetProductDetailsResponse.from(product);
    }
}

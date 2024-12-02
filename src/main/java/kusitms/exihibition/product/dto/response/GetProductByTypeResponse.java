package kusitms.exihibition.product.dto.response;

import kusitms.exihibition.product.domain.entity.Product;
import kusitms.exihibition.team.domain.enums.TeamType;

public record GetProductByTypeResponse(
        Long productId,
        String name,
        String introduction,
        String thumbnailUrl,
        String siteUrl
) {

    public static GetProductByTypeResponse from(Product product) {
        return new GetProductByTypeResponse(
                product.getId(),
                product.getName(),
                product.getIntroduction(),
                product.getThumbnailUrl(),
                product.getTeam().getType() == TeamType.OTHER ? product.getServiceUrl() : null
        );
    }
}

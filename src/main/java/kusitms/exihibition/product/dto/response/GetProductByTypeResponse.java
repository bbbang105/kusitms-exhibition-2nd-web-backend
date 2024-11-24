package kusitms.exihibition.product.dto.response;

import kusitms.exihibition.product.domain.entity.Product;

public record GetProductByTypeResponse(
        Long productId,
        String name,
        String introduction,
        String thumbnailUrl
) {

    public static GetProductByTypeResponse from(Product product) {
        return new GetProductByTypeResponse(
                product.getId(),
                product.getName(),
                product.getIntroduction(),
                product.getThumbnailUrl()
        );
    }
}

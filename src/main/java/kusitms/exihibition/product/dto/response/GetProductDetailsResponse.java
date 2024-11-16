package kusitms.exihibition.product.dto.response;

import kusitms.exihibition.product.domain.entity.Product;

public record GetProductDetailsResponse(
        String name,
        String category,
        String introduction,
        String description,
        String thumbnailUrl,
        String instagramUrl,
        String serviceUrl,
        String target,
        String problem,
        String solution
) {

    public static GetProductDetailsResponse from(Product product) {
        return new GetProductDetailsResponse(
                product.getName(),
                product.getCategory(),
                product.getIntroduction(),
                product.getDescription(),
                product.getThumbnailUrl(),
                product.getInstagramUrl(),
                product.getServiceUrl(),
                product.getTarget(),
                product.getProblem(),
                product.getSolution()
        );
    }
}
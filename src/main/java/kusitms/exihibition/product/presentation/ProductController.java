package kusitms.exihibition.product.presentation;

import kusitms.exihibition.global.dto.ApiResponse;
import kusitms.exihibition.product.application.ProductService;
import kusitms.exihibition.product.dto.response.GetProductByTypeResponse;
import kusitms.exihibition.product.dto.response.GetProductDetailsResponse;
import kusitms.exihibition.product.status.ProductSuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    /**
     * 유형별 프로덕트 조회 API
     *
     * 이 API는 팀의 유형별로 해당 팀에서 제작한 프로덕트 리스트를 조회합니다.
     *
     * @param type 조회할 팀의 유형 (예: all, yb, ob, other)
     * @return 선택된 팀 유형에 속하는 프로덕트 리스트 및 성공 메시지
     */
    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<List<GetProductByTypeResponse>>> getProductsByType(
            @PathVariable("type") String type
    ) {

        List<GetProductByTypeResponse> response = productService.getProductsByType(type);
        return ApiResponse.onSuccess(ProductSuccessStatus._GET_PRODUCTS_BY_TYPE, response);
    }

    /**
     * 특정 프로덕트 상세 조회 API
     *
     * 이 API는 특정 프로덕트의 상세 정보를 조회합니다.
     *
     * @param productId 조회할 프로덕트의 ID
     * @return 프로덕트 상세 정보 및 성공 메시지
     */
    @GetMapping("details/{product_id}")
    public ResponseEntity<ApiResponse<GetProductDetailsResponse>> getProductDetails(
            @PathVariable("product_id") Long productId
    ) {

        GetProductDetailsResponse response = productService.getProductDetails(productId);
        return ApiResponse.onSuccess(ProductSuccessStatus._GET_PRODUCT_DETAILS, response);
    }
}

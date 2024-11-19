package kusitms.exihibition.product.status;

import kusitms.exihibition.global.code.BaseCode;
import kusitms.exihibition.global.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductSuccessStatus implements BaseCode {
    _GET_PRODUCTS_BY_TYPE(HttpStatus.OK, "200", "유형 별 프로덕트 조회에 성공했습니다."),
    _GET_PRODUCT_DETAILS(HttpStatus.OK, "200", "프로덕트 상세 조회에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .isSuccess(true)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}

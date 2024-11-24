package kusitms.exihibition.member.status;


import kusitms.exihibition.global.code.BaseErrorCode;
import kusitms.exihibition.global.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorStatus implements BaseErrorCode {
    _INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "MEMBER-001", "올바른 멤버 유형이 아닙니다."),
    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}

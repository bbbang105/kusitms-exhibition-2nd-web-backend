package kusitms.exihibition.global.code;

import kusitms.exihibition.global.dto.ErrorReasonDto;

public interface BaseErrorCode {
    ErrorReasonDto getReason();
    ErrorReasonDto getReasonHttpStatus();
}
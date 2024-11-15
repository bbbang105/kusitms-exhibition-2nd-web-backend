package kusitms.exihibition.test.presentation;

import kusitms.exihibition.global.dto.ApiResponse;
import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.global.status.ErrorStatus;
import kusitms.exihibition.global.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/health-check")
    public ResponseEntity<ApiResponse<Object>> healthCheck(){

        return ApiResponse.onSuccess(SuccessStatus._OK, "Good!");
    }

    @GetMapping("/error")
    public void error(){
        throw new CustomException(ErrorStatus._BAD_REQUEST);
    }
}

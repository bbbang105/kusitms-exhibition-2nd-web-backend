package kusitms.exihibition.element;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import kusitms.exihibition.configuration.ControllerTestConfig;
import kusitms.exihibition.element.application.ElementService;
import kusitms.exihibition.element.dto.request.GetElementTestResultRequest;
import kusitms.exihibition.element.dto.response.GetElementTestResultResponse;
import kusitms.exihibition.element.presentation.ElementController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElementController.class)
public class ElementControllerTest extends ControllerTestConfig {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ElementService elementService;

    @Test
    @DisplayName("원소 테스트 결과를 생성하고 내용을 반환한다.")
    public void getElementTestResult_Success() throws Exception {
        // given
        GetElementTestResultRequest request = new GetElementTestResultRequest(
                List.of(1, 2, 1, 2, 1, 1, 1)
        );
        String requestContent = objectMapper.writeValueAsString(request);

        GetElementTestResultResponse response = new GetElementTestResultResponse(
                "질소",
                "N2",
                "https://storage.googleapis.com/kusitms-exhibition-bucket/element/질소.svg",
                "봉지 속 과자를 안전히 지켜주는 질소처럼 팀에서 부드럽게 중재와 조율을 잘 해주는 완충재 같은 존재예요. 그러나 모두를 배려하다 산으로 갈 수 있으니 팀의 목적이 무엇인지 항상 기억해두세요!",
                "이산화탄소",
                "아이디어 발상을 잘하는 이산화탄소의 의견들을 잘 수렴해주기 때문에 함께한다면 좋은 시너지를 낼 수 있을거예요!"
        );

        Mockito.when(elementService.getElementTestResult(Mockito.any(GetElementTestResultRequest.class)))
                .thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/v1/elements")
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("원소 테스트 결과 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload.koreanName").value("질소"))
                .andExpect(jsonPath("$.payload.englishName").value("N2"))
                .andExpect(jsonPath("$.payload.imgUrl").value("https://storage.googleapis.com/kusitms-exhibition-bucket/element/질소.svg"))
                .andExpect(jsonPath("$.payload.description").isNotEmpty())
                .andExpect(jsonPath("$.payload.fitElementName").value("이산화탄소"))
                .andExpect(jsonPath("$.payload.fitElementDescription").isNotEmpty())
                .andDo(print())
                .andDo(MockMvcRestDocumentationWrapper.document("element-test-result",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("Element")
                                        .description("원소 테스트 결과를 생성하고 내용을 반환한다.")
                                        .requestFields(
                                                fieldWithPath("selections").description("유저의 7개 선택 항목 리스트 (1 또는 2)")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload.koreanName").description("원소의 한글 이름"),
                                                fieldWithPath("payload.englishName").description("원소의 영어 이름"),
                                                fieldWithPath("payload.imgUrl").description("원소 이미지 URL"),
                                                fieldWithPath("payload.description").description("원소의 설명"),
                                                fieldWithPath("payload.fitElementName").description("궁합이 맞는 원소 이름"),
                                                fieldWithPath("payload.fitElementDescription").description("궁합이 맞는 원소 설명")
                                        )
                                        .requestSchema(Schema.schema("GetElementTestResultRequest"))
                                        .responseSchema(Schema.schema("GetElementTestResultResponse"))
                                        .build()
                        )
                ));
    }
}

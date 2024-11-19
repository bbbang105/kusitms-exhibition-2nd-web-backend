package kusitms.exihibition.product;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import kusitms.exihibition.configuration.ControllerTestConfig;
import kusitms.exihibition.product.application.ProductService;
import kusitms.exihibition.product.dto.response.GetProductByTypeResponse;
import kusitms.exihibition.product.dto.response.GetProductDetailsResponse;
import kusitms.exihibition.product.presentation.ProductController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest extends ControllerTestConfig {

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("유형별로 프로덕트를 조회한다.")
    public void getProductsByTypeTest() throws Exception {
        // given
        List<GetProductByTypeResponse> responses = List.of(
                new GetProductByTypeResponse("A팀 서비스명", "A팀 한줄 소개", "https://thumbnail.url/a"),
                new GetProductByTypeResponse("B팀 서비스명", "B팀 한줄 소개", "https://thumbnail.url/b")
        );

        Mockito.when(productService.getProductsByType(anyString())).thenReturn(responses);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/products/{type}", "yb")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("유형 별 프로덕트 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload[0].name").value("A팀 서비스명"))
                .andExpect(jsonPath("$.payload[0].introduction").value("A팀 한줄 소개"))
                .andExpect(jsonPath("$.payload[0].thumbnailUrl").value("https://thumbnail.url/a"))
                .andExpect(jsonPath("$.payload[1].name").value("B팀 서비스명"))
                .andExpect(jsonPath("$.payload[1].introduction").value("B팀 한줄 소개"))
                .andExpect(jsonPath("$.payload[1].thumbnailUrl").value("https://thumbnail.url/b"))
                .andDo(MockMvcRestDocumentationWrapper.document("product/get-products-by-type",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("Archive")
                                        .description("유형별로 프로덕트를 조회한다.")
                                        .pathParameters(
                                                parameterWithName("type").description("팀 유형 [예시 : all, yb, ob, other]")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload[].name").description("프로덕트 이름"),
                                                fieldWithPath("payload[].introduction").description("프로덕트 한줄 소개"),
                                                fieldWithPath("payload[].thumbnailUrl").description("프로덕트 썸네일 URL")
                                        )
                                        .responseSchema(Schema.schema("GetProductByTypeResponse"))
                                        .build()
                        )
                ));
    }

    @Test
    @DisplayName("특정 프로덕트의 상세 정보를 조회한다.")
    public void getProductDetailsTest() throws Exception {
        // given
        GetProductDetailsResponse response = new GetProductDetailsResponse(
                "A팀 서비스명",
                "A팀 카테고리",
                "A팀 한줄 소개",
                "A팀 상세 설명",
                "https://thumbnail.url/a",
                "https://instagram.url/a",
                "https://service.url/a",
                "A팀 타겟",
                "A팀 문제",
                "A팀 해결책"
        );

        Mockito.when(productService.getProductDetails(anyLong())).thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/v1/products/details/{product_id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("프로덕트 상세 조회에 성공했습니다."))
                .andExpect(jsonPath("$.payload.name").value("A팀 서비스명"))
                .andExpect(jsonPath("$.payload.category").value("A팀 카테고리"))
                .andExpect(jsonPath("$.payload.introduction").value("A팀 한줄 소개"))
                .andExpect(jsonPath("$.payload.description").value("A팀 상세 설명"))
                .andExpect(jsonPath("$.payload.thumbnailUrl").value("https://thumbnail.url/a"))
                .andExpect(jsonPath("$.payload.instagramUrl").value("https://instagram.url/a"))
                .andExpect(jsonPath("$.payload.serviceUrl").value("https://service.url/a"))
                .andExpect(jsonPath("$.payload.target").value("A팀 타겟"))
                .andExpect(jsonPath("$.payload.problem").value("A팀 문제"))
                .andExpect(jsonPath("$.payload.solution").value("A팀 해결책"))
                .andDo(MockMvcRestDocumentationWrapper.document("product/get-product-details",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("Archive")
                                        .description("특정 프로덕트의 상세 정보를 조회한다.")
                                        .pathParameters(
                                                parameterWithName("product_id").description("조회할 프로덕트의 ID [예시 : 1 (NUMBER Type)]")
                                        )
                                        .responseFields(
                                                fieldWithPath("isSuccess").description("성공 여부"),
                                                fieldWithPath("code").description("응답 코드"),
                                                fieldWithPath("message").description("응답 메시지"),
                                                fieldWithPath("payload.name").description("프로덕트 이름"),
                                                fieldWithPath("payload.category").description("프로덕트 카테고리"),
                                                fieldWithPath("payload.introduction").description("프로덕트 한줄 소개"),
                                                fieldWithPath("payload.description").description("프로덕트 상세 설명"),
                                                fieldWithPath("payload.thumbnailUrl").description("프로덕트 썸네일 URL"),
                                                fieldWithPath("payload.instagramUrl").description("프로덕트 Instagram URL"),
                                                fieldWithPath("payload.serviceUrl").description("프로덕트 서비스 URL"),
                                                fieldWithPath("payload.target").description("프로덕트 타겟"),
                                                fieldWithPath("payload.problem").description("프로덕트 문제"),
                                                fieldWithPath("payload.solution").description("프로덕트 솔루션")
                                        )
                                        .responseSchema(Schema.schema("GetProductDetailsResponse"))
                                        .build()
                        )
                ));
    }
}

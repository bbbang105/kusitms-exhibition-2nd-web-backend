package kusitms.exihibition.element.dto.response;

import kusitms.exihibition.element.domain.enums.ElementTestResult;

public record GetElementTestResultResponse(
        String koreanName,
        String englishName,
        String imgUrl,
        String description,
        String fitElementName,
        String fitElementDescription
        ){

    public static GetElementTestResultResponse from(ElementTestResult elementTestResult) {
        return new GetElementTestResultResponse(
                elementTestResult.getKoreanName(),
                elementTestResult.getEnglishName(),
                elementTestResult.getImgUrl(),
                elementTestResult.getDescription(),
                elementTestResult.getFitElementName(),
                elementTestResult.getFitElementDescription()
        );
    }
}
package kusitms.exihibition.element.application;

import kusitms.exihibition.element.domain.enums.ElementTestResult;
import kusitms.exihibition.element.dto.request.GetElementTestResultRequest;
import kusitms.exihibition.element.dto.response.GetElementTestResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElementService {

    private static final int QUESTION_7 = 6; // 7번째 질문
    private static final int QUESTION_6 = 5; // 6번째 질문
    private static final int QUESTION_5 = 4; // 5번째 질문
    private static final int QUESTION_3 = 2; // 3번째 질문

    // 원소 테스트 결과 생성 및 반환 메서드
    public GetElementTestResultResponse getElementTestResult(GetElementTestResultRequest request) {
        List<Integer> selections = request.selections();

        ElementTestResult elementTestResult = determineElementTestResult(selections);
        return GetElementTestResultResponse.from(elementTestResult);
    }

    // 조건별 ElementTestResult 결정 메서드
    private ElementTestResult determineElementTestResult(List<Integer> selections) {
        if (isFirstAnswer(selections, QUESTION_7)) {
            return isFirstAnswer(selections, QUESTION_6)
                    ? ElementTestResult.N2
                    : ElementTestResult.O2;
        } else {
            if (isFirstAnswer(selections, QUESTION_5)) {
                return isFirstAnswer(selections, QUESTION_3)
                        ? ElementTestResult.CO2
                        : ElementTestResult.He;
            } else {
                return ElementTestResult.Ar;
            }
        }
    }

    // 특정 질문에 대해 첫 번째 답변을 선택했는지 확인하는 메서드
    private boolean isFirstAnswer(List<Integer> selections, int questionIndex) {
        return selections.get(questionIndex) == 1;
    }
}

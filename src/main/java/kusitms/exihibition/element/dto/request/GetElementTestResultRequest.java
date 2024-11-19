package kusitms.exihibition.element.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GetElementTestResultRequest(
        @NotNull(message = "유저 선택 내역은 null일 수 없습니다.")
        @Size(min = 7, max = 7, message = "유저 선택 내역은 반드시 7개의 항목을 포함해야 합니다.")
        List<Integer> selections
) {
}
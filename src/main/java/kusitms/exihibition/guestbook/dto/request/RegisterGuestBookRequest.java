package kusitms.exihibition.guestbook.dto.request;

import jakarta.validation.constraints.NotBlank;
import kusitms.exihibition.guestbook.domain.entity.GuestBook;

public record RegisterGuestBookRequest(
        @NotBlank(message = "방명록 내용은 빈 값일 수 없습니다.") String content
) {

    public GuestBook toEntity() {
        return GuestBook.builder()
                .content(content)
                .build();
    }
}
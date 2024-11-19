package kusitms.exihibition.guestbook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kusitms.exihibition.guestbook.domain.entity.GuestBook;

public record RegisterGuestBookRequest(
        @NotBlank(message = "방명록 내용은 빈 값일 수 없습니다.")
        @Size(max = 200, message = "방명록 내용은 200자를 초과할 수 없습니다.")
        String content
) {

    public GuestBook toEntity() {
        return GuestBook.builder()
                .content(content)
                .build();
    }
}

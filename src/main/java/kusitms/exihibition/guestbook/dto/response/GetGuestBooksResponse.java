package kusitms.exihibition.guestbook.dto.response;

import kusitms.exihibition.global.util.DateUtil;
import kusitms.exihibition.guestbook.domain.entity.GuestBook;

public record GetGuestBooksResponse(
        String content,
        String createdDate
) {
    public static GetGuestBooksResponse from(GuestBook guestBook) {
        return new GetGuestBooksResponse(
                guestBook.getContent(),
                DateUtil.formatDateTime(guestBook.getCreatedDate())
        );
    }
}
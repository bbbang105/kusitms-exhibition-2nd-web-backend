package kusitms.exihibition.guestbook.dto.response;

import kusitms.exihibition.global.util.DateUtil;
import kusitms.exihibition.guestbook.domain.entity.GuestBook;

import java.util.List;

public record GetGuestBooksResponse(
        int totalGuestBookCount,
        int totalPageCount,
        List<GuestBookResponse> guestBooks
        ){

    public static GetGuestBooksResponse of(int totalGuestBookCount, int totalPageCount, List<GuestBookResponse> guestBooks) {
        return new GetGuestBooksResponse(
                totalGuestBookCount,
                totalPageCount,
                guestBooks
        );
    }

    public record GuestBookResponse(
            String content,
            String createdDate
    ) {
        public static GuestBookResponse from(GuestBook guestBook) {
            return new GuestBookResponse(
                    guestBook.getContent(),
                    DateUtil.formatDateTime(guestBook.getCreatedDate())
            );
        }
    }
}
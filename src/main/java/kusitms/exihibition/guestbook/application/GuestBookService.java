package kusitms.exihibition.guestbook.application;

import kusitms.exihibition.guestbook.domain.entity.GuestBook;
import kusitms.exihibition.guestbook.domain.repository.GuestBookRepository;
import kusitms.exihibition.guestbook.dto.request.RegisterGuestBookRequest;
import kusitms.exihibition.guestbook.dto.response.GetGuestBooksResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    // 방명록 등록 메서드
    @Transactional
    public void registerGuestBook(RegisterGuestBookRequest request) {
        GuestBook newGuestBook = request.toEntity();
        guestBookRepository.save(newGuestBook);
    }

    // 방명록 조회 메서드 (1페이지당 10개)
    @Transactional(readOnly = true)
    public GetGuestBooksResponse getGuestBooks(Pageable pageable) {
        Page<GuestBook> guestBooksPage = guestBookRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<GetGuestBooksResponse.GuestBookResponse> guestBooks = guestBooksPage.stream()
                .map(GetGuestBooksResponse.GuestBookResponse::from)
                .toList();

        int totalGuestBookCount = (int) guestBooksPage.getTotalElements();
        int totalPageCount = guestBooksPage.getTotalPages();

        return GetGuestBooksResponse.of(totalGuestBookCount, totalPageCount, guestBooks);
    }
}
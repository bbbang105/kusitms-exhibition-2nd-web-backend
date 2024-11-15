package kusitms.exihibition.guestbook.application;

import kusitms.exihibition.guestbook.domain.entity.GuestBook;
import kusitms.exihibition.guestbook.domain.repository.GuestBookRepository;
import kusitms.exihibition.guestbook.dto.request.RegisterGuestBookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Transactional
    public void registerGuestBook(RegisterGuestBookRequest request) {
        GuestBook newGuestBook = request.toEntity();
        guestBookRepository.save(newGuestBook);
    }
}
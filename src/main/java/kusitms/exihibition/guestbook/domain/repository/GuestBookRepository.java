package kusitms.exihibition.guestbook.domain.repository;

import kusitms.exihibition.guestbook.domain.entity.GuestBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    Page<GuestBook> findAllByOrderByCreatedDateDesc(Pageable pageable);
}

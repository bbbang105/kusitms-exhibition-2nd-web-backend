package kusitms.exihibition.guestbook.domain.entity;

import jakarta.persistence.*;
import kusitms.exihibition.global.dao.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "guestbooks")
public class GuestBook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guestbooks_id")
    private Long id;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Builder
    public GuestBook(String content) {
        this.content = content;
    }
}

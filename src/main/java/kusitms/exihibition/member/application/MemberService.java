package kusitms.exihibition.member.application;

import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.member.domain.enums.MemberType;
import kusitms.exihibition.member.domain.entity.Member;
import kusitms.exihibition.member.domain.repository.MemberRepository;
import kusitms.exihibition.member.dto.response.GetMembersByTypeResponse;
import kusitms.exihibition.member.status.MemberErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<GetMembersByTypeResponse> getMembersByType(String type) {
        List<Member> members;

        if (type == null || type.isBlank()) {
            // type이 null이면 전체 조회
            members = memberRepository.findAll();
        } else {
            switch (type) {
                case "tf":
                    members = memberRepository.findByType(MemberType.TF);
                    break;
                case "participants":
                    members = memberRepository.findByType(MemberType.PARTICIPANTS);
                    break;
                case "other":
                    members = memberRepository.findByType(MemberType.OTHER);
                    break;
                default:
                    throw new CustomException(MemberErrorStatus._INVALID_MEMBER_TYPE);
            }
        }

        return members.stream()
                .map(GetMembersByTypeResponse::from)
                .collect(Collectors.toList());
    }
}
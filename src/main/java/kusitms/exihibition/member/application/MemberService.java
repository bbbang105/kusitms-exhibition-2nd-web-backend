package kusitms.exihibition.member.application;

import kusitms.exihibition.global.exception.CustomException;
import kusitms.exihibition.member.domain.entity.Member;
import kusitms.exihibition.member.domain.enums.MemberType;
import kusitms.exihibition.member.domain.repository.MemberRepository;
import kusitms.exihibition.member.dto.response.GetMembersByTypeResponse;
import kusitms.exihibition.member.status.MemberErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private static final List<String> PART_ORDER = List.of(
            "PM / Planner", "PM / FE Developer", "PM / Designer", "Planner", "Designer", "FE Developer", "BE Developer"
    );

    // 유형 별 멤버 조회 메서드
    @Transactional(readOnly = true)
    public List<GetMembersByTypeResponse> getMembersByType(String type) {
        List<Member> members = switch (type) {
            case "all" -> memberRepository.findAll();
            case "tf" -> memberRepository.findByType(MemberType.TF);
            case "participants" -> memberRepository.findByType(MemberType.PARTICIPANTS);
            case "other" -> memberRepository.findByType(MemberType.OTHER);
            default -> throw new CustomException(MemberErrorStatus._INVALID_MEMBER_TYPE);
        };

        // 중복 필터링 및 파트 기준 정렬 적용
        return filterAndSortMembers(members).stream()
                .map(GetMembersByTypeResponse::from)
                .collect(Collectors.toList());
    }

    // 중복 필터링 및 정렬 메서드
    private List<Member> filterAndSortMembers(List<Member> members) {
        // 중복 제거 로직
        List<Member> distinctMembers = members.stream()
                .collect(Collectors.groupingBy(Member::getName, Collectors.toList()))
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    List<Member> memberList = entry.getValue();
                    if (entry.getKey().equals("김서연")) {
                        return memberList.stream(); // 동명이인이므로 모두 포함
                    } else {
                        return memberList.stream().limit(1); // 중복 제거
                    }
                })
                .toList();

        // 한글 이름 정렬
        List<Member> sortedByName = distinctMembers.stream()
                .sorted(Comparator.comparing(Member::getName, Comparator.naturalOrder()))
                .toList();

        System.out.print(sortedByName.size());

        // 파트 순서 정렬
        return sortedByName.stream()
                .sorted(Comparator.comparingInt(member -> {
                    String part = member.getPart();
                    return PART_ORDER.contains(part) ? PART_ORDER.indexOf(part) : Integer.MAX_VALUE;
                }))
                .toList();
    }
}

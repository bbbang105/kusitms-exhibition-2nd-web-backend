package kusitms.exihibition.member.dto.response;

import kusitms.exihibition.member.domain.entity.Member;

public record GetMembersByTypeResponse(
        String name,
        String imgUrl,
        String part,
        String instagramUrl,
        String linkedinUrl,
        String githubUrl,
        String behanceUrl

) {
    public static GetMembersByTypeResponse from(Member member) {
        return new GetMembersByTypeResponse(
                member.getName(),
                member.getImgUrl(),
                member.getPart(),
                member.getInstagramUrl(),
                member.getLinkedinUrl(),
                member.getGithubUrl(),
                member.getBehanceUrl()
        );
    }
}
package kusitms.exihibition.team.dto.response;

import kusitms.exihibition.member.domain.entity.Member;
import kusitms.exihibition.team.domain.entity.Team;

import java.util.List;

public record GetTeamInfosResponse(
        String teamName,
        String generation,
        String origin,
        String description,
        List<MemberInfo> memberInfos

) {
    public static GetTeamInfosResponse of(Team team, List<MemberInfo> memberInfos) {
        return new GetTeamInfosResponse(
                team.getName(),
                team.getGeneration(),
                team.getOrigin(),
                team.getDescription(),
                memberInfos
        );
    }

    public record MemberInfo(
            String name,
            String imgUrl,
            String part,
            String instagramUrl,
            String linkedinUrl,
            String githubUrl,
            String behanceUrl

    ) {
        public static MemberInfo from(Member member) {
            return new MemberInfo(
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
}

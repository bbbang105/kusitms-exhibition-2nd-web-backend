package kusitms.exihibition.element.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElementTestResult {

    N2(
            "질소",
            "N2",
            "https://storage.googleapis.com/kusitms-exhibition-bucket/element/N2.svg",
            "봉지 속 과자를 안전히 지켜주는 질소처럼 팀에서 부드럽게 중재와 조율을 잘 해주는 완충재 같은 존재예요. 그러나 모두를 배려하다 산으로 갈 수 있으니 팀의 목적이 무엇인지 항상 기억해두세요!",
            "이산화탄소",
            "아이디어 발상을 잘하는 이산화탄소의 의견들을 잘 수렴해주기 때문에 함께한다면 좋은 시너지를 낼 수 있을거예요!"
    ),
    CO2(
            "이산화탄소",
            "CO2",
            "https://storage.googleapis.com/kusitms-exhibition-bucket/element/CO2.svg",
            "들이쉬기 보다 내쉬기를 잘하는 당신. 가끔 4차원이라는 소리도 들어요. 수렴은 약하지만 발산 능력이 좋은 비상한 아이디어 천재만재! 그러나 아이디어를 내뿜는 것도 좋지만 현실적인 상황을 냉철하게 판단하는 것도 중요함을 기억해주세요.",
            "아르곤",
            "현실적인 상황을 고려해 냉철하고 강단있는 결정을 내리는 아르곤과 함께라면, 아이디어를 구체적인 실현 방안까지 연결짓기 수월할거예요!"
    ),
    O2(
            "산소",
            "O2",
            "https://storage.googleapis.com/kusitms-exhibition-bucket/element/O2.svg",
            "언제나 자리를 지키며 부지런하고 성실하고 묵묵하게 팀을 서포트하는 존재예요. 팀플의 방향성을 체크하고 노선에서 이탈하지 않게 잡아주는 당신. 스스로를 뽐내지는 않지만 한결같은 당신이 없다면 팀은 삐그덕거리고 말거예요. 그러나 때로는 스스로의 성과를 증명해내는 것도 필요하답니다.",
            "아르곤",
            "현실적인 상황을 고려해 냉철하고 강단있는 결정을 내리는 아르곤과 함께라면, 아이디어를 구체적인 실현 방안까지 연결짓기 수월할거예요!"
    ),
    Ar(
            "아르곤",
            "Ar",
            "https://storage.googleapis.com/kusitms-exhibition-bucket/element/Ar.svg",
            "가장 앞단에서 훌륭한 말솜씨로 팀을 멋있게 대변하고 카리스마 있게 결단내리는 아빠같은 존재인 당신! 날카로운 촉으로 냉철하게 비판해요. 그러나 때로는 부드러운 포용으로 다양한 의견을 수렴한다면 예상과 다른 굉장한 결과가 나올 수도 있다는 사실 기억해주세요.",
            "헬륨",
            "긴장된 분위기를 유머러스하게 풀어내는 헬륨과 함께라면, 아르곤에게 부족한 부분을 헬륨이 채워줘 팀의 분위기가 화기애애할 거예요!"
    ),
    He(
            "헬륨",
            "He",
            "https://storage.googleapis.com/kusitms-exhibition-bucket/element/He.svg",
            "눈치가 빠르고 어려운 상황도 웃음짓게 만드는 농담쟁이 유머감각 만점 우리만의 개그맨! 팀원들의 사기를 올려 없으면 허전한 존재예요! 그러나 때로는 진지한 모습으로 반전 매력을 보인다면, 사회성뿐 아니라 능력까지 있는 사람으로 모두에게 각인될거예요!",
            "이산화탄소",
            "당신의 각종 드립과 개그를 스펀지처럼 흡수해 잘 받아치는 이산화탄소와 함께라면, 팀의 사기를 유지하는데 도움이 될거예요!"
    ),
    ;

    private final String koreanName;
    private final String englishName;
    private final String imgUrl;
    private final String description;
    private final String fitElementName;
    private final String fitElementDescription;
}

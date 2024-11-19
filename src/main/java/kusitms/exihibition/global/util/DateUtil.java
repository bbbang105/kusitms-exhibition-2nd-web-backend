package kusitms.exihibition.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DateUtil {

    private static final DateTimeFormatter CUSTOM_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

    /**
     * LocalDateTime 객체를 지정된 포맷으로 문자열로 변환합니다.
     *
     * @param dateTime 변환할 LocalDateTime 객체
     * @return 변환된 날짜 문자열
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(CUSTOM_FORMATTER);
    }
}
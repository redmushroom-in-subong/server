package com.rms.drifeserver.domain.common.exception.type;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request
    INVALID("BR000", "잘못된 요청입니다"),
    INVALID_TYPE("BR001", "잘못된 타입이 입력되었습니다"),

    INVALID_MISSING_PARAMETER("BR100", "필수 파라미터가 입력되지 않았습니다"),
    INVALID_MISSING_LATITUDE("BR101", "현재 위치의 위도를 입력해주세요 (latitude)"),
    INVALID_MISSING_LONGITUDE("BR102", "현재 위치의 경도를 입력해주세요 (longitude)"),
    INVALID_MISSING_MAP_LATITUDE("BR103", "지도상 위도를 입력해주세요 (mapLatitude)"),
    INVALID_MISSING_MAP_LONGITUDE("BR104", "지도상 경도를 입력해주세요 (mapLongitude)"),
    INVALID_MISSING_AUTH_TOKEN("BR105", "인증 토큰을 입력해주세요"),

    INVALID_AUTH_TOKEN( "BR200", "만료되거나 유효하지 않은 인증 토큰입니다"),
    INVALID_CONTACTS_NUMBER_FORMAT( "BR201", "잘못된 연락처 번호입니다."),
    INVALID_BUSINESS_NUMBER_FORMAT("BR202", "잘못된 사업자 번호입니다"),
    INVALID_RATING_RANGE("BR203", "잘못된 평가 점수입니다\n(1 ~ 5 사이의 평가 점수를 입력해주세요)"),
    INVALID_LATITUDE_RANGE( "BR204", "잘못된 위도 범위를 입력하였습니다\n(33.1 ~ 38.61 사이의 위도를 입력해주세요)"),
    INVALID_LONGITUDE_RANGE("BR205", "잘못된 경도 범위를 입력하였습니다\n(124.60 ~ 131.87 사이의 경도를 입력해주세요)"),
    INVALID_EMPTY_UPLOAD_FILE_NAME( "BR206", "잘못된 파일입니다\n파일의 이름이 없습니다."),
    INVALID_UPLOAD_FILE_TYPE("BR207", "잘못된 파일 확장자입니다"),
    INVALID_UPLOAD_FILE_SIZE("BR208", "업로드 가능한 파일 크기를 초과했습니다"),
    INVALID_BADGE_CODE( "BR209", "잘못된 뱃지코드입니다."),

    // 401 UnAuthorized
    UNAUTHORIZED("UA000", "세션이 만료되었습니다. 다시 로그인 해주세요"),


    // 403 Forbidden
    FORBIDDEN( "FB000", "허용하지 않는 요청입니다"),


    // 404 Not Found
    NOTFOUND("NF000", "존재하지 않습니다"),
    NOTFOUND_USER( "NF001", "탈퇴하거나 존재하지 않는 유저입니다"),
    NOTFOUND_STORE( "NF002", "삭제되거나 존재하지 않는 가게입니다"),
    NOTFOUND_REVIEW("NF003", "삭제되거나 존재하지 않는 리뷰입니다"),
    NOTFOUND_BOARD("NF004", "삭제되거나 존재하지 않는 게시글입니다"),
    NOTFOUND_COMMENT("NF005", "삭제되거나 존재하지 않는 댓글입니다"),
    NOTFOUND_STORE_IMAGE("NF006", "삭제되거나 존재하지 않는 가게 이미지입니다"),
    NOTFOUND_FAQ("NF007", "삭제되거나 존재하지 않는 FAQ입니다"),
    NOTFOUND_BADGE("N008", "보유하고 있지 않은 뱃지입니다"),
    NOTFOUND_BOSS("N009", "존재하지 않는 사장님 계정입니다"),
    NOTFOUND_BOSS_OWNED_STORE("NF010", "사장님이 운영하는 가게가 존재하지 않습니다."),
    NOTFOUND_SIGNUP_REGISTRATION("NF011", "해당하는 가입 신청은 존재하지 않습니다"),


    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED( "MN000", "Not Allowed Method"),


    // 406 Not Acceptable
    NOT_ACCEPTABLE( "NA000", "Not Acceptable"),


    // 409 Conflict
    CONFLICT( "CF000", "이미 존재합니다"),
    CONFLICT_NICKNAME("CF001", "이미 사용중인 닉네임입니다.\n다른 닉네임을 이용해주세요"),
    CONFLICT_USER("CF002", "이미 해당 계정으로 회원가입하셨습니다.\n로그인 해주세요"),
    CONFLICT_DELETE_REQUEST_STORE("CF003", "이미 해당하는 가게에 삭제요청 하였습니다."),
    CONFLICT_VISIT_HISTORY( "CF004", "오늘 이미 방문 인증한 가게입니다.\n다음에 다시 인증해주세요"),
    CONFLICT_REGISTER_BOSS("CF005", "이미 사장님 가입을 신청하셨습니다"),
    CONFLICT_BADGE_NAME( "CF006", "이미 사용중인 뱃지코드입니다."),


    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE("UM000", "Unsupported Media Type"),


    // 500 Internal Server Exception
    INTERNAL_SERVER("IS000", "예상치 못한 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),
    INTERNAL_SERVER_UPDATE_STORE_OPTIMISTIC_LOCK_FAILED( "IS002", "일시적으로 다른 사용자와 동시에 가게 수정 요청을 하였습니다ㅠㅠ\n잠시 후 다시 시도해주세요!"),


    // 502 Bad Gateway
    BAD_GATEWAY("BG000", "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),


    // 503 Service UnAvailable
    SERVICE_UNAVAILABLE("SU000", "현재 해당 기능은 점검 중입니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

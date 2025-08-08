package inmobi.guess_number.exception;

public enum ErrorCode {
    USER_EXISTED(500, "User Existed"),
    USER_NOT_EXISTED(501,"USER NOT EXISTED"),
    UNAUTHORIZED(401, "Unauthorized"),
    NO_TURNS_LEFT(404, "NO MORE TURNS LEFT"),
            ;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}

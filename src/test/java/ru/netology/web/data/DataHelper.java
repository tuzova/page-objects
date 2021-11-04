package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class TransferData {
        private String amount1;
        private String amount2;
        private String amount3;
        private String cardFirst;
        private String cardSecond;
    }

    public static TransferData getTransferData(AuthInfo authInfo) {
        return new TransferData("500", "10500", "100","5559000000000001", "5559000000000002");
    }

}
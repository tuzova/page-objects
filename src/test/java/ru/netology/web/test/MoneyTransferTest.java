package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyToFirstCard() {

        open("http://localhost:7777");
        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo(); // берем данные авторизации
        var verificationPage = loginPage.validLogin(authInfo); // переход на verificationPage с loginPage

        var verificationCode = DataHelper.getVerificationCodeFor(authInfo); // берем данные кода верификации
        var dashboardPage = verificationPage.validVerify(verificationCode); // переход на dashboardPage с verificationPage

        var firstCardBalance = dashboardPage.getFirstCardBalance(); // начальная проверка баланса карт
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var transferAmount = DataHelper.getTransferData(authInfo); // берем данные по трансферу
        var transferPage = dashboardPage.transferToFirstCard(transferAmount); // переход на transferPage с dashboardPage

        var firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance(); // проверка баланса карт после перевода
        var secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferMoneyToSecondCard() {

        open("http://localhost:7777");
        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo(); // берем данные авторизации
        var verificationPage = loginPage.validLogin(authInfo); // переход на verificationPage с loginPage

        var verificationCode = DataHelper.getVerificationCodeFor(authInfo); // берем данные кода верификации
        var dashboardPage = verificationPage.validVerify(verificationCode); // переход на dashboardPage с verificationPage

        var firstCardBalance = dashboardPage.getFirstCardBalance(); // начальная проверка баланса карт
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var transferAmount = DataHelper.getTransferData(authInfo); // берем данные по трансферу
        var transferPage = dashboardPage.transferToSecondCard(transferAmount); // переход на transferPage с dashboardPage

        var firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance(); // проверка баланса карт после перевода
        var secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldNotTransferIfZeroBalance() {

        open("http://localhost:7777");
        var loginPage = new LoginPage();

        var authInfo = DataHelper.getAuthInfo(); // берем данные авторизации
        var verificationPage = loginPage.validLogin(authInfo); // переход на verificationPage с loginPage

        var verificationCode = DataHelper.getVerificationCodeFor(authInfo); // берем данные кода верификации
        var dashboardPage = verificationPage.validVerify(verificationCode); // переход на dashboardPage с verificationPage

        var firstCardBalance = dashboardPage.getFirstCardBalance(); // начальная проверка баланса карт
        var secondCardBalance = dashboardPage.getSecondCardBalance();

        var transferAmount = DataHelper.getTransferData(authInfo); // берем данные по трансферу
        var transferPage = dashboardPage.transferToSecondCardIfZero(transferAmount); // переход на transferPage с dashboardPage

        var firstCardBalanceAfterTransfer = dashboardPage.getFirstCardBalance(); // проверка баланса карт после перевода
        var secondCardBalanceAfterTransfer = dashboardPage.getSecondCardBalance();
    }
}

package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyToFirstCard() {

        open("http://localhost:7777");
        String transferAmount = "500";
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo(); // берем данные авторизации
        var verificationPage = loginPage.validLogin(authInfo); // переход на verificationPage с loginPage
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo); // берем данные кода верификации
        var dashboardPage = verificationPage.validVerify(verificationCode); // переход на dashboardPage с verificationPage
        var firstCardBalanceInitial = dashboardPage.getFirstCardBalance(); // получение начального баланса карт
        var secondCardBalanceInitial = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.transferToFirstCard(); // переход на transferPage с dashboardPage
        var secondCardData = DataHelper.getCardsData(authInfo); // берем данные второй карты
        var transferAction = transferPage.transferToFirstCard(secondCardData, transferAmount);
        var firstCardBalanceFinal = dashboardPage.getFirstCardBalance(); // получение конечного баланса карт
        var secondCardBalanceFinal = dashboardPage.getSecondCardBalance();
        var firstCardBalanceFinalExpected = firstCardBalanceInitial + Integer.parseInt(transferAmount);
        var secondCardBalanceFinalExpected = secondCardBalanceInitial - Integer.parseInt(transferAmount);
        assertEquals(firstCardBalanceFinal, firstCardBalanceFinalExpected);
        assertEquals(secondCardBalanceFinal, secondCardBalanceFinalExpected);
    }

    @Test
    void shouldTransferMoneyToSecondCard() {

        open("http://localhost:7777");
        String transferAmount = "500";
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalanceInitial = dashboardPage.getFirstCardBalance();
        var secondCardBalanceInitial = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.transferToSecondCard();
        var firstCardData = DataHelper.getCardsData(authInfo);
        var transferAction = transferPage.transferToSecondCard(firstCardData, transferAmount);
        var firstCardBalanceFinal = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinal = dashboardPage.getSecondCardBalance();
        var firstCardBalanceFinalExpected = firstCardBalanceInitial - Integer.parseInt(transferAmount);
        var secondCardBalanceFinalExpected = secondCardBalanceInitial + Integer.parseInt(transferAmount);
        assertEquals(firstCardBalanceFinal, firstCardBalanceFinalExpected);
        assertEquals(secondCardBalanceFinal, secondCardBalanceFinalExpected);
    }

    @Test
    void shouldNotTransferMoneyIfNegativeBalance() {

        open("http://localhost:7777");
        String transferAmount = "30000";
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalanceInitial = dashboardPage.getFirstCardBalance();
        var secondCardBalanceInitial = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.transferToSecondCard();
        var firstCardData = DataHelper.getCardsData(authInfo);
        var transferAction = transferPage.transferToSecondCard(firstCardData, transferAmount);
        transferPage.warningMessage();
    }
}


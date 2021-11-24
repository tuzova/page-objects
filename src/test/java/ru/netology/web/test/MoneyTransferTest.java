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
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalanceInitial = dashboardPage.getFirstCardBalance();
        var secondCardBalanceInitial = dashboardPage.getSecondCardBalance();
        var transferPage = dashboardPage.transferToFirstCard();
        var cardsData = DataHelper.getCardsData(authInfo);
        var transferAction = transferPage.transfer(cardsData.getCardSecond(), transferAmount);
        var firstCardBalanceFinal = dashboardPage.getFirstCardBalance();
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
        var cardsData = DataHelper.getCardsData(authInfo);
        var transferAction = transferPage.transfer(cardsData.getCardFirst(), transferAmount);
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
        var cardsData = DataHelper.getCardsData(authInfo);
        var transferAction = transferPage.transfer(cardsData.getCardFirst(), transferAmount);
        transferPage.warningMessage();
        var firstCardBalanceFinal = dashboardPage.getFirstCardBalance();
        var secondCardBalanceFinal = dashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceFinal, firstCardBalanceInitial);
        assertEquals(secondCardBalanceFinal, secondCardBalanceInitial);
    }
}


package ru.netology.web.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement transferCardField = $("[data-test-id='from'] input");
    private SelenideElement actionTransferButton = $("[data-test-id=action-transfer]");


    // метод перевода со второй карты на первую
    public DashboardPage transferToFirstCard(DataHelper.Cards cardData, String transferAmount) {
        amountField.setValue(transferAmount);
        transferCardField.setValue(cardData.getCardSecond());
        actionTransferButton.click();
        return new DashboardPage();
    }

    // метод перевода с первой карты на вторую
    public DashboardPage transferToSecondCard(DataHelper.Cards cardData, String transferAmount) {
        amountField.setValue(transferAmount);
        transferCardField.setValue(cardData.getCardFirst());
        actionTransferButton.click();
        return new DashboardPage();
    }

    public void warningMessage() {
        $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='error-notification'] div.notification__content").shouldBe(visible);
        $(withText("Неверно указана сумма перевода")).shouldBe(visible);
    }
}

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

    public DashboardPage transfer(String cardNumber, String transferAmount) {
        amountField.setValue(transferAmount);
        transferCardField.setValue(cardNumber);
        actionTransferButton.click();
        return new DashboardPage();
    }

    public DashboardPage warningMessage() {
        $(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='error-notification'] div.notification__content").shouldBe(visible);
        $(withText("Неверно указана сумма перевода")).shouldBe(visible);
        return new DashboardPage();
    }
}

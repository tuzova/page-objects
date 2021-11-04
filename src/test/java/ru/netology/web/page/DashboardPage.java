package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private SelenideElement transferFirstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement transferSecondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement transferCardField = $("[data-test-id='from'] input");
    private SelenideElement actionTransferButton = $("[data-test-id=action-transfer]");

    // метод перевода со второй карты на первую
    public DashboardPage transferToFirstCard(DataHelper.TransferData transferData) {
        transferFirstCardButton.click();
        amountField.setValue(transferData.getAmount1());
        transferCardField.setValue(transferData.getCardSecond());
        actionTransferButton.click();
        return new DashboardPage();
    }

    // метод перевода с первой карты на вторую
    public DashboardPage transferToSecondCard(DataHelper.TransferData transferData) {
        transferSecondCardButton.click();
        amountField.setValue(transferData.getAmount2());
        transferCardField.setValue(transferData.getCardFirst());
        actionTransferButton.click();
        return new DashboardPage();
    }

    // метод перевода с первой карты на вторую при нулевом балансе
    public DashboardPage transferToSecondCardIfZero(DataHelper.TransferData transferData) {
        transferSecondCardButton.click();
        amountField.setValue(transferData.getAmount3());
        transferCardField.setValue(transferData.getCardFirst());
        actionTransferButton.click();
        return new DashboardPage();
    }

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    // метод проверки баланса по картам (по индексу)

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
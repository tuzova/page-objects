package ru.netology.web.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement transferCardField = $("[data-test-id='from'] input");
    private SelenideElement actionTransferButton = $("[data-test-id=action-transfer]");

    // метод перевода со второй карты на первую
    public TransferPage transferToFirstCard(DataHelper.Cards cardData, String transferAmount) {
        amountField.setValue(transferAmount);
        transferCardField.setValue(cardData.getCardSecond());
        actionTransferButton.click();
        return new TransferPage();
    }

    // метод перевода с первой карты на вторую
    public TransferPage transferToSecondCard(DataHelper.Cards cardData, String transferAmount) {
        amountField.setValue(transferAmount);
        transferCardField.setValue(cardData.getCardFirst());
        actionTransferButton.click();
        return new TransferPage();
    }
}


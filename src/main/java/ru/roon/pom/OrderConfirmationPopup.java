package ru.roon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmationPopup {
    private final WebDriver driver;
    //Заголовок всплывающего окна
    private final By headerPopup = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text() ='Хотите оформить заказ?']");
    // Кнопка "Да"
    private final By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    public OrderConfirmationPopup(WebDriver driver){
        this.driver = driver;
    }



    public void confirmOrder() {
        waitLoadedOrderConfirmationPopup();
        driver.findElement(yesButton).click();
    }

    private void waitLoadedOrderConfirmationPopup() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerPopup));
    }
}

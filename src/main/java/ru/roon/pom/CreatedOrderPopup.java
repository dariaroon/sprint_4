package ru.roon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreatedOrderPopup {
    private final WebDriver driver;
    //Заголовок всплывающего окна
    private final By headerPopup = By.xpath(".//div[@class = 'Order_ModalHeader__3FDaJ' and contains(text(), 'Заказ оформлен')]");
    // Кнопка "Посмотреть статус"
    private final By showStatusButton = By.xpath(".//button[text()='Посмотреть статус']");

    public CreatedOrderPopup(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerPopup));
        return driver.findElement(headerPopup).isDisplayed()
                && driver.findElement(showStatusButton).isDisplayed();
    }
}

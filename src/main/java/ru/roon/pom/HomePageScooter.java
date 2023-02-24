package ru.roon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageScooter {
    private final WebDriver driver;
    private static final String xpathQuestionTemplate = ".//div[text() = '%s']";
    //Кнопка принятия cookie "Да все привыкли"
    private final By cookieButton = By.id("rcc-confirm-button");
    //Блок с вопросами о важном
    private final By divFAQ = By.className("Home_FAQ__3uVm4");
    // Открытый ответ
    private final By openedAnswer = By.xpath(".//div[@class = 'accordion__panel' and not(@hidden)]/p");
    // Верхняя кнопка заказа
    private final By topOrderButton = By.className("Button_Button__ra12g");
    // Нижняя кнопка заказа
    private final By downOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToFAQ() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(divFAQ));
    }

    public String openQuestion(String question) {
        WebElement questionElement = driver.findElement(By.xpath(String.format(xpathQuestionTemplate, question)));
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(questionElement));
        questionElement.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(questionElement, "aria-expanded", "true"));
        return driver.findElement(openedAnswer).getText();
    }

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void clickTopOrderButton() {
        WebElement button = driver.findElement(topOrderButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public void clickDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        WebElement button = driver.findElement(downOrderButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }
}

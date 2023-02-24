package ru.roon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstPageOrder {
    private static final String MATCHED_METRO_STATION_TEMPLATE = ".//div[@class='Order_Text__2broi' and text()='%s']";
    //Заголовок первой вормы
    private final By firstFormHeader = By.xpath(".//div[@class = 'Order_Header__BZXOb' and text() = 'Для кого самокат']");
    //Поле  Имя
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле Фамилия
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле Адрес
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Выпадающий список метро
    private final By metroList = By.xpath(".//ul[@class='select-search__options']");
    //Поле Телефон
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By buttonNext = By.xpath(".//button[text()='Далее']");
    private final WebDriver driver;

    public FirstPageOrder(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void fillAndComplete(String firstName,
                                String lastName,
                                String address,
                                String metroStation,
                                String phoneNumber) {
        waitUntilFirstFormLoaded();
        enterValueIntoField(firstNameField, firstName);
        enterValueIntoField(lastNameField, lastName);
        enterValueIntoField(addressField, address);
        selectMetroStation(metroStation);
        enterValueIntoField(phoneNumberField, phoneNumber);
        clickButtonNext();
    }

    private void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    private void selectMetroStation(String metroStation) {
        driver.findElement(metroStationField).clear();
        driver.findElement(metroStationField).sendKeys(metroStation);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(metroList));
        driver.findElement(By.xpath(String.format(MATCHED_METRO_STATION_TEMPLATE, metroStation))).click();
    }

    private void enterValueIntoField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    private void waitUntilFirstFormLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(firstFormHeader));
    }
}

package ru.roon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondPageOrder {

    private static final String RENTAL_PERIOD_SELECTOR_TEMPLATE = ".//*[text() = '%s']";
    private static final String COLOR_SELECTOR_TEMPLATE = ".//label[@class = 'Checkbox_Label__3wxSf' and text() = '%s']";
    private final WebDriver driver;
    //Заголовок второй формы
    private final By secondFormHeader = By.xpath(".//div[text()='Про аренду']");
    //Поле для ввода даты доставки самоката
    private final By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Календарь
    private final By calendar = By.xpath(".//div[@class = 'react-datepicker']");
    //Выбранный день в календаре
    private final By selectedDay = By.xpath(".//div[contains(@class, 'datepicker__day--selected')]");
    //Поле для ввода продолжительности проката
    private final By rentalPeriodField = By.xpath(".//div[@class = 'Dropdown-placeholder' and text() = '* Срок аренды']");
    //Выпадающий список продолжительности проката
    private final By rentalPeriodList = By.xpath(".//div[@class = 'Dropdown-menu']");

    //Поле Коментарий
    private final By commentForCouriersField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    // Кнопка "Заказать"
    private final By submitButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    public SecondPageOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void fillAndComplete(String deliveryDate, String rentalPeriod, String commentForCourier, String color) {
        waitUntilSecondFormLoaded();
        selectDate(deliveryDate);
        selectRentalPeriod(rentalPeriod);
        selectColor(color);
        enterValueIntoField(commentForCouriersField, commentForCourier);
        clickSubmitButton();
    }

    private void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    private void selectColor(String color) {
        driver.findElement(By.xpath(String.format(COLOR_SELECTOR_TEMPLATE, color))).click();
    }

    private void selectRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodList));
        driver.findElement(By.xpath(String.format(RENTAL_PERIOD_SELECTOR_TEMPLATE, rentalPeriod))).click();

    }

    private void waitUntilSecondFormLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(secondFormHeader));

    }

    private void selectDate(String date) {
        driver.findElement(deliveryDateField).clear();
        driver.findElement(deliveryDateField).sendKeys(date);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(calendar));
        driver.findElement(selectedDay).click();
    }

    private void enterValueIntoField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
}

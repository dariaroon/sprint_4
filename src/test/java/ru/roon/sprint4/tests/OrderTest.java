package ru.roon.sprint4.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.roon.pom.*;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru";

    private WebDriver driver;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String commentForCourier;
    private final String color;
    private HomePageScooter homePage;
    private FirstPageOrder firstPageOrder;
    private SecondPageOrder secondPageOrder;
    private OrderConfirmationPopup orderConfirmationPopup;
    private CreatedOrderPopup createdOrderPopup;

    public OrderTest(String firstName,
                     String lastName,
                     String address,
                     String metroStation,
                     String phoneNumber,
                     String deliveryDate,
                     String rentalPeriod,
                     String commentForCourier,
                     String color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.commentForCourier = commentForCourier;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Оформление заказа. Тестовые данные: {0} {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Петр", "Петров", "Москва", "Черкизовская", "+79999999999", "01.01.2023", "сутки", "Комментарий1", "чёрный жемчуг"},
                {"Иван", "Иванов", "Москва", "Черкизовская", "+79999999999", "01.01.2023", "трое суток", "Комментарий2", "серая безысходность"},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(URL);
        homePage = new HomePageScooter(driver);
        firstPageOrder = new FirstPageOrder(driver);
        secondPageOrder = new SecondPageOrder(driver);
        orderConfirmationPopup = new OrderConfirmationPopup(driver);
        createdOrderPopup = new CreatedOrderPopup(driver);
        homePage.acceptCookies();
    }

    @Test
    public void orderFromTopButton() {
        homePage.clickTopOrderButton();
        firstPageOrder.fillAndComplete(firstName, lastName, address, metroStation, phoneNumber);
        secondPageOrder.fillAndComplete(deliveryDate, rentalPeriod, commentForCourier, color);
        orderConfirmationPopup.confirmOrder();
        assertTrue(createdOrderPopup.isVisible());
    }

    @Test
    public void orderFromDownButton() {
        homePage.clickDownOrderButton();
        firstPageOrder.fillAndComplete(firstName, lastName, address, metroStation, phoneNumber);
        secondPageOrder.fillAndComplete(deliveryDate, rentalPeriod, commentForCourier, color);
        orderConfirmationPopup.confirmOrder();
        assertTrue(createdOrderPopup.isVisible());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

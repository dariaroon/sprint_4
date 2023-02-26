package ru.roon.sprint4.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.roon.pom.HomePageScooter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(Parameterized.class)
public class ScooterFAQTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru";
    private final String questionFAQ;
    private final String expectedAnswerFAQ;
    private WebDriver driver;


    public ScooterFAQTest(String questionFAQ, String expectedAnswerFAQ) {
        this.questionFAQ = questionFAQ;
        this.expectedAnswerFAQ = expectedAnswerFAQ;
    }

    @Parameterized.Parameters(name = "Соответсвие ответов вопросам в разделе FAQ. Тестовые данные: {0} {1} {2} {3} {4} {5} {6} {7}")
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkFAQ() {
        driver.get(URL);
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        homePageScooter.scrollToFAQ();
        homePageScooter.acceptCookies();
        String actualAnswer = homePageScooter.openQuestion(questionFAQ);
        assertThat("Текст ответа не соответствует вопросу.", actualAnswer, is(expectedAnswerFAQ));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

import ru.yandex.praktikum.samokat.HomePage;
import ru.yandex.praktikum.samokat.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String name;
    private final String lastName;
    private final String city;
    private final String metro;
    private final String phone;
    protected WebDriver driver;

    public OrderTest(String name, String lastName, String city, String metro, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.metro = metro;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Object[][] getQue() {
        //тест. данные
        return new Object[][]{
                {"Оби Ван", "Кеноби","Москва","Кропоткинская","+71234567890"},
                {"Оби ТУ", "Кеноби","Москва","Спортивная","+70987654321"}
        };
    }

    @Test
    public void checkOrderTopButton() {
        //драйвер firefox
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //гл. страница, форма заказа
        HomePage HomePage = new HomePage(driver);

        HomePage.pressTopButtonOrder();

        OrderPage OrderPage = new OrderPage(driver);
        //принять куки
        OrderPage.clickCookie();
        //заполнение
        OrderPage.inputName(name);
        OrderPage.inputLastName(lastName);
        OrderPage.inputAdress(city);
        OrderPage.inputMetro(metro);
        OrderPage.inputPhone(phone);
        OrderPage.clickNext();
        OrderPage.waitSecondPageOrder();
        OrderPage.inputDate("05.11.2023");
        OrderPage.inputRenatalPeriod("трое суток");
        OrderPage.clickChoiceColor();
        OrderPage.inputComment("не кантовать");
        OrderPage.clickOrderButton();
        OrderPage.waitConfirmOrderForm();

        OrderPage.clickNoButton();

        driver.quit();

        //оформление заказа

    }

    @Test
    public void checkOrderBottomButton() {
        //драйвер
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        //гл. страница, форма заказа
        HomePage HomePage = new HomePage(driver);
        OrderPage OrderPage = new OrderPage(driver);
        //принять куки
        OrderPage.clickCookie();
        HomePage.pressBottomButtonOrder();

        //заполнение
        OrderPage.inputName(name);
        OrderPage.inputLastName(lastName);
        OrderPage.inputAdress(city);
        OrderPage.inputMetro(metro);
        OrderPage.inputPhone(phone);
        OrderPage.clickNext();
        OrderPage.waitSecondPageOrder();
        OrderPage.inputDate("05.11.2023");
        OrderPage.inputRenatalPeriod("трое суток");
        OrderPage.clickChoiceColor();
        OrderPage.inputComment("не кантовать");
        OrderPage.clickOrderButton();
        OrderPage.waitConfirmOrderForm();

        OrderPage.clickYesButton();
        OrderPage.waitOrderComleteForm();
        OrderPage.clickSeeStatusButton();
        OrderPage.waitStatusOrderPage();

        driver.quit();
    }
}

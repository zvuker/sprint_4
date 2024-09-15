import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.samokat.HomePage;
import ru.yandex.praktikum.samokat.OrderPage;

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

    @Before
    public void setUp() {
        //драйвер firefox
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Принятие куки
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickCookie();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkOrderTopButton() {
        //гл. страница, форма заказа
        HomePage homePage = new HomePage(driver);

        homePage.pressTopButtonOrder();

        OrderPage orderPage = new OrderPage(driver);

        //заполнение
        orderPage.inputName(name);
        orderPage.inputLastName(lastName);
        orderPage.inputAdress(city);
        orderPage.inputMetro(metro);
        orderPage.inputPhone(phone);
        orderPage.clickNext();
        orderPage.waitSecondPageOrder();
        orderPage.inputDate("11.11.2023");
        orderPage.inputRenatalPeriod("трое суток");
        orderPage.clickChoiceColor();
        orderPage.inputComment("не кантовать");
        orderPage.clickOrderButton();
        orderPage.waitConfirmOrderForm();
        orderPage.clickNoButton();
    }

    @Test
    public void checkOrderBottomButton() {
        //гл. страница, форма заказа
        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);
        homePage.pressBottomButtonOrder();

        //заполнение
        orderPage.inputName(name);
        orderPage.inputLastName(lastName);
        orderPage.inputAdress(city);
        orderPage.inputMetro(metro);
        orderPage.inputPhone(phone);
        orderPage.clickNext();
        orderPage.waitSecondPageOrder();
        orderPage.inputDate("11.11.2023");
        orderPage.inputRenatalPeriod("трое суток");
        orderPage.clickChoiceColor();
        orderPage.inputComment("не кантовать");
        orderPage.clickOrderButton();
        orderPage.waitConfirmOrderForm();
        orderPage.clickYesButton();
        orderPage.waitOrderComleteForm();
        orderPage.clickSeeStatusButton();
        orderPage.waitStatusOrderPage();
    }
}
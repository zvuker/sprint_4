import ru.yandex.praktikum.samokat.QuestionsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class QuestionsTest {

    private final String text;
    private final String link;

    public QuestionsTest(String text, String link) {
        this.text = text;
        this.link = link;
    }

    @Parameterized.Parameters
    public static Object[][] getQue() {
        //тест. данные
        return new Object[][]{
                {"Сутки — 400 рублей", "0"},
                {"Пока что у нас так: один заказ", "1"},
                {"Допустим, вы оформляете заказ", "2"},
                {"Только начиная с завтрашнего дня", "3"},
                {"Пока что нет!", "4"},
                {"Самокат приезжает к вам с полной зарядкой", "5"},
                {"Да, пока самокат не привезли", "6"},
                {"Да, обязательно.", "7"}
        };
    }

    @Test
    public void checkQuestions  () {
        // драйвер хром
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        QuestionsPage QuestionsPage = new QuestionsPage(driver);
        //проверить вопросы о важном
        QuestionsPage.pressQue(link);
        //проверить текст ответа на вопрос
        assertTrue(QuestionsPage.getTextQue(text).contains(text));
        //закрыть браузер
        driver.quit();
    }
}


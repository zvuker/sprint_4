package ru.yandex.praktikum.samokat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionsPage {

    static WebDriver driver;

    //вопросы о важном
    private  String pathQuest;
    private  String queText;

    //самокаты

    public QuestionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void pressQue(String pathQuest) {

        WebElement element = driver.findElement(By.xpath(".//div[@id = 'accordion__heading-"+ pathQuest+"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public static String getTextQue(String queText) {

        WebElement element = driver.findElement(By.xpath(".//div/p[contains(text(),'"+queText+"')]"));
        element.isDisplayed();
        String text = element.getText();
        return text;
    }
}

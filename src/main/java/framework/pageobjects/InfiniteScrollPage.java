package framework.pageobjects;

import framework.element.Button;
import framework.element.Script;
import framework.element.Scroll;
import framework.utils.Logger;
import org.openqa.selenium.By;

public class InfiniteScrollPage extends BaseForm {

    private  Button BUTTON_INFINITE_SCROLL = new Button(By.xpath("//a[contains(text(),'Infinite Scroll')]"), "scroll btn");
    private  Scroll NUMBER_OF_PARAGRAPHS = new Scroll(By.xpath("*//div[@class='jscroll-added']"), "number present paragraph");

    public InfiniteScrollPage(By locator, String nameOfPage) {
        super(locator, nameOfPage);
    }

    public void clickScroll() {
        BUTTON_INFINITE_SCROLL.click();
    }

    public void infiniteScrollPageIsOpen() {
        Logger.writeLog("Infinite Scroll page is open");
        PageIsPresent(By.xpath("*//div[@class='example']"), "Infinite Scroll Page");
    }

    public void scrollToParagraph(int index) {
        Script script = new Script();
        while (getNumberPresentParagraphs() < index) {
            script.ScrollScript();
        }
    }

    public int getNumberPresentParagraphs() {
        return NUMBER_OF_PARAGRAPHS.getSize();
    }

    public InfiniteScrollPage() {
    }
}


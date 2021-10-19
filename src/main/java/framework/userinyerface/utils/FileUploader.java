package framework.userinyerface.utils;

import aquality.selenium.core.logging.Logger;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FileUploader {

    public static void uploadImage() {

        try {

            Robot robot = new Robot();
            robot.mouseMove(750,550);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_S);
            robot.setAutoDelay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.setAutoDelay(200);
            robot.keyPress(KeyEvent.VK_S);
            robot.setAutoDelay(200);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_S);
            robot.setAutoDelay(200);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            Logger.getInstance().info("The file could not be uploaded to the page !");
        }
    }
}

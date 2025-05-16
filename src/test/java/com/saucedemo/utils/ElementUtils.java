package com.saucedemo.utils;

import org.openqa.selenium.*;

public class ElementUtils{

    public static String getText(WebElement element) {
        if (element == null) return "";
        return element.getText().trim();
    }


    public static String getValue(WebElement element) {
        if (element == null) return "";
        return element.getAttribute("value").trim();
    }

    public static String getValue(Cookie cookie) {
        if (cookie == null) return "";
        return cookie.getValue().trim();
    }

}

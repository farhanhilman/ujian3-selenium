package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSaucedemo {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\My Tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Windows Maximize");
        driver.get("https://www.saucedemo.com/");
        System.out.println("Open Browser");

        //Login
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Login");

        System.out.println("Test URL/Link");
        String url = driver.getCurrentUrl();
        assertEquals(url, "https://www.saucedemo.com/inventory.html");

        System.out.println("Test Tulisan Swag Labs");
        String actual = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        assertEquals(actual, "Swag Labs");

        //Add 1 produk ke keranjang
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        System.out.println("Menambahkan produk ke keranjang");

        System.out.println("Test tulisan berubah dari Add to cart ke Remove");
        String actual1 =  driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).getText();
        assertEquals(actual1, "Remove");

        System.out.println("Test angka di logo keranjang bertambah");
        String actual4 =  driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        assertEquals(actual4, "1");

        delay(3);

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        System.out.println("Test cek produk di keranjang");
        String actual2 = driver.findElement(By.xpath("//div[@class='cart_quantity']")).getText();
        assertEquals(actual2, "1");

        delay(3);

        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();

        delay(3);

        //Batal add produk ke keranjang
        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();
        System.out.println("Batal add produk ke keranjang");

        System.out.println("Test tulisan berubah dari Remove ke Add to cart");
        String actual3 =  driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).getText();
        assertEquals(actual3, "Add to cart");

        delay(3);

        //Logout
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        delay(3);
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        System.out.println("Logout");

        String url1 = driver.getCurrentUrl();
        assertEquals(url1, "https://www.saucedemo.com/");

        //Scenario Negative Test Case Login
        System.out.println("Scenario Negative Test Login");

        //Input username atau password yang salah
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Test Input Username/Password Salah");

        String error = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertContains(error, "Username and password do not match");

        delay(3);

        //tidak input password
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Test Tidak menginput password");

        String error1 = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertContains(error1, "Password is required");

        delay(3);

        //tidak input username
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("Test Tidak menginput username");

        String error2 = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        assertContains(error2, "Username is required");

        delay(5);

        driver.quit();

    }

    static void assertEquals(String actual, String expected){
        if(actual.equals(expected)){
            System.out.println("---passed---");
        }
        else {
            System.out.println("---failed---");
        }
    }

    static void assertContains(String actual, String expected){
        if(actual.contains(expected)){
            System.out.println("---passed---");
        }
        else {
            System.out.println("---failed---");
        }
    }

    static void delay(long detik){
        try {
            Thread.sleep(1000*detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

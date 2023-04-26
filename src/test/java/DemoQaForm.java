import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "FireFox";
        //Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void demoQaForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Elizaveta");
        $("#lastName").setValue("Kuznetsova");
        $("#userEmail").setValue("elizavetak@demo.com");

        $(byText("Female")).click();
        $("#userNumber").setValue("9999999999");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(byText("1997")).click();
        $(".react-datepicker__month-select").click();
        $(byText("March")).click();
        $(byText("29")).click();

        $("#subjectsInput").setValue("History").pressEnter();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();

        $(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("img/1.jpg");

        $("#currentAddress").setValue("Tbilisi");

        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();

        $(".modal-content").should(appear);
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(text("Elizaveta Kuznetsova"),
                text("elizavetak@demo.com"),
                text("Female"),
                text("9999999999"),
                text("29 March,1997"),
                text("History, Arts, Computer Science"),
                text("Reading"),
                text("1.jpg"),
                text("Tbilisi"),
                text("NCR Delhi"));
    }
}

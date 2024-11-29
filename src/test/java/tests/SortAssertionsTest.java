package tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SortAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void haveJUnitExampleCodeTest() {
        open("/selenide/selenide");

        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();

        $$("#wiki-pages-box ul > li summary a").shouldHave(itemWithText("SoftAssertions"));
        $(".wiki-more-pages-link").$("button").click();
        $("#wiki-pages-box").$("a[href=\"/selenide/selenide/wiki/SoftAssertions\"]").click();

        $("#user-content-3-using-junit5-extend-test-class").preceding(0).shouldHave(text("JUnit5"));
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}

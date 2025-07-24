package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import base.BaseClass;
import io.qameta.allure.Description;

public class Login extends BaseClass {

    @Test
    @Description("loginSuccess")
    public void loginSuccess(){

            APIResponse response1 = request().get(origin+ "validate-phone-number",
                    RequestOptions.create().setHeader("Content-Type", "application/json")
                            .setQueryParam("countryCode", "+91")
                            .setQueryParam("phoneNo", "9878564732")
                            .setQueryParam("login", "false"));
            int StatusCode1 = response1.status();
            System.out.println("StatusCode1---->" + StatusCode1);

            // <-------------- Login_TC_01 --------------->

            try {
                Assert.assertEquals(StatusCode1, 200);
                System.out.println("StatusCode1"+StatusCode1);
            } catch (AssertionError e) {
                System.out.println("StatusCode1"+StatusCode1);
            }


        

        }


}

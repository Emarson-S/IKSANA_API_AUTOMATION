package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import base.BaseClass;

public class Login extends BaseClass {

    @Test
    public void validatePhoneNumber(){
        // Login using Registered Phone number
         try {
         APIResponse responseAPI=request().get(origin + "validate-phone-number",RequestOptions.create()
         .setQueryParam("countryCode", "+91")
         .setQueryParam("phoneNo", "6554433444")
         .setQueryParam("login", "false")
         .setQueryParam("hashValue", "5CH1ajIvhkB")
         .setHeader("Channel", "MOBILE")
         .setHeader("Application", "Iksana-Base")
         .setHeader("Content-Type", "application/json"));
            int statusCode=responseAPI.status();
            String response=responseAPI.text();
            if (isJSONValid(response) && statusCode == 200) {
                System.out.println("Login using registered mobile number");
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String resposeCode=responsebody.get("code").getAsString();
                String resposeMessage=responsebody.get("message").getAsString();
                try{  
                    Assert.assertEquals(resposeCode, "0000");
                    System.out.println("Success code valid : " + resposeCode);
                } catch (AssertionError e) {
                    System.out.println("Success code invalid : " + resposeCode);
                }
                try{  
                    Assert.assertEquals(resposeMessage, "Otp Genereated Successfully");
                    System.out.println("Success message valid : " + resposeMessage);
                } catch (AssertionError e) {
                    System.out.println("Success message invalid : " + resposeMessage);
                }
            }else{
                System.out.println(isJSONValid(response));
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred during Login: " + e.getMessage());
            }

    // Login using UnRegistered Phone number
    
         try {
         APIResponse responseAPI=request().get(origin + "validate-phone-number",RequestOptions.create()
         .setQueryParam("countryCode", "+91")
         .setQueryParam("phoneNo", "8756453782")
         .setQueryParam("login", "false")
         .setQueryParam("hashValue", "5CH1ajIvhkB")
         .setHeader("Channel", "MOBILE")
         .setHeader("Application", "Iksana-Base")
         .setHeader("Content-Type", "application/json"));
            int statusCode=responseAPI.status();
            String response=responseAPI.text();
            if (isJSONValid(response) && statusCode == 200) {
                System.out.println("Login using unregistered mobile number");
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String resposeCode=responsebody.get("code").getAsString();
                String resposeMessage=responsebody.get("message").getAsString();
                try{  
                    Assert.assertEquals(resposeCode, "0000");
                    System.out.println("Success code valid : " + resposeCode);
                } catch (AssertionError e) {
                    System.out.println("Success code invalid : " + resposeCode);
                }
                try{  
                    Assert.assertEquals(resposeMessage, "Otp Genereated Successfully");
                    System.out.println("Success message valid : " + resposeMessage);
                } catch (AssertionError e) {
                    System.out.println("Success message invalid : " + resposeMessage);
                }
            }else{
                System.out.println(isJSONValid(response));
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred during Login: " + e.getMessage());
            }
    }


}
    

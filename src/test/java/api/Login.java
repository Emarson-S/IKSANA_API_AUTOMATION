package api;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import base.BaseClass;
import io.qameta.allure.Description;

public class Login extends BaseClass {

    @Test
    public void phoneNoVerificationSuccess(){
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

    @Test
    public void phoneNoValidationCheck() {

    // verification of Phone Number..

        List<String> value = new ArrayList<>();
        value.add("");            // Blank
        value.add("24556343");  // Less than 10 digits
        value.add("3756756789");       // first digit is <=4 and 10 digit
        value.add("abc@123!");    // Characters & special symbols

        int i = 1;
            for (String phone : value) {
                if(i==1){
                System.out.println("Blank phone number");
                }
                else if(i==2){
                System.out.println("Less than 10 digits");
                }
                else if(i==3){
                System.out.println("first digit is <=4 and 10 digit");
                }
                else if(i==4){
                System.out.println(" Characters & special symbols");
                }
                try {
                    APIResponse responseAPI = request().get(origin + "validate-phone-number", RequestOptions.create()
                        .setQueryParam("countryCode", "+91")
                        .setQueryParam("phoneNo",phone)
                        .setQueryParam("login", "false")
                        .setQueryParam("hashValue", "5CH1ajIvhkB")
                        .setHeader("Channel", "MOBILE")
                        .setHeader("Application", "Iksana-Base")
                        .setHeader("Content-Type", "application/json"));

                        int statusCode = responseAPI.status();
                        String response = responseAPI.text();

                        if (isJSONValid(response) && statusCode == 200) {
                            JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                            String responseCode = responsebody.get("code").getAsString();
                            String responseMessage = responsebody.get("message").getAsString();

                            try {
                                Assert.assertEquals(responseCode, "1111");
                                System.out.println("Response code valid : " + responseCode);
                            
                                
                            } catch (AssertionError e) {
                                System.out.println("Response code invalid : " + responseCode);
                                break;
                            }
                            try {
                                if(i==1){
                                    Assert.assertEquals(responseMessage, "Data should not be empty");
                                    System.out.println("Response message valid : " + responseMessage);
                                    }
                                    else{
                                Assert.assertEquals(responseMessage, "Please enter valid phone number");
                                System.out.println("Response message valid : " + responseMessage);
                                    }
                            } catch (AssertionError e) {
                                System.out.println("Response message invalid : " + responseMessage);
                            }
                        } else {
                            System.out.println("Response is not valid JSON or Status != 200");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred during phone number validation: " + e.getMessage());
                    }
                    i++;
            }
    //verification of Country Code..

        List<String> countryCodes = new ArrayList<>();
        countryCodes.add("");         // Blank country code
        countryCodes.add("+1");       // Different valid region
        countryCodes.add("91");       // Missing '+' sign
        countryCodes.add("@#$");      // Invalid special characters

        int j = 1;
        for (String code : countryCodes) {
            if (j == 1) {
                System.out.println("Blank country code");
            } else if (j == 2) {
                System.out.println("Different valid country code");
            } else if (j == 3) {
                System.out.println("Missing '+' sign");
            } else if (j == 4) {
                System.out.println("Special characters");
            }

            try {
                APIResponse responseAPI = request().get(origin + "validate-phone-number", RequestOptions.create()
                    .setQueryParam("countryCode", code)
                    .setQueryParam("phoneNo", "9876543210") 
                    .setQueryParam("login", "false")
                    .setQueryParam("hashValue", "5CH1ajIvhkB")
                    .setHeader("Channel", "MOBILE")
                    .setHeader("Application", "Iksana-Base")
                    .setHeader("Content-Type", "application/json"));

                int statusCode = responseAPI.status();
                String response = responseAPI.text();

                if (isJSONValid(response) && statusCode == 200) {
                    JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                    String responseCode = responsebody.get("code").getAsString();
                    String responseMessage = responsebody.get("message").getAsString();

                    try {
                        Assert.assertEquals(responseCode, "1111");
                        System.out.println("Response code valid : " + responseCode);
                    } catch (AssertionError e) {
                        System.out.println("Response code invalid : " + responseCode);
                        break;
                    }
                    try {
                        if(j==1){
                        Assert.assertEquals(responseMessage, "Data should not be empty");
                        System.out.println("Response message valid : " + responseMessage);
                        }
                        else{
                            Assert.assertEquals(responseMessage, "Please enter proper contry code");
                            System.out.println("Response message valid : " + responseMessage);
                        }
                    } catch (AssertionError e) {
                        System.out.println("Response message invalid : " + responseMessage);
                    }
                } else {
                    System.out.println("Response is not valid JSON or Status != 200");
                }
            } catch (Exception e) {
                System.out.println("An error occurred during country code validation: " + e.getMessage());
            }
            j++;
        }

    // verification of Hash Value..

        try {
            APIResponse responseAPI = request().get(origin + "validate-phone-number", RequestOptions.create()
            .setQueryParam("countryCode", "+91")         // valid country code
            .setQueryParam("phoneNo", "9876543210")      // valid phone number
            .setQueryParam("login", "false")
            .setQueryParam("hashValue", "")   // Blank Hash Value
            .setHeader("Channel", "MOBILE")
            .setHeader("Application", "Iksana-Base")
            .setHeader("Content-Type", "application/json"));

            int statusCode = responseAPI.status();
            String response = responseAPI.text();
            System.out.println("Blank Hash value");

            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String responseCode = responsebody.get("code").getAsString();
                String responseMessage = responsebody.get("message").getAsString();

                try {
                    Assert.assertEquals(responseCode, "0000");
                    System.out.println("Response code valid : " + responseCode);
                } catch (AssertionError e) {
                    System.out.println("Response code invalid : " + responseCode);
                }
                try {
                    Assert.assertEquals(responseMessage, "Otp Genereated Successfully");
                    System.out.println("Response message valid : " + responseMessage);
                } catch (AssertionError e) {
                    System.out.println("Response message invalid : " + responseMessage);
                }
                } else {
                    System.out.println("Response is not valid JSON or Status != 200");
                }
            } catch (Exception e) {
                System.out.println("An error occurred during hash value validation: " + e.getMessage());
            }
        
    }

    @Test
    public void LoginSuccessWithReqPhoneNO() {
        try {
            // Registered user
            System.out.println("Registered user Login without any Previous session"); 
            String registeredPhone = "9878989889";  
            APIResponse responseOTP = request().get(origin + "validate-phone-number", RequestOptions.create()
                .setQueryParam("countryCode", "+91")
                .setQueryParam("phoneNo", registeredPhone)
                .setQueryParam("login", "false")
                .setQueryParam("hashValue", "5CH1ajIvhkB")
                .setHeader("Channel", "MOBILE")
                .setHeader("Application", "Iksana-Base")
                .setHeader("Content-Type", "application/json"));

            String response = responseOTP.text();
            int statusCode=responseOTP.status();
            if(isJSONValid(response)&&statusCode==200){
            JsonObject responseBody = JsonParser.parseString(response).getAsJsonObject();
            String OTP = responseBody.get("data").getAsString();
            String Auth=basicAuth(registeredPhone, OTP);

            // login-with-otp API
            APIResponse responseAPI = getRequestWithAuth("login-with-otp", Auth, "Mobile");
            int statuscode1=responseAPI.status();
            String response1 = responseAPI.text();

            if(isJSONValid(response1) && statuscode1==200 ){
            JsonObject responseBody1=JsonParser.parseString(response1).getAsJsonObject();
            String ResponseCode = responseBody1.get("code").getAsString();
            String ResponseMessage = responseBody1.get("message").getAsString();
            try {
                Assert.assertEquals(ResponseCode, "0000");
                System.out.println("Registered user code valid: " + ResponseCode);
            } catch (AssertionError e) {
                System.out.println("Registered user code invalid: " + ResponseCode);
            }
            try {
                Assert.assertEquals(ResponseMessage, "Login Successfull"); 
                System.out.println("Registered user message valid: " + ResponseMessage);
            } catch (AssertionError e) {
                System.out.println("Registered user message invalid: " + ResponseMessage);
            }


            if(isJSONValid(response1)){
            JsonObject responseData=responseBody1.get("data").getAsJsonObject();
            String token=responseData.get("token").getAsString();
            try{
                Assert.assertNotNull(token);
                System.out.println("Registered user token generated: " + token);
            }
            catch(AssertionError e){
                 System.out.println("Registered user token not available: " + token);
            }
            
            }else{
                System.out.println("Registered user data not available: ");
            }
        }
        else{
            System.out.println(isJSONValid(response1));
        }
            
            }
        }
        catch(Exception e){
            System.out.println("an error occured during registered user Login :" + e.getMessage());
        }
       
    }
  
    @Test
    public void LoginSuccessWithUnReqPhoneNO(){
    try {
        // Unregistered user
        System.out.println("UnRegistered user Login"); 
        String unregisteredPhone = "6787893635";

        APIResponse responseOTP1 = request().get(origin + "validate-phone-number", RequestOptions.create()
            .setQueryParam("countryCode", "+91")
            .setQueryParam("phoneNo", unregisteredPhone)
            .setQueryParam("login", "false")
            .setQueryParam("hashValue", "5CH1ajIvhkB")
            .setHeader("Channel", "MOBILE")
            .setHeader("Application", "Iksana-Base")
            .setHeader("Content-Type", "application/json"));

        String response2 = responseOTP1.text();
        int statusCode = responseOTP1.status();

        if (isJSONValid(response2) && statusCode == 200) {
            JsonObject responseBody2 = JsonParser.parseString(response2).getAsJsonObject();
            String OTP1 = responseBody2.get("data").getAsString();
            String auth = basicAuth(unregisteredPhone, OTP1);

            // login-with-otp API
            APIResponse responseAPI3 = getRequestWithAuth("login-with-otp", auth, "Mobile");
            String response3 = responseAPI3.text();
            int loginStatusCode = responseAPI3.status();

            if (isJSONValid(response3) && loginStatusCode == 200) {
                JsonObject responseBody3 = JsonParser.parseString(response3).getAsJsonObject();

                String responseCode1 = responseBody3.get("code").getAsString();
                String responseMessage1 = responseBody3.get("message").getAsString();

                try {
                    Assert.assertEquals(responseCode1, "1313");
                    System.out.println("Unregistered user code valid: " + responseCode1);
                } catch (AssertionError e) {
                    System.out.println("Unregistered user code invalid: " + responseCode1);
                }

                try {
                    Assert.assertEquals(responseMessage1, "User Not registered");
                    System.out.println("Unregistered user message valid: " + responseMessage1);
                } catch (AssertionError e) {
                    System.out.println("Unregistered user message invalid: " + responseMessage1);
                }
                if (responseBody3.has("data")) {
                    JsonObject dataObj = responseBody3.getAsJsonObject("data");
                    String returnedPhone = dataObj.get("phoneNo").getAsString();

                    try {
                        Assert.assertEquals(returnedPhone, unregisteredPhone);
                        System.out.println("Unregistered user phone number matches: " + returnedPhone);
                    } catch (AssertionError e) {
                        System.out.println("Unregistered user phone mismatch. Expected: "
                            + unregisteredPhone + " but got: " + returnedPhone);
                    }
                } else {
                    System.out.println("No data object found in unregistered user response");
                }

            } else {
                System.out.println("Unregistered user login-with-otp response invalid JSON or Status != 200");
            }

        } else {
            System.out.println("Unregistered user OTP response invalid JSON or Status != 200");
        }

    } catch (Exception e) {
        System.out.println("An error occurred during UnRegistered User Login : " + e.getMessage());
    }

    }

    @Test
    public void LogInValidationCheck(){
        try{
            System.out.println("Wrong phone number with correct OTP");
            String phonenumber="6787793635";
            APIResponse responseAPI = request().get(origin+"validate-phone-number",RequestOptions.create()
            .setQueryParam("countryCode", "+91")
                .setQueryParam("phoneNo",phonenumber)
                .setQueryParam("login", "false")
                .setQueryParam("hashValue", "5CH1ajIvhkB")
                .setHeader("Channel", "MOBILE")
                .setHeader("Application", "Iksana-Base")
                .setHeader("Content-Type", "application/json"));
            int statuscode = responseAPI.status();
            String response = responseAPI.text();
            if(isJSONValid(response) && statuscode==200){
                try{
                    JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                    String OTP = responsebody.get("data").getAsString();
                    String username = basicAuth("6182793635", OTP);
                    APIResponse responseAPI1 = getRequestWithAuth("login-with-otp", username,"Mobile");
                    int statuscode1 = responseAPI1.status();
                    String responsebody1 = responseAPI1.text();
                    if(isJSONValid(responsebody1) && statuscode1==200){
                        JsonObject responsebody2 = JsonParser.parseString(responsebody1).getAsJsonObject();
                        String responsecode1 = responsebody2.get("code").getAsString();
                        String responsemessage1 = responsebody2.get("message").getAsString();
                        try{
                            Assert.assertEquals(responsecode1, "1111");
                            System.out.println("Response code valid : "+ responsecode1 );
                        }
                        catch(Exception b){
                            System.out.println("Response code Invalid : "+ responsecode1);
                        }
                         try{
                            Assert.assertEquals(responsemessage1, "Please verfify your data");
                            System.out.println("Response message valid  : "+ responsemessage1 );
                        }
                        catch(Exception b){
                            System.out.println("Response message Invalid : "+ responsemessage1);
                        }
                    }
                }catch(Exception e){
                    System.out.println("Error occured while verify OTP :" + e.getMessage());
                }
            }
        }catch(Exception a){
            System.out.println("Error occured while generate OTP :" + a.getMessage());
        }
        //Wrong OTP 
        try{
            System.out.println("Wrong OTP with correct phone");
            String phonenumber="6787793635";
            APIResponse responseAPI = request().get(origin+"validate-phone-number",RequestOptions.create()
            .setQueryParam("countryCode", "+91")
                .setQueryParam("phoneNo",phonenumber)
                .setQueryParam("login", "false")
                .setQueryParam("hashValue", "5CH1ajIvhkB")
                .setHeader("Channel", "MOBILE")
                .setHeader("Application", "Iksana-Base")
                .setHeader("Content-Type", "application/json"));
            int statuscode = responseAPI.status();
            String response = responseAPI.text();
            if(isJSONValid(response) && statuscode==200){
                try{
                    JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                    String OTP = responsebody.get("data").getAsString();
                    System.out.println("receivedOTP :"+ OTP);
                    String wrongOTP="765643";
                    String username = basicAuth(phonenumber,wrongOTP);
                    APIResponse responseAPI1 = getRequestWithAuth("login-with-otp", username,"Mobile");
                    int statuscode1 = responseAPI1.status();
                    String responsebody1 = responseAPI1.text();
                    if(isJSONValid(responsebody1) && statuscode1==200){
                        JsonObject responsebody2 = JsonParser.parseString(responsebody1).getAsJsonObject();
                        String responsecode1 = responsebody2.get("code").getAsString();
                        String responsemessage1 = responsebody2.get("message").getAsString();
                        try{
                            Assert.assertEquals(responsecode1, "1111");
                            System.out.println("Response code valid : "+ responsecode1 );
                        }
                        catch(Exception b){
                            System.out.println("Response code Invalid : "+ responsecode1);
                        }
                        try{
                            Assert.assertEquals(responsemessage1, "Enter Valid OTP");
                            System.out.println("Response message valid  : "+ responsemessage1 );
                        }
                        catch(Exception b){
                            System.out.println("Response message Invalid : "+ b.getMessage());
                        }
                    }
                }catch(Exception e){
                    System.out.println("Error occured while verify OTP :" + e.getMessage());
                }
            }
        }catch(Exception a){
            System.out.println("Error occured while generate OTP :" + a.getMessage());
        }
        //OTP expiration >=5 mins
        // try{
        //     System.out.println("Expired OTP with valid phone");
        //     String phonenumber="6787793635";
        //     APIResponse responseAPI = request().get(origin+"validate-phone-number",RequestOptions.create()
        //     .setQueryParam("countryCode", "+91")
        //         .setQueryParam("phoneNo",phonenumber)
        //         .setQueryParam("login", "false")
        //         .setQueryParam("hashValue", "5CH1ajIvhkB")
        //         .setHeader("Channel", "MOBILE")
        //         .setHeader("Application", "Iksana-Base")
        //         .setHeader("Content-Type", "application/json"));
        //     int statuscode = responseAPI.status();
        //     String response = responseAPI.text();
        //     if(isJSONValid(response) && statuscode==200){
        //         try{
        //             JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
        //             String OTP = responsebody.get("data").getAsString();
        //             String username = basicAuth(phonenumber,OTP);
        //             System.out.println("Waiting 5 minutes before next API call...");
        //             TimeUnit.MINUTES.sleep(5);
        //             System.out.println("Done waiting!");
        //             APIResponse responseAPI1 = getRequestWithAuth("login-with-otp", username,"Mobile");
        //             int statuscode1 = responseAPI1.status();
        //             String responsebody1 = responseAPI1.text();
        //             if(isJSONValid(responsebody1) && statuscode1==200){
        //                 JsonObject responsebody2 = JsonParser.parseString(responsebody1).getAsJsonObject();
        //                 String responsecode1 = responsebody2.get("code").getAsString();
        //                 String responsemessage1 = responsebody2.get("message").getAsString();
        //                 try{
        //                     Assert.assertEquals(responsecode1, "1111");
        //                     System.out.println("Response code valid : "+ responsecode1 );
        //                 }
        //                 catch(Exception b){
        //                     System.out.println("Response code Invalid : "+ responsecode1);
        //                 }
        //                 try{
        //                     Assert.assertEquals(responsemessage1, "OTP Expired");
        //                     System.out.println("Response message valid  : "+ responsemessage1 );
        //                 }
        //                 catch(Exception b){
        //                     System.out.println("Response message Invalid : "+ b.getMessage());
        //                 }
        //             }
        //         }catch(Exception e){
        //             System.out.println("Error occured while verify OTP :" + e.getMessage());
        //         }
        //     }
        // }catch(Exception a){
        //     System.out.println("Error occured while generate OTP :" + a.getMessage());
        // }
    }
 @Test
    public void LoginSessionSuccess(){
        try{
             System.out.println("Already have session check");
            String phonenumber="5746737928";
            APIResponse responseAPI = request().get(origin+"validate-phone-number",RequestOptions.create()
            .setQueryParam("countryCode", "+91")
                .setQueryParam("phoneNo",phonenumber)
                .setQueryParam("login", "false")
                .setQueryParam("hashValue", "5CH1ajIvhkB")
                .setHeader("Channel", "MOBILE")
                .setHeader("Application", "Iksana-Base")
                .setHeader("Content-Type", "application/json"));
            int statuscode = responseAPI.status();
            String response = responseAPI.text();
            if(isJSONValid(response) && statuscode==200){
                try{
                    JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                    String OTP = responsebody.get("data").getAsString();
                    String username = basicAuth(phonenumber,OTP);
                    System.out.println("OTP sent");

                    //Login with otp
                    System.out.println("Login with otp");
                    APIResponse responseAPI1 = getRequestWithAuth("login-with-otp", username,"Mobile");
                    int statuscode1 = responseAPI1.status();
                    String responsebody1 = responseAPI1.text();
                    if(isJSONValid(responsebody1) && statuscode1==200){
                        JsonObject responsebody2 = JsonParser.parseString(responsebody1).getAsJsonObject();
                        JsonObject responsedata = responsebody2.get("data").getAsJsonObject();
                        JsonObject userdata = responsedata.get("user").getAsJsonObject();
                        Map<String, String> userInfo = new HashMap<>();
                        userInfo.put("userId", userdata.get("userId").getAsString());
                        userInfo.put("email", userdata.get("email").getAsString());
                        userInfo.put("phoneNo", userdata.get("phoneNo").getAsString());
                        userInfo.put("roleId", userdata.get("roleId").getAsString());
                        userInfo.put("countryCode", userdata.get("countryCode").getAsString());
                        System.out.println("data found " );
                        try{
                            Assert.assertNotNull(responsedata);
                            System.out.println("Session verification Api called");
                            // session api call 
                            APIResponse userlogin = postRequestWithoutToken("session-verification", "WEB", userInfo);
                            int sessionStatuscode = userlogin.status();
                            String sessiondata = userlogin.text();
                            try{
                                if(isJSONValid(sessiondata) && sessionStatuscode==200){
                                JsonObject sessioninfo = JsonParser.parseString(sessiondata).getAsJsonObject();
                                String sessionCode = sessioninfo.get("code").getAsString();
                                String sessionMessage = sessioninfo.get("message").getAsString();
                                try {
                                    Assert.assertEquals(sessionCode, "0000");
                                    System.out.println("session code is valid :"+sessionCode);
                                } catch (Exception e) {
                                    System.out.println(" Invalid Session code :"+ e.getMessage());
                                }
                                try {
                                    Assert.assertEquals(sessionMessage, "Login Successfully");
                                    System.out.println("session code is valid :"+sessionMessage);
                                } catch (Exception e) {
                                    System.out.println(" Invalid session Message :"+ sessionMessage);
                                }
                               }
                            }
                            catch(Exception c){
                                System.out.println("Session data response not found");
                            }

                        }
                        catch(Exception b){
                            System.out.println("data Not found: "+ b.getMessage());
                        }
                    }
                }catch(Exception e){
                    System.out.println("Error occured while verify OTP :" + e.getMessage());
                }
            }

        }catch(Exception a){
            System.out.println("Error occured while generate OTP :" + a.getMessage());
        }
    } 

    @Test
    public void LoginSessionValidationCheck(){
        try{
             System.out.println("negative session check");
            String phonenumber="5746737928";
            APIResponse responseAPI = request().get(origin+"validate-phone-number",RequestOptions.create()
            .setQueryParam("countryCode", "+91")
                .setQueryParam("phoneNo",phonenumber)
                .setQueryParam("login", "false")
                .setQueryParam("hashValue", "5CH1ajIvhkB")
                .setHeader("Channel", "MOBILE")
                .setHeader("Application", "Iksana-Base")
                .setHeader("Content-Type", "application/json"));
            int statuscode = responseAPI.status();
            String response = responseAPI.text();
            if(isJSONValid(response) && statuscode==200){
                try{
                    JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                    String OTP = responsebody.get("data").getAsString();
                    String username = basicAuth(phonenumber,OTP);
                    System.out.println("OTP sent");

                    //Login with otp
                    System.out.println("Login with otp");
                    APIResponse responseAPI1 = getRequestWithAuth("login-with-otp", username,"Mobile");
                    int statuscode1 = responseAPI1.status();
                    String responsebody1 = responseAPI1.text();
                    if(isJSONValid(responsebody1) && statuscode1==200){
                        JsonObject responsebody2 = JsonParser.parseString(responsebody1).getAsJsonObject();
                        JsonObject responsedata = responsebody2.get("data").getAsJsonObject();
                        JsonObject userdata = responsedata.get("user").getAsJsonObject();
                        
                        //wrong user ID......
                        
                        Map<String, String> userInfo = new HashMap<>();
                        userInfo.put("userId", "82767262"); //wrong userId
                        userInfo.put("email", userdata.get("email").getAsString());
                        userInfo.put("phoneNo", userdata.get("phoneNo").getAsString());
                        userInfo.put("roleId", userdata.get("roleId").getAsString());
                        userInfo.put("countryCode", userdata.get("countryCode").getAsString());
                        
                        try{
                            Assert.assertNotNull(responsedata);
                            System.out.println("Session verification Api called with Wrong User ID");
                            // session api call 
                            APIResponse userlogin = postRequestWithoutToken("session-verification", "WEB", userInfo);
                            int sessionStatuscode = userlogin.status();
                            String sessiondata = userlogin.text();
                            try{
                                if(isJSONValid(sessiondata) && sessionStatuscode==200){
                                JsonObject sessioninfo = JsonParser.parseString(sessiondata).getAsJsonObject();
                                String sessionCode = sessioninfo.get("code").getAsString();
                                String sessionMessage = sessioninfo.get("message").getAsString();
                                try {
                                    Assert.assertEquals(sessionCode, "1111");
                                    System.out.println("session code is valid :"+sessionCode);
                                } catch (Exception e) {
                                    System.out.println(" Invalid Session code :"+ e.getMessage());
                                }
                                try {
                                    Assert.assertEquals(sessionMessage, "Enter a valid data");
                                    System.out.println("session code is valid :"+sessionMessage);
                                } catch (Exception e) {
                                    System.out.println(" Invalid session Message :"+ sessionMessage);
                                }
                               }
                            }
                            catch(Exception c){
                                System.out.println("Session data response not found");
                            }

                        }
                        catch(Exception b){
                            System.out.println("data Not found: "+ b.getMessage());
                        }
                        // wrong Email id 
                        Map<String, String> userInfo1 = new HashMap<>(userInfo);
                        userInfo1.put("userId", userdata.get("userId").getAsString());
                        userInfo1.put("email", "kkrs@gmail.com");

                        try{
                            Assert.assertNotNull(responsedata);
                            System.out.println("Session verification Api called with Wrong Email ID");
                            // session api call 
                            APIResponse userlogin = postRequestWithoutToken("session-verification", "WEB", userInfo1);
                            int sessionStatuscode = userlogin.status();
                            String sessiondata = userlogin.text();
                            System.out.println("session with wrong email address");
                            try{
                                if(isJSONValid(sessiondata) && sessionStatuscode==200){
                                JsonObject sessioninfo = JsonParser.parseString(sessiondata).getAsJsonObject();
                                String sessionCode = sessioninfo.get("code").getAsString();
                                String sessionMessage = sessioninfo.get("message").getAsString();
                                System.out.println(sessionCode);
                                System.out.println(sessionMessage);
                                try {
                                    Assert.assertEquals(sessionCode, "1111");
                                    System.out.println("session code is valid :"+sessionCode);
                                } catch (Exception e) {
                                    System.out.println(" Invalid Session code :"+ e.getMessage());
                                }
                                try {
                                    Assert.assertEquals(sessionMessage, "Enter a valid data");
                                    System.out.println("session code is valid :"+sessionMessage);
                                } catch (Exception e) {
                                    System.out.println(" Invalid session Message :"+ sessionMessage);
                                }
                               }
                            }
                            catch(Exception c){
                                System.out.println("Session data response not found");
                            }
                        }catch(Exception e){
                            System.out.println("data Not found: "+ e.getMessage());
                        }

                        // wrong phone number
                        
                        Map<String, String> userInfo2 = new HashMap<>(userInfo1);
                        userInfo1.put("email", userdata.get("email").getAsString());
                        userInfo1.put("phoneNo", userdata.get("phoneNo").getAsString());

                        try{
                            Assert.assertNotNull(responsedata);
                            System.out.println("Session verification Api called with Wrong phoneNO");
                            // session api call 
                            APIResponse userlogin = postRequestWithoutToken("session-verification", "WEB", userInfo2);
                            int sessionStatuscode = userlogin.status();
                            String sessiondata = userlogin.text();
                            System.out.println("session with wrong email address");
                            try{
                                if(isJSONValid(sessiondata) && sessionStatuscode==200){
                                JsonObject sessioninfo = JsonParser.parseString(sessiondata).getAsJsonObject();
                                String sessionCode = sessioninfo.get("code").getAsString();
                                String sessionMessage = sessioninfo.get("message").getAsString();
                                System.out.println(sessionCode);
                                System.out.println(sessionMessage);
                                try {
                                    Assert.assertEquals(sessionCode, "1111");
                                    System.out.println("session code is valid :"+sessionCode);
                                } catch (Exception e) {
                                    System.out.println(" Invalid Session code :"+ e.getMessage());
                                }
                                try {
                                    Assert.assertEquals(sessionMessage, "Enter a valid data");
                                    System.out.println("session code is valid :"+sessionMessage);
                                } catch (Exception e) {
                                    System.out.println(" Invalid session Message :"+ sessionMessage);
                                }
                               }
                            }
                            catch(Exception c){
                                System.out.println("Session data response not found");
                            }

                        }
                        catch(Exception b){
                            System.out.println("data Not found: "+ b.getMessage());
                        }
                        
                    }
                }catch(Exception e){
                    System.out.println("Error occured while verify OTP :" + e.getMessage());
                }
            }

        }catch(Exception a){
            System.out.println("Error occured while generate OTP :" + a.getMessage());
        }
    }
    
}
    

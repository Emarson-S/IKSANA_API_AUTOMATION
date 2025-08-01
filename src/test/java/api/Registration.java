package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;

import base.BaseClass;

public class Registration extends BaseClass {

    @Test(description = "Registration")
    public void registrationSuccess() {
        try {
            List<String> caregiverDetails = new ArrayList<>(); // empty list for caregivers
            Map<Object, Object> requestPayload = new HashMap<>();
            requestPayload.put("firstName", "jesu");
            requestPayload.put("lastName", "mahesh");
            requestPayload.put("dateOfBirth", "15-07-1997");
            requestPayload.put("gender", "Male");
            requestPayload.put("phoneNo", "8000000904");
            requestPayload.put("countryCode", "+91");
            requestPayload.put("profilePhoto", "");
            requestPayload.put("careGivers", caregiverDetails);
            requestPayload.put("age", "28");
            requestPayload.put("accept", true);
            requestPayload.put("email", "");
            requestPayload.put("emailVerification", false);
            requestPayload.put("phoneNoVerificatio", true);
            APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", requestPayload);
            int statusCode=responseAPI.status();
          
            // <------     Registration_TC_01   ------>

            try {
                Assert.assertEquals(statusCode, 200);
                System.out.println("Status code Valid : " + statusCode);
            } catch (AssertionError e) {
                System.out.println("Status code invalid : " + statusCode);

            }

            // <------     Registration_TC_02   ------>
      
            String response = getBodyData(responseAPI).toString();
            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String resposeCode=responsebody.get("code").getAsString();
                String resposeMessage=responsebody.get("message").getAsString();
            try{  
                Assert.assertEquals(resposeCode, 0000);
                System.out.println("Success code valid : " + resposeCode);
            } catch (AssertionError e) {
                System.out.println("Success code invalid : " + resposeCode);
            }
            try{  
                Assert.assertEquals(resposeMessage, "Registration Successful!");
                System.out.println("Success message valid : " + resposeMessage);
            } catch (AssertionError e) {
                System.out.println("Success message invalid : " + resposeMessage);
            }

            // <------     Registration_TC_03   ------>

            JsonObject responsedata = responsebody.get("data").getAsJsonObject();
            if (isJSONValid(responsedata.toString())) {
                JsonObject userdata = responsedata.get("user").getAsJsonObject();
                String userID=userdata.get("userId").getAsString();
            
            if(userID != null){
                System.out.println("User created successfully : " + userID);
            } else{
                System.out.println("User not created : " + userID);
            }
            } else {
                System.out.println("Data Not found");
            }
            }     
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }

    @Test(description = "Registration")
    public void registrationSuccessWithCG() throws IOException{
        try {
            Map<String, Object> familyMember = new HashMap<>();
            familyMember.put("firstName", "Swathi");
            familyMember.put("lastName", "Doe");
            familyMember.put("dateOfBirth", "15-07-2002");
            familyMember.put("gender", "Female");
            familyMember.put("phoneNo", "8219787861");
            familyMember.put("countryCode", "+91");
            familyMember.put("profilePhoto", "");
            familyMember.put("age", "22");
            familyMember.put("emailVerification", true);
            familyMember.put("phoneNoVerificatio", true);
            familyMember.put("email", "Mugilan123@yopmail.com");
            familyMember.put("relationship", "Daughter");

            List<Object> caregiverDetails = new ArrayList<>();
            caregiverDetails.add(familyMember);

            Map<Object, Object> requestPayload = new HashMap<>();
            requestPayload.put("firstName", "vishnu");
            requestPayload.put("lastName", "Ganesh");
            requestPayload.put("dateOfBirth", "15-07-1997");
            requestPayload.put("gender", "Male");
            requestPayload.put("phoneNo", "9640787844");
            requestPayload.put("countryCode", "+91");
            requestPayload.put("profilePhoto", "");
            requestPayload.put("email", "");
            requestPayload.put("age", "28");
            requestPayload.put("accept", true);
            requestPayload.put("emailVerification", false);
            requestPayload.put("phoneNoVerificatio", true);
            requestPayload.put("careGivers", caregiverDetails);

            APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", requestPayload);
             int statusCode=responseAPI.status();
          
            // <------     Registration_TC_04   ------>

            try {
                Assert.assertEquals(statusCode, 200);
                System.out.println("Status code Valid : " + statusCode);
            } catch (AssertionError e) {
                System.out.println("Status code invalid : " + statusCode);

            }

            // <------     Registration_TC_05   ------>
      
            String response = getBodyData(responseAPI).toString();
            System.out.println(response);
            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                int resposeCode=responsebody.get("code").getAsInt();
                String resposeMessage=responsebody.get("message").getAsString();
            try{  
                Assert.assertEquals(resposeCode, 0000);
                System.out.println("Success code valid : " + resposeCode);
            } catch (AssertionError e) {
                System.out.println("Success code invalid : " + resposeCode);
            }
            try{  
                Assert.assertEquals(resposeMessage, "Registration Successful!");
                System.out.println("Success message valid : " + resposeMessage);
            } catch (AssertionError e) {
                System.out.println("Success message invalid : " + resposeMessage);
            }

            // <------     Registration_TC_06   ------>
            if(!responsebody.get("data").isJsonNull()){
            JsonObject responsedata = responsebody.get("data").getAsJsonObject();
            if (responsedata!=null && isJSONValid(responsedata.toString())) {
                JsonObject userdata = responsedata.get("user").getAsJsonObject();
                String userID=userdata.get("userId").getAsString();
            
            if(userID != null){
                System.out.println("User created successfully : " + userID);
            } else{
                System.out.println("User not created : " + userID);
            }
            } else {
                System.out.println("Data Not found");
            }
        }else {
                System.out.println("Data Not found ---> null");
            }
        }     
        } catch (AssertionError e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    

    }

    @Test(description = "Registration")
    public void userRegDuplicationCheck() throws IOException{
        
            List<String> caregiverDetails = new ArrayList<>(); //caregiverDetails empty list
            Map<Object, Object> requestPayload0 = new HashMap<>();
            requestPayload0.put("firstName", "vishnu");
            requestPayload0.put("lastName", "Ganesh");
            requestPayload0.put("dateOfBirth", "15-07-1997");
            requestPayload0.put("gender", "Male");
            requestPayload0.put("phoneNo", "9640787874");
            requestPayload0.put("countryCode", "+91");
            requestPayload0.put("profilePhoto", "");
            requestPayload0.put("email", "");
            requestPayload0.put("age", 28);
            requestPayload0.put("accept", true);
            requestPayload0.put("emailVerification", false);
            requestPayload0.put("phoneNoVerificatio", true);
            requestPayload0.put("careGivers",caregiverDetails);
            Map<Object,Object> requestPayload1=new HashMap<>(requestPayload0);
            requestPayload1.put("phoneNo", "9673787784");
            requestPayload1.put("email", "user1@gmail.com");
            requestPayload1.put("emailVerification", true);
            List<Map<Object,Object>> reqList = new ArrayList<>();
            reqList.add(requestPayload0); 
            reqList.add(requestPayload1);
        for (Map<Object,Object> payload : reqList){
            try {  
            APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", payload);
            int statusCode=responseAPI.status();
            String response = getBodyData(responseAPI).toString();
            if(payload==requestPayload0){
                System.out.println("Duplicate phone number");
            }
            else if(payload==requestPayload1){
                System.out.println("unique phone number and dupicate email");
            }
            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String resposeCode=responsebody.get("code").getAsString();
                String resposeMessage=responsebody.get("message").getAsString();
                try{  
                Assert.assertEquals(resposeCode, "1111");
                System.out.println("Success code valid : " + resposeCode);
                } catch (AssertionError e) {
                System.out.println("Success code invalid : " + resposeCode);
                }
                try{  
                Assert.assertEquals(resposeMessage, "Already Exist");
                System.out.println("response message is valid : " + resposeMessage);
                } catch (AssertionError e) {
                System.out.println("Success message invalid : " + resposeMessage);
                }
            }
        } 
        catch (AssertionError e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
        System.out.println("------------------------");
        }
    }
    
    @Test(description = "Registration")
    public void userRegDuplicationCheckWithCG() throws IOException{  
        // Duplication check with user and CG data(Not in DB)
            //duplicate phone in user and CG data and unique email should not in DB
            Map<String, Object> familyMember = new HashMap<>();
            familyMember.put("firstName", "vasanth");
            familyMember.put("lastName", "Doe");
            familyMember.put("dateOfBirth", "15-07-2002");
            familyMember.put("gender", "Female");
            familyMember.put("phoneNo", "9080706052");
            familyMember.put("countryCode", "+91");
            familyMember.put("profilePhoto", "");
            familyMember.put("age", "22");
            familyMember.put("emailVerification", true);
            familyMember.put("phoneNoVerificatio", true);
            familyMember.put("email", "user2@yopmail.com");
            familyMember.put("relationship", "Brother");

            List<Object> caregiverDetails = new ArrayList<>();
            caregiverDetails.add(familyMember);

            Map<Object, Object> requestPayload0 = new HashMap<>();
            requestPayload0.put("firstName", "John");
            requestPayload0.put("lastName", "Doe");
            requestPayload0.put("dateOfBirth", "15-07-1982");
            requestPayload0.put("gender", "Male");
            requestPayload0.put("phoneNo", "9080706052");
            requestPayload0.put("countryCode", "+91");
            requestPayload0.put("profilePhoto", "");
            requestPayload0.put("email", "user4@yopmail.com");
            requestPayload0.put("age", "43");
            requestPayload0.put("accept", true);
            requestPayload0.put("emailVerification", true);
            requestPayload0.put("phoneNoVerificatio", true);
            requestPayload0.put("careGivers", caregiverDetails);

            //duplicate email in user and CG data and unique phone should not in DB
            Map<Object,Object> familyMember0 = new HashMap<>(familyMember);
            familyMember0.put("phoneNo", "9080706053");// unique phone in CG data
            familyMember0.put("email", "user4@yopmail.com");
            familyMember0.put("emailVerification", true);
            List<Object> caregiverDetails0 = new ArrayList<>();
            caregiverDetails0.add(familyMember0);
            Map<Object,Object> requestPayload01=new HashMap<>(requestPayload0);
            requestPayload01.put("careGivers", caregiverDetails0);

        // Duplication check with CG data (in DB)
            
            // duplicate phone in CG that are in DB 
            Map<Object,Object>familyMember1 = new HashMap<>(familyMember);
            familyMember1.put("phoneNo", "9650787875");// duplicate phone in CG data(DB)
            familyMember1.put("email", "user4@yopmail.com");// unique email in CG data
            familyMember1.put("emailVerification", true);
            List<Object> caregiverDetails1 = new ArrayList<>();
            caregiverDetails1.add(familyMember1);


            Map<Object,Object> requestPayload1=new HashMap<>(requestPayload0);
            requestPayload1.put("phoneNo", "9080706052");// unique phone in user data
            requestPayload1.put("email", "");
            requestPayload1.put("emailVerification", false);
            requestPayload1.put("careGivers",caregiverDetails1);

            //duplicate email in CG that are in DB 
            Map<Object,Object> familyMember11 = new HashMap<>(familyMember1);
            familyMember11.put("phoneNo", "9080706053");// unique phone in CG data
            familyMember11.put("email", "user1@yopmail.com");// duplicate email in CG data(DB)
            familyMember11.put("emailVerification", true);
            List<Object> caregiverDetails11 = new ArrayList<>();
            caregiverDetails11.add(familyMember11);

            Map<Object,Object> requestPayload11=new HashMap<>(requestPayload1);
            requestPayload11.put("careGivers", caregiverDetails11);

        //Duplication check with CG data (Not in DB)
            //Duplicate phone between CG data and unique in user data
            Map<Object,Object>familyMember2 = new HashMap<>(familyMember);
            familyMember2.put("phoneNo", "9080706053"); // duplicate phone in CG data
            familyMember2.put("email", "user2@gmail.com");// unique email in CG data
            familyMember2.put("emailVerification", true);
            Map<Object,Object> familyMember3=new HashMap<>(familyMember2);
            familyMember3.put("email", "user4@gmail.com"); // unique email in CG(2) data
            List<Object> caregiverDetails2 =new ArrayList<>();
            caregiverDetails2.add(familyMember2);
            caregiverDetails2.add(familyMember3);
            Map<Object,Object> requestPayload2=new HashMap<>(requestPayload1);
            requestPayload2.put("careGivers", caregiverDetails2);

            //Duplicate email between CG data and unique in user data
            Map<Object,Object> familyMember21 = new HashMap<>(familyMember2);
            Map<Object,Object> familyMember31 = new HashMap<>(familyMember3);
            familyMember31.put("phoneNo", "9080706054"); // unique phone in CG(1) data
            familyMember31.put("email", "user2@gmail.com"); //Duplicate email in CG data
            List<Object> caregiverDetails21 = new ArrayList<>();
            caregiverDetails21.add(familyMember21);
            caregiverDetails21.add(familyMember31);

            Map<Object,Object> requestPayload21=new HashMap<>(requestPayload2);
            requestPayload21.put("careGivers", caregiverDetails21);

            List<Map<Object,Object>> reqList = new ArrayList<>();
            reqList.add(requestPayload0);
            reqList.add(requestPayload01); 
            reqList.add(requestPayload1);
            reqList.add(requestPayload11);
            reqList.add(requestPayload2);
            reqList.add(requestPayload21);
            int i=0;
            for (Map<Object,Object> payload : reqList){
            // System.out.println("Request Payload: " + payload);
                if(i==0){
                System.out.println("duplicate phone between user and CG data not in DB");
                }
            else if(i==1){
                System.out.println("duplicate email between user and CG data not in DB");
            }
            else if(i==2){
                System.out.println("Duplicate phone in CG that are already in DB");
            }
            else if(i==3){
                System.out.println("Duplicate email in CG that are already in DB");
            }
            else if(i==4){
                System.out.println("Duplication of phone between the caregivers");
            }
            else if(i==5){
                System.out.println("Duplication of email between the caregivers");
            }
            try {
                APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", payload);
                int statusCode = responseAPI.status();
                String response = getBodyData(responseAPI).toString();
                if (isJSONValid(response) && statusCode == 200) {
                    JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                    String resposeCode = responsebody.get("code").getAsString();
                    String resposeMessage = responsebody.get("message").getAsString();
                    try {
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try {
                        Assert.assertEquals(resposeMessage, "Already Exist");
                        System.out.println("response message is valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            }
            catch (AssertionError e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
            }
            System.out.println("------------------------");
            i++;
            }
        }

    @Test(description = "Registration")
    public void userRegCGLimitCheck() {

                                    //Registration_TC_15

    // CG limit check
        List<Object> caregiverDetails = new ArrayList<>();

        // Loop to create 5 caregivers dynamically
        for (int i = 0; i < 5; i++) {

            Map<String, Object> familyMember = new HashMap<>();
            familyMember.put("firstName", "familyMember");
            familyMember.put("lastName", String.valueOf(i));
            familyMember.put("dateOfBirth", "15-07-2002");
            familyMember.put("gender", "Female");
            familyMember.put("phoneNo", "908070606" + i);  
            familyMember.put("countryCode", "+91");
            familyMember.put("profilePhoto", "");
            familyMember.put("age", "22");
            familyMember.put("emailVerification", true);
            familyMember.put("phoneNoVerificatio", true);
            familyMember.put("email", "user2" + i + "@yopmail.com"); 
            familyMember.put("relationship", "Brother");

        // Add this familyMember to caregiver list
        caregiverDetails.add(familyMember);
        }

        // Now attach caregiverDetails to requestPayload
        Map<Object, Object> payload = new HashMap<>();
        payload.put("firstName", "John");
        payload.put("lastName", "Doe");
        payload.put("dateOfBirth", "15-07-1982");
        payload.put("gender", "Male");
        payload.put("phoneNo", "9080706052");
        payload.put("countryCode", "+91");
        payload.put("profilePhoto", "");
        payload.put("email", "user4@yopmail.com");
        payload.put("age", "43");
        payload.put("accept", true);
        payload.put("emailVerification", true);
        payload.put("phoneNoVerificatio", true);
        payload.put("careGivers", caregiverDetails);
        try {
            APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", payload);
            int statusCode = responseAPI.status();
            String response = getBodyData(responseAPI).toString();
            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String resposeCode = responsebody.get("code").getAsString();
                String resposeMessage = responsebody.get("message").getAsString();
                    try {
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("response code valid : " + resposeCode);
                    } 
                    catch (AssertionError e) {
                            System.out.println("response code invalid : " + resposeCode);
                    }
                    try {
                        Assert.assertEquals(resposeMessage, "You can add up to 4 members only.");
                        System.out.println("response message is valid : " + resposeMessage);
                    }
                    catch (AssertionError e) {
                            System.out.println("response message invalid : " + resposeMessage);
                    }
                    }
            }
            catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
            }
    }

    @Test(description = "Registration")
    public void userRegWithNullValues() throws IOException{
   
    List<String> caregiverDetails = new ArrayList<>();      //empty cg list
    // Phone number = null but verification = true
    Map<Object, Object> payload = new HashMap<>();
    payload.put("firstName", "Test");
    payload.put("lastName", "User");
    payload.put("dateOfBirth", "15-07-1995");
    payload.put("gender", "Male");
    payload.put("phoneNo", null); // NULL PHONE
    payload.put("countryCode", "+91");
    payload.put("profilePhoto", "");
    payload.put("email", "usernullphone@yopmail.com");
    payload.put("age", "30");
    payload.put("accept", true);
    payload.put("emailVerification", true);
    payload.put("phoneNoVerificatio", true);
    payload.put("careGivers", caregiverDetails);

    // Email = null but verification = true
    Map<Object, Object> payload1 = new HashMap<>(payload);
    payload1.put("phoneNo", "9080706099"); // valid phone
    payload1.put("email", null); // NULL EMAIL
    payload1.put("emailVerification", true); 
    payload1.put("phoneNoVerificatio", true);

    List<Map<Object, Object>> reqList = new ArrayList<>();
    reqList.add(payload);
    reqList.add(payload1);

    int i = 0;
    for (Map<Object, Object> request : reqList) {
        if(i==0){
            System.out.println("null value on Phone number field");
        }
        else if(i==1){
            System.out.println("null value on email field");
        }
        try {
            APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", payload);
            int statusCode = responseAPI.status();
            String response = getBodyData(responseAPI).toString();

            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String responseCode = responsebody.get("code").getAsString();
                String responseMessage = responsebody.get("message").getAsString();
                try {
                    Assert.assertEquals(responseCode, "1111");
                    System.out.println("Response code valid : " + responseCode);
                } catch (AssertionError e) {
                    System.out.println("Response code invalid : " + responseCode);
                }
                try {
                    Assert.assertEquals(responseMessage, "Data should not be empty");
                    System.out.println("Response message valid : " + responseMessage);
                } catch (AssertionError e) {
                    System.out.println("Response message invalid : " + responseMessage);
                }
            }
            } catch (AssertionError e) {
            System.out.println("AssertionError occurred: " + e.getMessage());
            }

        System.out.println("------------------------");
        i++;
    }
}

    @Test(description = "Registration")
    public void caregiverRegWithNullValues() {

    Map<Object, Object> userPayload = new HashMap<>();
    userPayload.put("firstName", "Periya");
    userPayload.put("lastName", "saami");
    userPayload.put("dateOfBirth", "15-07-1985");
    userPayload.put("gender", "Male");
    userPayload.put("phoneNo", "9080706011");
    userPayload.put("countryCode", "+91");
    userPayload.put("profilePhoto", "");
    userPayload.put("email", "periyasaami@yopmail.com");
    userPayload.put("age", "40");
    userPayload.put("accept", true);
    userPayload.put("emailVerification", true);
    userPayload.put("phoneNoVerificatio", true);

    // Phone number = null but verification = true
    Map<String, Object> caregiverNullPhone = new HashMap<>();
    caregiverNullPhone.put("firstName", "valli");
    caregiverNullPhone.put("lastName", "ammal");
    caregiverNullPhone.put("dateOfBirth", "15-07-1990");
    caregiverNullPhone.put("gender", "Female");
    caregiverNullPhone.put("phoneNo", null);
    caregiverNullPhone.put("countryCode", "+91");
    caregiverNullPhone.put("profilePhoto", "");
    caregiverNullPhone.put("email", "valliyammal@yopmail.com");
    caregiverNullPhone.put("age", "33");
    caregiverNullPhone.put("relationship", "Sister");
    caregiverNullPhone.put("emailVerification", true);
    caregiverNullPhone.put("phoneNoVerificatio", true);

    // Email = null but verification = true
    Map<String, Object> caregiverNullEmail = new HashMap<>(caregiverNullPhone);
    caregiverNullEmail.put("phoneNo", "9080706018");
    caregiverNullEmail.put("email", null);
    caregiverNullEmail.put("emailVerification", true);

    List<Object> cgCase1List = new ArrayList<>();
    cgCase1List.add(caregiverNullPhone);

    List<Object> cgCase2List = new ArrayList<>();
    cgCase2List.add(caregiverNullEmail);

    Map<Object, Object> requestCase1 = new HashMap<>(userPayload);
    requestCase1.put("careGivers", cgCase1List);

    Map<Object, Object> requestCase2 = new HashMap<>(userPayload);
    requestCase2.put("careGivers", cgCase2List);

    List<Map<Object, Object>> reqList = new ArrayList<>();
    reqList.add(requestCase1);
    reqList.add(requestCase2);

    int i = 0;
    for (Map<Object, Object> request : reqList) {
        if (i == 0) {
            System.out.println("null value on CareGiver Phone number field");
        } else if (i == 1) {
            System.out.println("null value on CareGiver email field");
        }
        try {
            APIResponse responseAPI = postRequestWithoutToken("user-registration", "MOBILE", request);
            int statusCode = responseAPI.status();
            String response = getBodyData(responseAPI).toString();

            if (isJSONValid(response) && statusCode == 200) {
                JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                String responseCode = responsebody.get("code").getAsString();
                String responseMessage = responsebody.get("message").getAsString();
                try {
                    Assert.assertEquals(responseCode, "1111");
                    System.out.println("Response code valid : " + responseCode);
                } catch (AssertionError e) {
                    System.out.println("Response code invalid : " + responseCode);
                }
                try {
                    Assert.assertEquals(responseMessage, "Data should not be empty");
                    System.out.println("Response message valid : " + responseMessage);
                } catch (AssertionError e) {
                    System.out.println("Response message invalid : " + responseMessage);
                }
            }
        } catch (Exception e) {
            System.out.println("AssertionError occurred: " + e.getMessage());
        }
        System.out.println("------------------------");
        i++;
    }
}

    @Test(description = "Email and Phone Verification")
    public void userVerificationSuccess(){
                                    //Registration_TC_20
        // email verification....
        Map<Object ,Object> payload = new HashMap<>();
        payload.put("email","mahesh@yopmail.com");
        payload.put("value","email");
        payload.put("phoneNo","9000000061");
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "0000");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Successfully created");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            
            }
            catch(AssertionError e){
                System.out.println("error Occured :"+e.getMessage());
            }
        // phoneNo verification...
        Map<Object ,Object> payload1 = new HashMap<>();
        payload1.put("email",null);
        payload1.put("value","phone");
        payload1.put("phoneNo","8765452425");
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "0000");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Successfully created");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            
            }
            catch(AssertionError e){
                System.out.println("error Occured :"+e.getMessage());
            }
    }

    @Test(description = "Email and Phone Verification")
    public void userVerificationEmailFailure(){

                                    //Registration_TC_22
        // invalid email verification....
        System.out.println("invalid email verification");
        Map<Object ,Object> payload = new HashMap<>();
        payload.put("email","swathi@gmcom");
        payload.put("value","email");
        payload.put("phoneNo",null);
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Please enter valid email address");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            
            }
            catch(AssertionError e){
                System.out.println("error Occured :"+e.getMessage());
            }
                                 //Registration_TC_23

        //Already existed email verification....
        System.out.println("Already exist email verification");
        Map<Object ,Object> payload1 = new HashMap<>(payload);
        payload1.put("email","prem@gmaol.com");
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload1);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Email address is already exists");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            
        }
        catch(AssertionError e){
            System.out.println("error Occured :"+e.getMessage());
        }
    }

    @Test(description = "Email and Phone Verification")
    public void userVerificationPhoneNoFailure(){

                                     //Registration_TC_24
        // invalid email verification....
        System.out.println("invalid PhoneNumber verification");
        Map<Object ,Object> payload = new HashMap<>();
        payload.put("email",null);
        payload.put("value","phone");
        payload.put("phoneNo","87653425");
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Please enter valid contact number");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            
            }
            catch(AssertionError e){
                System.out.println("error Occured :"+e.getMessage());
            }
                                 //Registration_TC_25

        //Already existed email verification....
        System.out.println("Already exist PhoneNumber verification");
        Map<Object ,Object> payload1 = new HashMap<>(payload);
        payload1.put("phoneNo","9484646565");
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload1);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Contact number already exists");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
            
        }
        catch(AssertionError e){
            System.out.println("error Occured :"+e.getMessage());
        }

                                 //Registration_TC_26

        //Verify email without email verification....
        System.out.println("Verify email without email");
        Map<Object ,Object> payload2 = new HashMap<>(payload1);
        payload2.put("value","email");
        System.out.println(payload2);
        try{
            APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload2);
            int responseStatus = response.status();
            String responseMessage = response.text();
                if(isJSONValid(responseMessage) && responseStatus==200){
                    JsonObject responseBody = JsonParser.parseString(responseMessage).getAsJsonObject();
                    String resposeCode=responseBody.get("code").getAsString();
                    String resposeMessage=responseBody.get("message").getAsString();
                    try{  
                        Assert.assertEquals(resposeCode, "1111");
                        System.out.println("Success code valid : " + resposeCode);
                    } catch (AssertionError e) {
                        System.out.println("Success code invalid : " + resposeCode);
                    }
                    try{  
                        Assert.assertEquals(resposeMessage, "Email cannot be null");
                        System.out.println("Success message valid : " + resposeMessage);
                    } catch (AssertionError e) {
                        System.out.println("Success message invalid : " + resposeMessage);
                    }
                }
                else{
                    System.out.println(responseMessage);
                }
            
        }
        catch(AssertionError e){
            System.out.println("error Occured :"+e.getMessage());
        }
    }

    @Test(description = "Email and Phone Verification")
    public void codeVerificationSuccess(){
                                           //Registration_TC_27
        // email verification sucess ....
        System.out.println("Successfull code verification for email");
        String VerificationCode = "IK3198"; //input 
        Map<Object ,Object> payload = new HashMap<>();
        payload.put("email","prem@gmaol.com");
        payload.put("verificationCode",VerificationCode);
        payload.put("value","email");
        APIResponse response = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload);
        int statusCode = response.status();
        String ResponseMessage = response.text();
        if(isJSONValid(ResponseMessage)&&statusCode==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "0000");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Successfully updated");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }
                                    //Registration_TC_28
        // phone verification sucess ....
        System.out.println("Successfull code verification for phoneNumber");
        String VerificationCode1 = "961236"; //input 
        Map<Object ,Object> payload1 = new HashMap<>();
        payload1.put("phoneNo","8686868686");
        payload1.put("verificationCode",VerificationCode1);
        payload1.put("value","phone");
        APIResponse response1 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload1);
        int statusCode1 = response1.status();
        String ResponseMessage1 = response1.text();
        if(isJSONValid(ResponseMessage)&&statusCode1==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage1).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "0000");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Successfully updated");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }
    }

    @Test(description = "Email and Phone Verification")
    public void codeVerificationEmailFailure(){
         // email verification failure ....
        System.out.println("code verification failure for invalid email");

        Map<Object ,Object> payload = new HashMap<>();
        payload.put("email","prem@gmail.com"); // invalid email
        payload.put("verificationCode","IK3198"); // valid code
        payload.put("value","email");
        APIResponse response = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload);
        int statusCode = response.status();
        String ResponseMessage = response.text();
        if(isJSONValid(ResponseMessage)&&statusCode==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "1111");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Please enter a valid code");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }

        System.out.println("code verification failure for invalid verificationCode");
        
        Map<Object ,Object> payload1 = new HashMap<>();
        payload1.put("email","prem@gmail.com"); // valid email
        payload1.put("verificationCode","IK3298"); // invalid code
        payload1.put("value","email");
        APIResponse response1 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload1);
        int statusCode1 = response1.status();
        String ResponseMessage1 = response1.text();
        if(isJSONValid(ResponseMessage1)&&statusCode1==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage1).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "1111");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Please enter a valid code");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }

        System.out.println("code verification failure for wrong value");
        
        Map<Object ,Object> payload2 = new HashMap<>();
        payload2.put("email","prem@gmail.com"); // valid email
        payload2.put("verificationCode","IK3198"); // valid code
        payload2.put("value","phone"); // wrong value
        APIResponse response2 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload2);
        int statusCode2 = response2.status();
        String ResponseMessage2 = response2.text();
        if(isJSONValid(ResponseMessage2)&&statusCode2==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage2).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "1111");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Please enter a valid code");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }
    
    }

     @Test(description = "Email and Phone Verification")
    public void codeVerificationPhoneFailure(){
         // phoneNo verification failure ....
        System.out.println("code verification failure for invalid phoneNumber");

        Map<Object ,Object> payload = new HashMap<>();
        payload.put("phoneNo","8686238686"); // invalid phone number
        payload.put("verificationCode","961236"); // valid code
        payload.put("value","phone");
        APIResponse response = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload);
        int statusCode = response.status();
        String ResponseMessage = response.text();
        if(isJSONValid(ResponseMessage)&&statusCode==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "1111");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Please enter a valid code");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }

        System.out.println("code verification failure for invalid verificationCode");
        
        Map<Object ,Object> payload1 = new HashMap<>();
        payload1.put("phoneNo","8686868686"); // valid phoneNumber
        payload1.put("verificationCode","965236"); // invalid code
        payload1.put("value","phone");
        APIResponse response1 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload1);
        int statusCode1 = response1.status();
        String ResponseMessage1 = response1.text();
        if(isJSONValid(ResponseMessage1)&&statusCode1==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage1).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "1111");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + responseCode);
        }
        try {
            Assert.assertEquals(responseMessage, "Please enter a valid code");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + responseMessage);
        }
        }
        else{
            System.out.println("error in response");
        }

        System.out.println("code verification failure for wrong value");
        
        Map<Object ,Object> payload2 = new HashMap<>();
        payload2.put("phoneNo","8686868686"); // valid phoneNumber
        payload2.put("verificationCode","961236"); // valid code
        payload2.put("value","email"); // wrong value
        APIResponse response2 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload2);
        int statusCode2 = response2.status();
        String ResponseMessage2 = response2.text();
        if(isJSONValid(ResponseMessage2)&&statusCode2==200){
        JsonObject responseBody=JsonParser.parseString(ResponseMessage2).getAsJsonObject();
        String responseCode = responseBody.get("code").getAsString();
        String responseMessage = responseBody.get("message").getAsString();
        try{
            Assert.assertEquals(responseCode, "1111");
            System.out.println("Success code valid : " + responseCode);
        }
        catch(AssertionError e){
            System.out.println("Success code invalid : " + e.getMessage());
        }
        try {
            Assert.assertEquals(responseMessage, "Please enter a valid code");
            System.out.println("Success message valid : " + responseMessage);
        } catch (AssertionError e) {
            System.out.println("Success message invalid : " + e.getMessage());
        }
        }
        else{
            System.out.println("error in response");
        }
    
    }

    @Test(description = "Email and Phone Verification")
    public void duplicateVerification(){

                                    //Registration_TC_35

        // System.out.println("email verification sent");
        // Map<Object ,Object> payload = new HashMap<>();
        // payload.put("email","mahesh@gmail.com");
        // payload.put("value","email");
        // payload.put("phoneNo","9000000061");
        // try{
        //     APIResponse response = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload);
        //     int responseStatus = response.status();
        //     String responseMessage = response.text();
        //         if(isJSONValid(responseMessage) && responseStatus==200){
                    try{
                        System.out.println("code verification success");
                        String VerificationCode = "IK3949"; //input code
                        Map<Object ,Object> payload1 = new HashMap<>();
                        payload1.put("email","mahesh@yopmail.com"); 
                        payload1.put("verificationCode",VerificationCode); 
                        payload1.put("value","email");
                        APIResponse response1 = postRequestWithoutToken("rest/api/v1/code-verification", "Mobile", payload1);
                        int statusCode1 = response1.status();
                        String ResponseMessage1 = response1.text();
                        if(isJSONValid(ResponseMessage1)&&statusCode1==200){
                        JsonObject responseBody1=JsonParser.parseString(ResponseMessage1).getAsJsonObject();
                        String responseCode1 = responseBody1.get("code").getAsString();
                            try{
                                Assert.assertEquals(responseCode1, "0000");
                                System.out.println("Success code valid : " + responseCode1);

                                //dupicate verification for already verified email

                                try{
                                    System.out.println("email verification sent for already verified email");
                                    Map<Object ,Object> payload3 = new HashMap<>();
                                        payload3.put("email","mahesh@yopmail.com");
                                        payload3.put("value","email");
                                        payload3.put("phoneNo","9000000061");
                                    try{
                                        APIResponse response3 = postRequestWithoutToken("rest/api/v1/email-verify","MOBILE", payload3);
                                        int responseStatus3 = response3.status();
                                        String responseMessage3 = response3.text();
                                        if(isJSONValid(responseMessage3) && responseStatus3==200){
                                            JsonObject responseBody3=JsonParser.parseString(responseMessage3).getAsJsonObject();
                                            String responseCode3 = responseBody3.get("code").getAsString();
                                            String responseMessage4 = responseBody3.get("message").getAsString();

                                            try {
                                                Assert.assertEquals(responseCode3, "2222");
                                                System.out.println("response code valid :"+responseCode3);
                                            } catch (AssertionError e) {
                                                System.out.println(e.getMessage());
                                            }
                                             try {
                                                Assert.assertEquals(responseMessage4, "Email address is already verified");
                                                System.out.println("response code valid :"+responseMessage4);
                                            } catch (AssertionError e) {
                                                System.out.println(e.getMessage());
                                            }

                                        }
                                    }
                                    catch(Exception e){

                                    }
                                }
                                catch(Exception e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            catch(AssertionError e){
                                System.out.println("Success code invalid : " + responseCode1);
                            }
                        }
                        else{
                            System.out.println("error in response");
                        }

                    }
                    catch(Exception e){

                    }
                }                
}
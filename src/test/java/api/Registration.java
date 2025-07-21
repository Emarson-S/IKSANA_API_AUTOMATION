package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

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
            requestPayload.put("firstName", "John");
            requestPayload.put("lastName", "Doe");
            requestPayload.put("dateOfBirth", "15-07-1997");
            requestPayload.put("gender", "Male");
            requestPayload.put("phoneNo", "8000000901");
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

    @Test
    public void registrationSuccessWithCG() {
        try {
            Map<String, Object> familyMember = new HashMap<>();
            familyMember.put("firstName", "Swathi");
            familyMember.put("lastName", "Doe");
            familyMember.put("dateOfBirth", "15-07-2002");
            familyMember.put("gender", "Female");
            familyMember.put("phoneNo", "8219787865");
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
            requestPayload.put("phoneNo", "9640787874");
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

    @Test
    public void userRegDuplicationCheck() {
        
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
            System.out.println("Request Payload: " + payload);
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
        catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
        System.out.println("------------------------");
        }
    }
    
    @Test
    public void userRegDuplicationCheckWithCG() {  
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
            System.out.println("Request Payload: " + payload);
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
            catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
            }
            System.out.println("------------------------");
            i++;
            }
        }

    @Test
    public void userRegDuplicationCheckWithCGInDB() {
}
}

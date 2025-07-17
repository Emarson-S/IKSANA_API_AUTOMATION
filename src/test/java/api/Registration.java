package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;

import base.BaseClass;

public class Registration extends BaseClass{

@Test
public void registrationSuccess() {
    try{
        List <String> caregiverDetails = new ArrayList<>(); // empty list for caregivers
        Map<Object,Object> requestPayload = new HashMap<>();
        requestPayload.put("firstName", "John");
        requestPayload.put("lastName", "Doe");
        requestPayload.put("dateOfBirth","15-07-1997");
        requestPayload.put("gender", "Male");
        requestPayload.put("phoneNo", "7979787874");
        requestPayload.put("countryCode", "+91");
        requestPayload.put("profilePhoto", "");
        requestPayload.put("careGivers",caregiverDetails);
        requestPayload.put("age", "28");
        requestPayload.put("accept", true);
        requestPayload.put("email",""); 
        requestPayload.put("emailVerification", false);
        requestPayload.put("phoneNoVerificatio", true);
        APIResponse responseAPI = postRequestWithoutToken("user-registration","MOBILE",requestPayload);
        String response=responseAPI.text();
                                //<---Registration_TC_01 --->
                                //<---Registration_TC_02 --->

        if (isJSONValid(response) && responseAPI.status()==200){
            System.out.println("Status code verified : " + responseAPI.status());
            JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                            
                                //<---Registration_TC_03 --->

                if(responsebody.get("code").getAsInt()== 0000){
                    System.out.println("success code verified: " + responsebody.get("code").getAsString());
                }
                else {
                    System.out.println("Registration failed with code: " + responsebody.get("code").getAsString());
                }  
                // Verify the message in the response 
                if(responsebody.get("message").getAsString()=="Registration Successful!"){
                    System.out.println("Registration successful with message: " + responsebody.get("message").getAsString());
                }
                else {
                    System.out.println("Registration failed with message : " + responsebody.get("message").getAsString());
                }
                // Verify the data in the response
                String responsebodydata= responsebody.get("data").toString();
                if(isJSONValid(responsebodydata)){
                    JsonObject responsedata = responsebody.get("data").getAsJsonObject();
                    JsonObject userdata=responsedata.get("user").getAsJsonObject();
                    if(userdata.get("userId")!= null){
                        System.out.println("User ID created successfully : " + userdata.get("userId").getAsString());
                    }
                    else {
                        System.out.println("User ID failed to create.");
                    }
                }
                else {
                    System.out.println("Data Not found");
                }
        }
        else {
            System.out.println("Registration failed with status: " + responseAPI.status());
        }
    }   
catch (Exception e) {
        System.out.println("An error occurred during registration: " + e.getMessage());
}
}
@Test
public void registrationSuccessWithCG(){
      try{
        Map <String,Object> familyMember = new HashMap<>();
        familyMember.put("firstName", "Swathi");
        familyMember.put("lastName", "Doe");
        familyMember.put("dateOfBirth","15-07-2002");
        familyMember.put("gender", "Female");
        familyMember.put("phoneNo", "8219787865");
        familyMember.put("countryCode", "+91");
        familyMember.put("profilePhoto", "");
        familyMember.put("age", "22");
        familyMember.put("emailVerification", true);
        familyMember.put("phoneNoVerificatio", true);
        familyMember.put("email","Mugilan123@yopmail.com");
        familyMember.put("relationship","Daughter");

        List <Object> caregiverDetails = new ArrayList<>();
        caregiverDetails.add(familyMember);

        Map<Object,Object> requestPayload = new HashMap<>();
        requestPayload.put("firstName", "vishnu");
        requestPayload.put("lastName", "Ganesh");
        requestPayload.put("dateOfBirth","15-07-1997");
        requestPayload.put("gender", "Male");
        requestPayload.put("phoneNo", "9640787874");
        requestPayload.put("countryCode", "+91");
        requestPayload.put("profilePhoto", "");
        requestPayload.put("email",""); 
        requestPayload.put("age", "28");
        requestPayload.put("accept", true);
        requestPayload.put("emailVerification", false);
        requestPayload.put("phoneNoVerificatio", true);
        requestPayload.put("careGivers",caregiverDetails);
        System.out.println("Request Payload: " + requestPayload);
        APIResponse responseAPI = postRequestWithoutToken("user-registration","MOBILE",requestPayload);
        String response=responseAPI.text();
                                //<---Registration_TC_04 --->
                                //<---Registration_TC_05 --->

        if (isJSONValid(response) && responseAPI.status()==200){
            System.out.println("Status code verified : " + responseAPI.status());
            JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
            System.out.println(responsebody);
                            
                                //<---Registration_TC_06 --->

                if(responsebody.get("code").getAsInt()== 0000){
                    System.out.println("success code verified: " + responsebody.get("code").getAsString());
                }
                else {
                    System.out.println("Registration failed with code: " + responsebody.get("code").getAsString());
                }  
                // Verify the message in the response 
                if(responsebody.get("message").getAsString()=="Registration Successful!"){
                    System.out.println("Registration successful with message: " + responsebody.get("message").getAsString());
                }
                else {
                    System.out.println("Registration failed with message : " + responsebody.get("message").getAsString());
                }
                // Verify the data in the response
                String responsebodydata= responsebody.get("data").toString();
                if(isJSONValid(responsebodydata)){
                    JsonObject responsedata = responsebody.get("data").getAsJsonObject();
                    JsonObject userdata=responsedata.get("user").getAsJsonObject();
                    if(userdata.get("userId")!= null){
                        System.out.println("User ID created successfully : " + userdata.get("userId").getAsString());
                    }
                    else {
                        System.out.println("User ID failed to create.");
                    }
                }
                else {
                    System.out.println("Data Not found");
                }
        }
        else {
            System.out.println("Registration failed with status: " + responseAPI.status());
        }
    }   
catch (Exception e) {
        System.out.println("An error occurred during registration: " + e.getMessage());
}

}
@Test
public void regFailureWithDuplicateData() {
    try{
        List <String> caregiverDetails = new ArrayList<>(); // empty list for caregivers
        Map<Object,Object> requestPayload = new HashMap<>();
        requestPayload.put("firstName", "John");
        requestPayload.put("lastName", "Doe");
        requestPayload.put("dateOfBirth","15-07-1997");
        requestPayload.put("gender", "Male");
        requestPayload.put("phoneNo", "7979787874");
        requestPayload.put("countryCode", "+91");
        requestPayload.put("profilePhoto", "");
        requestPayload.put("careGivers",caregiverDetails);
        requestPayload.put("age", "28");
        requestPayload.put("accept", true);
        requestPayload.put("email","Mega@gmail.com"); 
        requestPayload.put("emailVerification", false);
        requestPayload.put("phoneNoVerificatio", true);
        APIResponse responseAPI = postRequestWithoutToken("user-registration","MOBILE",requestPayload);
        String response=responseAPI.text();
                                //<---Registration_TC_01 --->
                                //<---Registration_TC_02 --->

        if (isJSONValid(response) && responseAPI.status()==200){
            System.out.println("Status code verified : " + responseAPI.status());
            JsonObject responsebody = JsonParser.parseString(response).getAsJsonObject();
                            
                                //<---Registration_TC_03 --->

                if(responsebody.get("code").getAsInt()== 0000){
                    System.out.println("success code verified: " + responsebody.get("code").getAsString());
                }
                else {
                    System.out.println("Registration failed with code: " + responsebody.get("code").getAsString());
                }  
                // Verify the message in the response 
                if(responsebody.get("message").getAsString()=="Registration Successful!"){
                    System.out.println("Registration successful with message: " + responsebody.get("message").getAsString());
                }
                else {
                    System.out.println("Registration failed with message : " + responsebody.get("message").getAsString());
                }
                // Verify the data in the response
                String responsebodydata= responsebody.get("data").toString();
                if(isJSONValid(responsebodydata)){
                    JsonObject responsedata = responsebody.get("data").getAsJsonObject();
                    JsonObject userdata=responsedata.get("user").getAsJsonObject();
                    if(userdata.get("userId")!= null){
                        System.out.println("User ID created successfully : " + userdata.get("userId").getAsString());
                    }
                    else {
                        System.out.println("User ID failed to create.");
                    }
                }
                else {
                    System.out.println("Data Not found");
                }
        }
        else {
            System.out.println("Registration failed with status: " + responseAPI.status());
        }
    }   
catch (Exception e) {
        System.out.println("An error occurred during registration: " + e.getMessage());
}
}

}
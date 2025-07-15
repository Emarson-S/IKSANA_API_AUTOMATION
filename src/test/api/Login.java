import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class Login extends BaseClass{

    @test
    login success(){
        Map<Object,Object> map=new HashMap<>();
        map.put("test","meha");

        Playwright playwright =Playwright.create();
        APIRequest  apiRequest=playwright.request();
        APIRequestContext context=apiRequest.newContext();
        APIResponse response=context.post("http://57897.com", RequestOptions.create().setHeader("Application","Iksana-Base").setData(map));

    
    }

    

}

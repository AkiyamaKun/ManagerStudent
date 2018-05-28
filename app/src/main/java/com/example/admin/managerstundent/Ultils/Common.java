package com.example.admin.managerstundent.Ultils;

import com.example.admin.managerstundent.Remote.RetrofitClient;
import com.example.admin.managerstundent.Service.StudentDemoService;

/**
 * Author: DangNHH
 * 27/05/2018
 *
 * This class contain all interface service
 */
public class Common {

    private static final String BASE_URL = "http://myfirstazurewebapp20180423110404.azurewebsites.net/api/";

    public static StudentDemoService getService(){
        return RetrofitClient.getClient(BASE_URL).create(StudentDemoService.class);
    }
}

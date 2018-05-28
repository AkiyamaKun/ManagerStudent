package com.example.admin.managerstundent.Service;

import com.example.admin.managerstundent.Entity.StudentDemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: DangNHH
 * 27/05/2018
 *
 * Interface Service of Student Demo
 */
public interface StudentDemoService {
    @GET("student/{id}")
    Call<StudentDemo> getStudentDemo(@Path("id")Integer id);
}

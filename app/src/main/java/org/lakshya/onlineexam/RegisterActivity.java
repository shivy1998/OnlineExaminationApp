package org.lakshya.onlineexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText etName,etEmailId,etPassword,etMobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmailId = findViewById(R.id.etEmailid);
        etPassword = findViewById(R.id.etPassword);
        etMobileNo = findViewById(R.id.etMobileNo);
        Button btnRegister = findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String emailid = etEmailId.getText().toString();
                String password = etPassword.getText().toString();
                String mobileno = etMobileNo.getText().toString();

                insertStudentRecord(name,emailid,password,mobileno);
            }
        });
    }

    private void insertStudentRecord(final String name, final String emailid, final String password, final String mobileno) {
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        String REGISTER_URL = "http://192.168.43.216/lakshya/insert-student.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("name",name);
                params.put("emailid",emailid);
                params.put("password",password);
                params.put("mobileno",mobileno);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
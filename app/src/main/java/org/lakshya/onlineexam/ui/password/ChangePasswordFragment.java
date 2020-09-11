package org.lakshya.onlineexam.ui.password;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.lakshya.onlineexam.LoginActivity;
import org.lakshya.onlineexam.R;
import org.lakshya.onlineexam.RegisterActivity;

import java.util.HashMap;
import java.util.Map;


public class ChangePasswordFragment extends Fragment {
    EditText etCurrentPassword,etNewPassword,etRetypePassword;
    SharedPreferences sp;
    String emailid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        emailid = sp.getString("emailid",null);

        View view =  inflater.inflate(R.layout.fragment_change_password, container, false);
        etCurrentPassword = view.findViewById(R.id.etCurrentPassword);
        etNewPassword = view.findViewById(R.id.etNewPassword);
        etRetypePassword = view.findViewById(R.id.etRetypePassword);

        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentpassword = etCurrentPassword.getText().toString();
                String newpassword = etNewPassword.getText().toString();
                String retypepassword = etRetypePassword.getText().toString();
                if(currentpassword.length()==0){
                    Toast.makeText(getActivity(),"Enter your current password",Toast.LENGTH_LONG).show();
                }
                else if(newpassword.length()==0){
                    Toast.makeText(getActivity(),"Enter your new password",Toast.LENGTH_LONG).show();
                }
                else if(retypepassword.length()==0){
                    Toast.makeText(getActivity(),"Enter retype password",Toast.LENGTH_LONG).show();
                }
                else if(!newpassword.equals(retypepassword)){
                    Toast.makeText(getActivity(),"Retype password not matched",Toast.LENGTH_LONG).show();
                }
                else{
                    updatePassword(currentpassword,newpassword);
                }
            }
        });
        return view;
    }
    private void updatePassword(final String currentpassword, final String newpassword) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String UPDATE_PASSWORD_URL = "http://192.168.43.216/lakshya/update-student-password.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_PASSWORD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("current password not matched")){
                            Toast.makeText(getActivity(),"current password not matched",Toast.LENGTH_LONG).show();
                        }
                        else if(response.trim().equals("success")){
                            Toast.makeText(getActivity(),"password updated",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Try Again",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("currentpassword",currentpassword);
                params.put("newpassword",newpassword);
                params.put("emailid",emailid);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }
}
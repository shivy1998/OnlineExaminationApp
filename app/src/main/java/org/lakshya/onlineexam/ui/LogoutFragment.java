package org.lakshya.onlineexam.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lakshya.onlineexam.LoginActivity;
import org.lakshya.onlineexam.R;


public class LogoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logot, container, false);

        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        return  view;
    }
}
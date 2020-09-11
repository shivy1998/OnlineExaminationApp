package org.lakshya.onlineexam.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.lakshya.onlineexam.R;
import org.lakshya.onlineexam.adapter.GetExamDetailsAdapter;
import org.lakshya.onlineexam.model.GetExamDetails;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    List<GetExamDetails> getExamDetails;
    private static String JSON_URL = "http://192.168.43.216/lakshya/get-exam.php";
    GetExamDetailsAdapter recordingAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.examList);
        getExamDetails = new ArrayList<>();
        extractExamList();

        return root;
    }

    private void extractExamList() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        GetExamDetails recording = new GetExamDetails();
                        recording.setExamTitle(jsonObject.getString("title").toString());
                        recording.setExamDate(jsonObject.getString("date").toString());
                        recording.setExamImage(jsonObject.getString("image").toString());
                        recording.setExamUrl(jsonObject.getString("image").toString());
                        getExamDetails.add(recording);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(),"Error "+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recordingAdapter = new GetExamDetailsAdapter(getActivity(),getExamDetails);
                recyclerView.setAdapter(recordingAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error Mesage"+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);
    }
}
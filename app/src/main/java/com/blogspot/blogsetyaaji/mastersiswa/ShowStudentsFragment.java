package com.blogspot.blogsetyaaji.mastersiswa;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowStudentsFragment extends Fragment {


    public ShowStudentsFragment() {
        // Required empty public constructor
    }

    private ArrayList<HashMap<String, String>> studentsData;
    /* arraylist{
         hashmap {
             key : value,
             key : value
         },
         hashmap {
             key : value,
             key : value
         },
         hashmap {
             key : value,
             key : value
         }
     }*/
    private String url;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*View view;
        view = inflater.inflate(R.layout.fragment_show_students, container, false);
        RecyclerView lvStudent = view.findViewById(R.id.lv_student);*/

        return inflater.inflate(R.layout.fragment_show_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView lvStudent = view.findViewById(R.id.lv_student);
        studentsData = new ArrayList<HashMap<String, String>>();
        url = "http://192.168.70.10/SMPIDN/webdatabase/apitampilsiswa.php";
        showData();
    }

    private void showData() {
        // menampilkan prses request berupa progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        // membuat request
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // menjalankan request
        RequestQueue myRequestQueue = Volley.newRequestQueue(getActivity());
        myRequestQueue.add(myRequest);
    }
}

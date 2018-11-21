package com.blogspot.blogsetyaaji.mastersiswa;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TampilSiswaFragment extends Fragment {


    public TampilSiswaFragment() {
        // Required empty public constructor
    }

    private RecyclerView lv_siswa;
    private SwipeRefreshLayout swipesiswa;
    private ProgressBar pgsiswa;

    private ArrayList<HashMap<String, String>> listDataSiswa;
    private static String TAG_JSON = "tag_json";
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tampil_siswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_siswa = (RecyclerView) view.findViewById(R.id.lv_siswa);
        swipesiswa = (SwipeRefreshLayout) view.findViewById(R.id.swipeSiswa);
        pgsiswa = (ProgressBar) view.findViewById(R.id.pg_siswa);
        pgsiswa.setVisibility(View.GONE);

        lv_siswa.setLayoutManager(new LinearLayoutManager(getActivity()));

        listDataSiswa = new ArrayList<HashMap<String, String>>();

        url = "http://192.168.50.23/SMPIDN/webdatabase/apitampilsiswa.php";

        showData();
    }

    private void showData() {
        pgsiswa.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pgsiswa.setVisibility(View.GONE);
                // emnampilkan data yang di dapat ke logcat
                Log.d(TAG_JSON, "onResponse: " + response.toString());
                try {
                    // mengambil json array pada data
                    JSONArray jsonArray = response.getJSONArray("siswa");
                    // generate index untuk mengambil json objek di dalam json array siswa per row/index
                    for (int index = 0; index < jsonArray.length(); index++) {
                        // mengambil json objek di dalam json array berdasarkan index yahng sudah tergenerate
                        JSONObject jsonObject = jsonArray.getJSONObject(index);
                        // data yang sudah didapat dipilah lagi per field kemudian di masukkan ke dalam map
                        HashMap<String, String> map = new HashMap<>();
                        // di dalam map, kita masukan key dan value dari data
                        map.put("id", jsonObject.getString("id_siswa"));
                        map.put("nama", jsonObject.getString("nama_siswa"));
                        map.put("alamat", jsonObject.getString("alamat_siswa"));
                        // kita masukkan map field tadi ke dalam array list
                        listDataSiswa.add(map);
                        // kirim array list ke dalam adapter
                        SiswaAdapter siswaAdapter = new SiswaAdapter(getActivity(), listDataSiswa);
                        lv_siswa.setAdapter(siswaAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pgsiswa.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Gagal konek ke server", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }
}

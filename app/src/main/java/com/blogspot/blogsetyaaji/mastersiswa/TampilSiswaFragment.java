package com.blogspot.blogsetyaaji.mastersiswa;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class TampilSiswaFragment extends Fragment {


    public TampilSiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tampil_siswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgGlide = view.findViewById(R.id.imgGlide);

        // menampilkan gambar online di imageview
        Glide.with(view)
                .load("https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png")
                .into(imgGlide);
    }
}

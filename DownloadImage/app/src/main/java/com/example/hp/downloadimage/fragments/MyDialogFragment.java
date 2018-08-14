package com.example.hp.downloadimage.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hp.downloadimage.R;
import com.example.hp.downloadimage.utils.DataProvider;
import com.squareup.picasso.Picasso;

public class MyDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_for_dialogfragment, container, false);
        ImageView imageView = view.findViewById(R.id.dialog_image);
        Picasso.get().load(DataProvider.getItemByPos().getImgUrl()).into(imageView);
        return view;
    }

}

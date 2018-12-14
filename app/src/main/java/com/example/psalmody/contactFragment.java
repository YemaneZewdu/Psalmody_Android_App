package com.example.psalmody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class contactFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);

        Button button = (Button) v.findViewById(R.id.contact_us);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"yemane18berhan@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Psalmody App Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, "0");
                startActivity(Intent.createChooser(intent,"Send Your Feedback"));

            }
        });
        return  v;   }
}

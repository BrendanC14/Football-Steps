package com.cutlerdevelopment.footballsteps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.OfflineSettings;
import com.cutlerdevelopment.footballsteps.Models.SavedData;
import com.cutlerdevelopment.footballsteps.R;

public class MainActivity extends AppCompatActivity implements CreateOfflinePlayerFragment.OnSubmittedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SavedData.createSavedDataInstance(this);
        OfflineSettings.createOfflineSettingsInstance();
        OfflineGame.getInstance();
    }


    public void ClickOfflineCareer(View view) {

        CreateOfflinePlayerFragment fragment = new CreateOfflinePlayerFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, "fragmentTag")
                .addToBackStack(null)
                .commit();
        fragment.setOnSubmittedListener(this);

    }
    @Override
    public void onOfflinePlayerSubmitted() {
        Intent intent = new Intent(this, OfflineCareerMainMenu.class);
        startActivity(intent);
    }
}

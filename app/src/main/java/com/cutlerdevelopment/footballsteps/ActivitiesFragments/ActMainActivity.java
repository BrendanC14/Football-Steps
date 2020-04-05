package com.cutlerdevelopment.footballsteps.ActivitiesFragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cutlerdevelopment.footballsteps.Models.SharedModels.SavedData;
import com.cutlerdevelopment.footballsteps.R;

public class ActMainActivity extends AppCompatActivity implements FragCreateProPlayer.onFinishedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Get rid of this Activity
        Intent intent = new Intent(this, ActMainMenu.class);
        startActivity(intent);

    }


    public void ClickOfflineCareer(View view) {

        SavedData.createSavedDataInstance(this);

        FragCreateProPlayer fragment = new FragCreateProPlayer();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, "fragmentTag")
                .addToBackStack(null)
                .commit();
        fragment.setOnSubmittedListener(this);


    }
    @Override
    public void onFinishedListener() {


        Intent intent = new Intent(this, ActProMainMenu.class);
        startActivity(intent);
    }
}

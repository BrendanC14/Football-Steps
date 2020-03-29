package com.cutlerdevelopment.footballsteps.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.cutlerdevelopment.footballsteps.Constants.Position;
import com.cutlerdevelopment.footballsteps.Models.OfflineGame;
import com.cutlerdevelopment.footballsteps.Models.OfflinePlayer;
import com.cutlerdevelopment.footballsteps.R;

public class OfflineCareerMainMenu extends AppCompatActivity {

    TextView firstNameField;
    TextView surnameField;
    TextView positionField;
    TextView favNameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_career_main_menu);

        firstNameField = findViewById(R.id.mainMenuFirstName);
        surnameField = findViewById(R.id.mainMenuSurname);
        positionField = findViewById(R.id.mainMenuPosition);
        favNameField = findViewById(R.id.mainMenuFavTeam);

        OfflinePlayer player = OfflinePlayer.getInstance();

        firstNameField.setText(player.getFirstName());
        surnameField.setText(player.getSurname());
        positionField.setText(Position.getPositionLongName(player.getPosition()));
        favNameField.setText(player.getFavTeam().getName());
    }
}

package com.cutlerdevelopment.footballsteps.Utils.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.cutlerdevelopment.footballsteps.Constants.Colour;
import com.cutlerdevelopment.footballsteps.R;

public class NewOfflineCareerDialogFragment extends DialogFragment {

    private boolean teamModeSelected;
    private boolean targetedModeSelected;

    private TextView teamPlayerInfoText;
    private TextView scaledTargetInfoText;

    private Button teamModeButton;
    private ImageView teamModeBackground;

    private Button playerModeButton;
    private ImageView playerModeBackground;

    private Button targetModeButton;
    private ImageView targetModeBackground;

    private Button scaledModeButton;
    private ImageView scaledModeBackground;

    private Button confirmButton;

    public interface NewOfflineCareerDialogListener {
        public void confirmCareerSettings(DialogFragment dialog, boolean teamModeSelected, boolean targetModeSelected);
    }

    NewOfflineCareerDialogListener listener;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        listener = (NewOfflineCareerDialogListener) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_offline_team, container, false);
        // Set transparent background and no title
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View newOfflineCareerView = inflater.inflate(R.layout.dialog_new_offline_career, null);
        teamPlayerInfoText = newOfflineCareerView.findViewById(R.id.teamPlayerInformationText);
        scaledTargetInfoText = newOfflineCareerView.findViewById(R.id.targetScaledInformationText);
        teamModeButton = newOfflineCareerView.findViewById(R.id.newOfflineCareerTeamButton);
        teamModeBackground = newOfflineCareerView.findViewById(R.id.teamModeBackground);
        playerModeButton = newOfflineCareerView.findViewById(R.id.newOffCareerPlayerButton);
        playerModeBackground = newOfflineCareerView.findViewById(R.id.playerModeBackground);
        targetModeButton = newOfflineCareerView.findViewById(R.id.newOfflineCareerTargetButton);
        targetModeBackground = newOfflineCareerView.findViewById(R.id.targetModeBackground);
        scaledModeButton = newOfflineCareerView.findViewById(R.id.newOfflineCareerScaledButton);
        scaledModeBackground = newOfflineCareerView.findViewById(R.id.scaledModeBackground);
        confirmButton = newOfflineCareerView.findViewById(R.id.newOffCareerConfirmButton);


        teamPlayerInfoText.setVisibility(View.GONE);
        scaledTargetInfoText.setVisibility(View.GONE);

        targetModeButton.setVisibility(View.GONE);
        scaledModeButton.setVisibility(View.GONE);
        targetModeBackground.setVisibility(View.GONE);
        scaledModeBackground.setVisibility(View.GONE);

        teamModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));
        playerModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));
        targetModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));
        scaledModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));

        confirmButton.setVisibility(View.INVISIBLE);

        teamModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamPlayerInfoText.setVisibility(View.VISIBLE);
                teamPlayerInfoText.setText(R.string.new_offline_career_team_mode_info);
                teamModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_SELECTED_BACKGROUND_COLOUR));
                playerModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));

                targetModeBackground.setVisibility(View.VISIBLE);
                scaledModeBackground.setVisibility(View.VISIBLE);
                targetModeButton.setVisibility(View.VISIBLE);
                scaledModeButton.setVisibility(View.VISIBLE);

                teamModeSelected = true;
            }
        });

        playerModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamPlayerInfoText.setVisibility(View.VISIBLE);
                teamPlayerInfoText.setText(R.string.new_offline_career_player_mode_info);
                playerModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_SELECTED_BACKGROUND_COLOUR));
                teamModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));

                targetModeBackground.setVisibility(View.VISIBLE);
                scaledModeBackground.setVisibility(View.VISIBLE);
                targetModeButton.setVisibility(View.VISIBLE);
                scaledModeButton.setVisibility(View.VISIBLE);

                teamModeSelected = false;
            }
        });

        targetModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaledTargetInfoText.setVisibility(View.VISIBLE);
                scaledTargetInfoText.setText(R.string.new_offline_career_target_mode_info);
                targetModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_SELECTED_BACKGROUND_COLOUR));
                scaledModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));

                confirmButton.setVisibility(View.VISIBLE);

                targetedModeSelected = true;
            }
        });

        scaledModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaledTargetInfoText.setVisibility(View.VISIBLE);
                scaledTargetInfoText.setText(R.string.new_offline_career_scaled_mode_info);
                scaledModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_SELECTED_BACKGROUND_COLOUR));
                targetModeBackground.setBackgroundColor(getResources().getColor(Colour.MODE_DEFAULT_BACKGROUND_COLOUR));

                confirmButton.setVisibility(View.VISIBLE);

                targetedModeSelected = false;
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
                listener.confirmCareerSettings(NewOfflineCareerDialogFragment.this, teamModeSelected, targetedModeSelected);
            }
        });


        builder.setView(newOfflineCareerView);

        return builder.create();

    }
}

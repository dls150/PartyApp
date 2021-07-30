package br.com.dls.party.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.dls.party.R;
import br.com.dls.party.constants.FimDeAnoConstants;
import br.com.dls.party.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.check_participate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                //salva a presença
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            } else {
                //salva a ausencia
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    private static class ViewHolder {
        CheckBox checkParticipate;
    }
}
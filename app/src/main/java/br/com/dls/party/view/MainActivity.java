package br.com.dls.party.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.dls.party.R;
import br.com.dls.party.constants.FimDeAnoConstants;
import br.com.dls.party.data.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);
        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        //concatena numero de dias + string dias
        String daysLeft = String.format("%s s%", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);

        //this.VerifyPresence()
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {
            // criando a intent para abrir a tela details
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder {
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }

    private int getDaysLeft() {
        //getInstance ?? a data de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        //ultimo dia do ano {1 at?? 365}
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;
    }

    private void verifyPresence(){
        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        if (presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao_confimado));
        } else if (presence.equals(FimDeAnoConstants.PRESENCE_KEY)) {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));
        } else {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao));
        }
    }
}
package moj.tvz.hr.kalkulatortopolovec;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.calculate) Button calculate;
    @BindView(R.id.result) TextView result;
    @BindView(R.id.spentFuel) EditText spentFuel;
    @BindView(R.id.distance) EditText distanceTraveled;
    @BindView(R.id.themeSelection) Switch theme;
    // Strings inside app
    @BindString(R.string.calculate) String calculateString;
    @BindString(R.string.fuel) String fuelString;
    @BindString(R.string.fuel_notice) String fuel_noticeString;
    @BindString(R.string.distance) String distanceString;
    @BindString(R.string.distance_notice) String distance_noticeString;
    @BindString(R.string.theme) String themeString;
    @BindString(R.string.fuel_error) String fuelErrorString;
    @BindString(R.string.distance_error) String distanceErrorString;
    @BindString(R.string.before) String before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Selecting strings from resources
        ((TextView) findViewById(R.id.themeSelection)).setText(themeString);

        ((TextView) findViewById(R.id.fuel)).setText(fuelString);
        ((TextView) findViewById(R.id.spentFuel)).setHint(fuel_noticeString);
        ((TextView) findViewById(R.id.km)).setText(distanceString);
        ((TextView) findViewById(R.id.distance)).setHint(distance_noticeString);

        ((Button) findViewById(R.id.calculate)).setText(calculateString);

    }

    private BigDecimal calculateFuelConsumption() {

        BigDecimal fuel, distance, consumption;

        fuel = new BigDecimal(spentFuel.getText().toString());
        distance = new BigDecimal(distanceTraveled.getText().toString());

        consumption = fuel.multiply(new BigDecimal("100")).divide(distance, 2, RoundingMode.HALF_UP);
        return consumption.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @OnClick(R.id.calculate)
    public void onCalculateViewClicked(View view) {
        // Removing errors if they were
        spentFuel.setError(null);
        distanceTraveled.setError(null);
        boolean ok = true;

        if (spentFuel.getText().toString().length() == 0) {
            spentFuel.setError(distanceErrorString);
            ok = false;
        }

        if (distanceTraveled.getText().toString().length() == 0) {
            distanceTraveled.setError(fuelErrorString);
            ok = false;
        }

        if (ok) {
            result.setText(before + " " + calculateFuelConsumption().toString() + " L/100km");
        }
    }

    @OnClick(R.id.themeSelection)
    public void onThemeViewClicked(View view) {

        if (theme.isChecked()) {
            findViewById(R.id.main).setBackgroundColor(Color.BLACK);
            theme.setTextColor(Color.WHITE);
            result.setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.fuel)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.spentFuel)).setHintTextColor(Color.WHITE);
            spentFuel.setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.km)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.distance)).setHintTextColor(Color.WHITE);
            distanceTraveled.setTextColor(Color.WHITE);
        } else {
            findViewById(R.id.main).setBackgroundColor(Color.WHITE);
            theme.setTextColor(Color.BLACK);
            result.setTextColor(Color.BLACK);
            ((TextView) findViewById(R.id.fuel)).setTextColor(Color.BLACK);
            ((TextView) findViewById(R.id.spentFuel)).setHintTextColor(Color.BLACK);
            spentFuel.setTextColor(Color.BLACK);
            ((TextView) findViewById(R.id.km)).setTextColor(Color.BLACK);
            ((TextView) findViewById(R.id.distance)).setHintTextColor(Color.BLACK);
            distanceTraveled.setTextColor(Color.BLACK);
        }

    }
}

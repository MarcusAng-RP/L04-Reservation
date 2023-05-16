package sg.edu.rp.c346.id22002222.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText groupSize;
    DatePicker dp;
    TimePicker tp;

    CheckBox checkSmoke;

    Button btnBook;

    Button btnReset;

    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etName);
        phone = findViewById(R.id.etPhone);
        groupSize = findViewById(R.id.etGroup);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        checkSmoke = findViewById(R.id.checkBoxSmoke);
        btnBook = findViewById(R.id.btnBook);
        btnReset = findViewById(R.id.btnReset);
        tvShow = findViewById(R.id.tvshow);

        dp.updateDate(2023, 5,1);
        tp.setIs24HourView(true);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().trim().length() != 0 && phone.getText().toString().trim().length() != 0 && groupSize.getText().toString().trim().length() != 0){
                    String message = "";
                    message += String.format("%s |", name.getText().toString());
                    message += String.format("%s |", phone.getText().toString());
                    message += String.format("%s |", groupSize.getText().toString());
                    message += String.format("%s |", dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear());
                    message += String.format("%s |", tp.getCurrentHour() + ":" + tp.getCurrentMinute());


                    if (checkSmoke.isChecked()) {
                        message += String.format("%s |", "Smoking");

                    } else {
                        message += String.format("%s |", "Non-smoking");
                    }

                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);

                    toast.show();
            }else{
                    Toast.makeText(getApplicationContext(), "Fields not completed!", Toast.LENGTH_LONG).show();
                }
        }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (tp.getCurrentHour() > 20 || tp.getCurrentHour() < 8){
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(00);

                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
                    System.out.println(currentDate);
                    LocalDate selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);

                    if (selectedDate.isBefore(currentDate)) {
                        dp.updateDate(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth() + 1);
                    }
                }
            });
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                phone.setText("");
                groupSize.setText("");
                checkSmoke.setChecked(false);
                dp.updateDate(2023, 5,1);
                tp.setIs24HourView(true);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);

            }
        });


    }
}
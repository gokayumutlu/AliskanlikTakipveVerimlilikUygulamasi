package g.com.atvu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class HabitAdd extends AppCompatActivity {


    Button saatSecBtn;
    TextView saatTv;
    Context context=this;
    Button iptal,save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_add);

        saatSecBtn=findViewById(R.id.timepickerbutton);
        saatTv=findViewById(R.id.timetv);
        iptal=findViewById(R.id.habit_add_esc_button);
        save=findViewById(R.id.habit_add_save_button);

        saatSecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar takvim=Calendar.getInstance();
                int saat=takvim.get(Calendar.HOUR_OF_DAY);
                int dakika=takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofday, int minute) {
                        saatTv.setText(hourofday+":"+minute);
                        Log.d("hour:"," "+hourofday);
                        Log.d("minute:"," "+minute);
                    }
                },saat,dakika,true);

                tpd.setButton(TimePickerDialog.BUTTON_POSITIVE,"Seç",tpd);
                tpd.setButton(TimePickerDialog.BUTTON_NEGATIVE,"İptal",tpd);
                tpd.show();
            }
        });

        iptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HabitAdd.this,MainActivity.class);
                startActivity(i);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(HabitAdd.this,details_habit.class);
                startActivity(in);
            }
        });

    }
}

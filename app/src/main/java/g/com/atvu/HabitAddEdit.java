package g.com.atvu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HabitAddEdit extends AppCompatActivity {


    Button saatSecBtn;
    TextView saatTv;
    Context context=this;
    Button iptal,save;
    EditText addTitleEt,addDescEt;
    public int hour2,minute2;
    String time;
    long interval;

    public static final String EXTRA_ID="g.com.atvu.EXTRA_ID";
    public static final String EXTRA_TITLE="g.com.atvu.EXTRA_TITLE";
    public static final String EXTRA_DESC="g.com.atvu.EXTRA_DESC";
    public static final String EXTRA_INTERVAL="g.com.atvu.EXTRA_INTERVAL";
    public static final String EXTRA_TIME="g.com.atvu.EXTRA_TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_add);

        saatSecBtn=findViewById(R.id.timepickerbutton);
        saatTv=findViewById(R.id.timetv);
        iptal=findViewById(R.id.habit_add_esc_button);
        save=findViewById(R.id.habit_add_save_button);
        addTitleEt=findViewById(R.id.add_habit_et_title);
        addDescEt=findViewById(R.id.add_habit_et_desc);

        getSupportActionBar();

        Intent intent=getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Hedef düzenle");
            addTitleEt.setText(intent.getStringExtra(EXTRA_TITLE));
            addDescEt.setText(intent.getStringExtra(EXTRA_DESC));
        }
        else{
            setTitle("Alışkanlık Ekle");
        }



        final String[] arraySpinner=new String[]{"Günde 1 defa","Günde 2 defa","Haftada 1 defa"};
        Spinner s=findViewById(R.id.spinnerTekrar);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("onItemSelected"," :"+arraySpinner[i]);
                //interval=arraySpinner[i];
                switch (i){
                    case 0: interval= 24*60*60; break;        //24 saat interval
                    case 1: interval=12*60*60; break;         //12 saat interval
                    case 2: interval=7*24*60*60; break;       //haftalık interval
                    default: interval= 1*60*60; Log.d("switch default","Default"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saatSecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar takvim=Calendar.getInstance();
                int saat=takvim.get(Calendar.HOUR_OF_DAY);
                int dakika=takvim.get(Calendar.MINUTE);

                TimePickerDialog tpd=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofday, int minute) {
                        takvim.set(Calendar.HOUR_OF_DAY,hourofday);
                        takvim.set(Calendar.MINUTE,minute);

                        SimpleDateFormat timeformat=new SimpleDateFormat("HH:mm");
                        time=timeformat.format(takvim.getTime());

                        saatTv.setText(hourofday+":"+minute);
                        hour2=hourofday;
                        minute2=minute;
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
                Intent i=new Intent(HabitAddEdit.this,MainActivity.class);
                startActivity(i);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHabit();
            }
        });

    }



    private void saveHabit(){
        String title=addTitleEt.getText().toString();
        String desc=addDescEt.getText().toString();
        //String sInterval=Long.toString(interval);

        if(title.trim().isEmpty() || desc.trim().isEmpty()){
            Log.d("title veya desc empty","empty title, desc");
            Toast.makeText(this,"İsim ve açıklama giriniz",Toast.LENGTH_LONG);
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESC,desc);
        data.putExtra(EXTRA_INTERVAL,interval);
        data.putExtra(EXTRA_TIME,time);

        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();


    }

    public void alarmManager(){
        Calendar calendar=Calendar.getInstance();

    }

}

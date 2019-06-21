package g.com.atvu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class details_habit extends AppCompatActivity {

    private HabitViewModel habitViewModel;
    TextView descTv,timeTv,tekrarTv;
    int habitId;
    String habitTitle,habitDesc;
    List<Habit> habits;
    //public static HabitDatabase hdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_habit);

        //hdb= Room.databaseBuilder(getApplicationContext(),HabitDatabase.class,"habit_database").build();

        Intent intent=getIntent();
        habitId=intent.getIntExtra(HabitAddEdit.EXTRA_ID,-1);
        habitTitle=intent.getStringExtra(HabitAddEdit.EXTRA_TITLE);
        habitDesc=intent.getStringExtra(HabitAddEdit.EXTRA_DESC);

        SharedPreferences sharedPreferences=this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("habitTitle",habitTitle);
        editor.putString("habitDesc",habitDesc);
        editor.apply();

        habitViewModel= ViewModelProviders.of(this).get(HabitViewModel.class);
        descTv=findViewById(R.id.habitdetaildesc);
        timeTv=findViewById(R.id.habitdescsaat);
        tekrarTv=findViewById(R.id.habitdesctekrartv);

        //habits=hdb.habitDao().getOneHabit(habitId);

        habits=habitViewModel.getOneHabit(habitId);
        descTv.setText(habitDesc);
        long interval=habits.get(0).getHabitInterval();
        if(interval==86400){
            tekrarTv.setText("Günde 1 defa");
        }
        else if(interval==43200){
            tekrarTv.setText("Günde 2 defa");
        }
        else if(interval==604800){
            tekrarTv.setText("Haftada 1 defa");
        }
        else{
            tekrarTv.setText("Error");
        }
        String time=habits.get(0).getHabitTime();
        String[] array=time.split("(?<=\\G.{2})");
        timeTv.setText(array[0]+array[1]);



        Log.d("Loggg333",habits.get(0).getHabitName());
    }
}

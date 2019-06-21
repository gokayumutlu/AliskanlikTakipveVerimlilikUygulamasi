package g.com.atvu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private HabitViewModel habitViewModel;
    public static final int ADD_HABIT_REQUEST=1;
    public static final int EDIT_HABIT_REQUEST=2;
    long interval;
    public String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final HabitAdapter habitAdapter=new HabitAdapter();
        recyclerView.setAdapter(habitAdapter);

        habitViewModel= ViewModelProviders.of(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits().observe(this, new Observer<List<Habit>>() {
            @Override
            public void onChanged(List<Habit> habits) {
                //update recyclerview
                //Toast.makeText(MainActivity.this,"onchanged",Toast.LENGTH_LONG).show();
                Log.d("Loggg","onchanged");
                habitAdapter.setHabits(habits);
                //habitAdapter.notifyDataSetChanged();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                habitViewModel.delete( habitAdapter.getHabitAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this,"Hedef silindi",Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        habitAdapter.setOnItemLongClickListener(new HabitAdapter.OnItemLongClickListener() {
            @Override
            public void onItemClick(Habit habit) {
                Intent intent=new Intent(MainActivity.this, HabitAddEdit.class);
                intent.putExtra(HabitAddEdit.EXTRA_ID,habit.getHabitId());
                intent.putExtra(HabitAddEdit.EXTRA_TITLE,habit.getHabitName());
                intent.putExtra(HabitAddEdit.EXTRA_DESC,habit.getHabitDesc());
                startActivityForResult(intent,EDIT_HABIT_REQUEST);

            }
        });

        habitAdapter.setOnItemSClickListener(new HabitAdapter.onItemSClickListener() {
            @Override
            public void onItemClick(Habit habit) {
                Intent intent=new Intent(MainActivity.this,details_habit.class);
                intent.putExtra(HabitAddEdit.EXTRA_ID,habit.getHabitId());
                intent.putExtra(HabitAddEdit.EXTRA_TITLE,habit.getHabitName());
                intent.putExtra(HabitAddEdit.EXTRA_DESC,habit.getHabitDesc());
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent=new Intent(MainActivity.this, HabitAddEdit.class);
                startActivityForResult(intent,ADD_HABIT_REQUEST); break;

            case R.id.delete_all_habits:
                habitViewModel.deleteAll();
                Log.d("Loggg","Bütün hedefler silindi");
                Toast.makeText(this,"Bütün hedefler silindi",Toast.LENGTH_LONG).show(); break;

            default: return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_HABIT_REQUEST && resultCode==RESULT_OK){
            String title=data.getStringExtra(HabitAddEdit.EXTRA_TITLE);
            String desc=data.getStringExtra(HabitAddEdit.EXTRA_DESC);
            long sInterval=data.getLongExtra(HabitAddEdit.EXTRA_INTERVAL,1);
            time=data.getStringExtra(HabitAddEdit.EXTRA_TIME);
            alarm();
            //interval=Long.valueOf(sInterval);
            //HabitMeta habitMeta=new HabitMeta(0,0,1561057800,86400,0);

            Habit habit=new Habit(0,12,title,desc,1561089600,sInterval,0,time);
            habitViewModel.insert(habit);

            Log.d("habit_saved","habit saved");
            Toast.makeText(this,"Hedef kaydedildi",Toast.LENGTH_LONG).show();

        }
        else if(requestCode==EDIT_HABIT_REQUEST && resultCode==RESULT_OK){
            int id=data.getIntExtra(HabitAddEdit.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(this,"habit error",Toast.LENGTH_SHORT).show();
                return;
            }

            String title=data.getStringExtra(HabitAddEdit.EXTRA_TITLE);
            String desc=data.getStringExtra(HabitAddEdit.EXTRA_DESC);
            long sInterval=data.getLongExtra(HabitAddEdit.EXTRA_INTERVAL,1);
            String time=data.getStringExtra(HabitAddEdit.EXTRA_TIME);
            alarm();
            Habit habit=new Habit(id,17,title,desc,1561099560,sInterval,0,time);
            habit.setHabitId(id);
            habitViewModel.update(habit);
            Toast.makeText(this,"Hedef güncellendi",Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("habit_not_saved","habit not saved");
            Toast.makeText(this, "Habit Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void alarm(){
        AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
        String[] array=time.split(":");
        int hour=Integer.parseInt(array[0]);
        int minute=Integer.parseInt(array[1]);
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
        }
    }
}

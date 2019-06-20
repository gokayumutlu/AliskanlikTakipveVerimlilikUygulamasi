package g.com.atvu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private HabitViewModel habitViewModel;
    public static final int ADD_HABIT_REQUEST=1;
    //long interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final HabitAdapter habitAdapter=new HabitAdapter();
        recyclerView.setAdapter(habitAdapter);

        habitViewModel= ViewModelProviders.of(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits(this,new Observer<List<Habit>>(){

            @Override
            public void onChanged(List<Habit> habits) {
                //update recyclerview
                Toast.makeText(MainActivity.this,"onchanged",Toast.LENGTH_LONG).show();
                habitAdapter.setHabits(habits);
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
                Intent intent=new Intent(MainActivity.this,HabitAdd.class);
                startActivityForResult(intent,ADD_HABIT_REQUEST);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_HABIT_REQUEST && resultCode==RESULT_OK){
            String title=data.getStringExtra(HabitAdd.EXTRA_TITLE);
            String desc=data.getStringExtra(HabitAdd.EXTRA_DESC);
            long sInterval=data.getLongExtra(HabitAdd.EXTRA_INTERVAL,1);
            String time=data.getStringExtra(HabitAdd.EXTRA_TIME);

            //interval=Long.valueOf(sInterval);

            Habit habit=new Habit(title,desc);
            HabitMeta habitMeta=new HabitMeta()

        }
    }
}

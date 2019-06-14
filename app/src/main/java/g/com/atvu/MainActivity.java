package g.com.atvu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private HabitViewModel habitViewModel;

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
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

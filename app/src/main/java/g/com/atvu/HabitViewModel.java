package g.com.atvu;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class HabitViewModel extends AndroidViewModel {

    private HabitRepository mRepository;

    private LiveData<List<Habit>> mAllHabits;

    public HabitViewModel(Application application){
        super(application);
        mRepository=new HabitRepository(application);
        mAllHabits=mRepository.getAllHabits();
    }

    LiveData<List<Habit>> getAllHabits(){
        return mAllHabits;
    }

    public void insert(Habit habit){
        mRepository.insert(habit);
    }



}

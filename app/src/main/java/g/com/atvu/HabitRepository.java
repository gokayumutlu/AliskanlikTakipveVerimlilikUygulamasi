package g.com.atvu;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class HabitRepository {

    private HabitDao mHabitDao;
    private LiveData<List<Habit>> mAllHabits;

    public HabitRepository(Application application){
        HabitDatabase db= HabitDatabase.getDatabase(application);
        mHabitDao =db.hedefDao();
        mAllHabits= mHabitDao.allHabits();
    }


}

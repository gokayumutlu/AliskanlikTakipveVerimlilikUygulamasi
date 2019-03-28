package g.com.atvu;

import android.app.Application;
import android.os.AsyncTask;

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

    LiveData<List<Habit>> getAllHabits(){
        return mAllHabits;
    }

    public void insert(Habit habit){
        new insertAsync(mHabitDao).execute(habit);
    }

    public void update(Habit habit){
        new updateAsync(mHabitDao).execute(habit);
    }

    public void deleteAll(){
        new deleteAllAsync(mHabitDao).execute();
    }

    public void deleteOne(Habit habit){
        new deleteOneAsync(mHabitDao).execute(habit);
    }




    private static class insertAsync extends AsyncTask<Habit,Void,Void> {

        private HabitDao mAsyncDao;

        insertAsync(HabitDao dao){
            mAsyncDao=dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            mAsyncDao.insert(habits[0]);
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<Habit,Void,Void>{

        private HabitDao mAsyncDao;

        updateAsync(HabitDao dao){
            mAsyncDao=dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            mAsyncDao.update(habits[0]);
            return null;
        }
    }

    private static class deleteAllAsync extends AsyncTask<Habit,Void,Void>{

        private HabitDao mAsyncDao;

        deleteAllAsync(HabitDao dao){
            mAsyncDao=dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            mAsyncDao.deleteAll();
            return null;
        }
    }

    private static class deleteOneAsync extends AsyncTask<Habit,Void,Void>{

        private HabitDao mAsyncDao;

        deleteOneAsync(HabitDao dao){
            mAsyncDao=dao;
        }

        @Override
        protected Void doInBackground(Habit... habits) {
            mAsyncDao.deleteOne(habits[0]);
            return null;
        }
    }


}

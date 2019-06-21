package g.com.atvu;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Habit.class}, version = 1, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class HabitDatabase extends RoomDatabase {

    public abstract HabitDao habitDao();

    private static volatile HabitDatabase INSTANCE;

    static HabitDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HabitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , HabitDatabase.class, "habit_databse")
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
        private HabitDao hbDao;
        private PopulateDbAsync(HabitDatabase habitDatabase){
            hbDao=habitDatabase.habitDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*
            hbDao.insert(new Habit(5,13,"Habit1","Description1",1561093200,86400,0,"0500"));
            hbDao.insert(new Habit(6,14,"Habit2","Description2",1561093380,86400,0,"0503"));
            hbDao.insert(new Habit(7,15,"Habit3","Description3",1561093440,43200,1,"0504"));
            */
            return null;
        }
    }

}

package g.com.atvu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Habit.class, HabitMeta.class}, version = 1, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class HabitDatabase extends RoomDatabase {

    public abstract HabitDao hedefDao();

    private static volatile HabitDatabase INSTANCE;

    static HabitDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HabitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , HabitDatabase.class, "habit_databse")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

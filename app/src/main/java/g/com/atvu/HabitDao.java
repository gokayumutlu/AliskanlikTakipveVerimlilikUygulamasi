package g.com.atvu;

import java.util.List;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface HabitDao {

    @Insert
    void insert(Habit habit);

    //@Delete
    //void deleteAll();

    @Delete
    void deleteOne(Habit habit);

    @Update
    void update(Habit habit);

    @Query("SELECT * FROM habit ORDER BY habit_id DESC")
    LiveData<List<Habit>> allHabits();

    @Query("DELETE FROM habit")
    void deleteAll();

    @Query("SELECT * FROM habit WHERE habit_id= :id")
    List<Habit> getOneHabit(int id);



}

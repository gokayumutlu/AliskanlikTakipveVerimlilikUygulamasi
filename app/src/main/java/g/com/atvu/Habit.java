package g.com.atvu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "habit")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "habit_id")
    private int habitId;

    @ColumnInfo(name="habit_key")
    private int habitKey;

    @ColumnInfo(name = "habit_name")
    private String habitName;

    @ColumnInfo(name = "habit_description")
    private String habitDesc;

    @ColumnInfo(name="habit_start")
    private long habitStart;

    @ColumnInfo(name="habit_interval")
    private long habitInterval;

    @ColumnInfo(name = "habit_done")
    private int habitDone;

    @ColumnInfo(name="habit_time")
    private String habitTime;

    public Habit(int habitId, int habitKey, String habitName, String habitDesc, long habitStart, long habitInterval, int habitDone, String habitTime) {
        this.habitId = habitId;
        this.habitName = habitName;
        this.habitDesc = habitDesc;
        this.habitStart = habitStart;
        this.habitInterval = habitInterval;
        this.habitDone = habitDone;
        this.habitTime=habitTime;
        this.habitKey=habitKey;
    }

    public int getHabitId() {
        return habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public String getHabitDesc() {
        return habitDesc;
    }

    public long getHabitStart() {
        return habitStart;
    }

    public long getHabitInterval() {
        return habitInterval;
    }

    public int getHabitDone() {
        return habitDone;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public void setHabitDesc(String habitDesc) {
        this.habitDesc = habitDesc;
    }

    public void setHabitStart(long habitStart) {
        this.habitStart = habitStart;
    }

    public void setHabitInterval(long habitInterval) {
        this.habitInterval = habitInterval;
    }

    public void setHabitDone(int habitDone) {
        this.habitDone = habitDone;
    }

    public String getHabitTime() {
        return habitTime;
    }

    public void setHabitTime(String habitTime) {
        this.habitTime = habitTime;
    }

    public int getHabitKey() {
        return habitKey;
    }

    public void setHabitKey(int habitKey) {
        this.habitKey = habitKey;
    }
}

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

    @ColumnInfo(name = "habit_name")
    private String habitName;

    @ColumnInfo(name = "habit_description")
    private String habitDesc;

    @ColumnInfo(name="habit_started")
    private String habitStarted;

    @ColumnInfo(name="habit_ended")
    private String habitEnded;

    @ColumnInfo(name = "habit_is_completed")
    private Boolean habitIsCompleted;

    public int getHabitId() {
        return habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public String getHabitDesc() {
        return habitDesc;
    }

    public String getHabitStarted() {
        return habitStarted;
    }

    public String getHabitEnded() {
        return habitEnded;
    }

    public Boolean getHabitIsCompleted() {
        return habitIsCompleted;
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

    public void setHabitStarted(String habitStarted) {
        this.habitStarted = habitStarted;
    }

    public void setHabitEnded(String habitEnded) {
        this.habitEnded = habitEnded;
    }

    public void setHabitIsCompleted(Boolean habitIsCompleted) {
        this.habitIsCompleted = habitIsCompleted;
    }
}

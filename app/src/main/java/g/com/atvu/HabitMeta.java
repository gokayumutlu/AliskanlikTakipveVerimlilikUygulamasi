package g.com.atvu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/*
 * ,
        foreignKeys = @ForeignKey(onDelete = CASCADE,entity = Habit.class,
                parentColumns = "habit_id",
                childColumns = "habit_id"), indices = {@Index("habit_id")}
  *
  * */

@Entity(tableName = "habit_meta")
public class HabitMeta {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int habitMetaId;

    @ColumnInfo(name = "habit_id")
    private int habitId;

    @ColumnInfo(name = "repeat_start")
    private long repeatStart;

    @ColumnInfo(name = "repeat_interval")
    private long repeatInterval;

    @ColumnInfo(name="habit_done")
    private int habitDone;

    public HabitMeta(int habitMetaId, int habitId, long repeatStart, long repeatInterval, int habitDone) {
        this.habitMetaId = habitMetaId;
        this.habitId = habitId;
        this.repeatStart = repeatStart;
        this.repeatInterval = repeatInterval;
        this.habitDone = habitDone;
    }

    public int getHabitMetaId() {
        return habitMetaId;
    }

    public void setHabitMetaId(int habitMetaId) {
        this.habitMetaId = habitMetaId;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public long getRepeatStart() {
        return repeatStart;
    }

    public void setRepeatStart(long repeatStart) {
        this.repeatStart = repeatStart;
    }

    public long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public int getHabitDone() {
        return habitDone;
    }

    public void setHabitDone(int habitDone) {
        this.habitDone = habitDone;
    }
}

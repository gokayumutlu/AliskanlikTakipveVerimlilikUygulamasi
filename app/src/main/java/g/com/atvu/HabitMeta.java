package g.com.atvu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "habit_meta",
        foreignKeys = @ForeignKey(onDelete = CASCADE,entity = Habit.class,parentColumns = "habit_id",childColumns = "habit_id"),indices = {@Index("habit_id")})
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
}

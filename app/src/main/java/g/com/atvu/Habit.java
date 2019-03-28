package g.com.atvu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "habit")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "habit_id")
    public int habitId;

    @ColumnInfo(name = "habit_name")
    private String habit_name;

    @ColumnInfo(name = "habit_description")
    private String habitDesc;

    @ColumnInfo(name = "habit_repeat")
    private String habitRepeat;

    @ColumnInfo(name = "habit_remind")
    private String habitRemind;

    @ColumnInfo(name = "habit_is_completed")
    private Boolean habitIsCompleted;

}

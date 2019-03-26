package g.com.atvu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface HedefDao {

    @Insert
    void insert(Hedef hedef);

    @Delete
    void deleteAll();

    @Update
    void update(Hedef hedef);

    //@Query()
    //void deleteOne();



}

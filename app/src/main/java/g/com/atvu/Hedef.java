package g.com.atvu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "hedef")
public class Hedef {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "hedef_id")
    public int hedefId;

    @ColumnInfo(name = "hedef_adi")
    private String hedefAd;

    @ColumnInfo(name = "hedef_aciklama")
    private String hedefAciklama;

    @ColumnInfo(name = "hedef_tekrar")
    private String hedefTekrar;

    @ColumnInfo(name = "hedef_hatirlatma")
    private String hedefHatirlatma;

    @ColumnInfo(name = "hedef_tamamlandi")
    private Boolean hedefTamamlandi;

}

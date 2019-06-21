package g.com.atvu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitHolder> {

    private OnItemLongClickListener listener;
    private List<Habit> habits=new ArrayList<>();

    @NonNull
    @Override
    public HabitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item2,parent,false);
        return new HabitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitHolder holder, int position) {
        if(habits!=null){
            Habit currentHabit=habits.get(position);
            holder.habitTitle.setText(currentHabit.getHabitName());
            holder.habitDesc.setText(currentHabit.getHabitDesc());
            //holder.habitId.setText(String.valuesOf(currentHabit.getId)); // string yerine int istenirse bu şekilde
            //holder.imageView1.setImageResource(R.drawable.checked);
            //holder.imageView2.setImageResource(R.drawable.checked);
            //holder.imageView3.setImageResource(R.drawable.error);
            //holder.imageView4.setImageResource(R.drawable.checked);

            if(currentHabit.getHabitDone()==0){
                holder.habitImage.setImageResource(R.drawable.error32);
            }
            else if(currentHabit.getHabitDone()==1){
                holder.habitImage.setImageResource(R.drawable.checked32);
            }
            else{
                Log.d("Loggg","currentHabit.getHabitDone() 0 veya 1 değil");
            }
        }
        else{
            Log.d("Loggg","habits = null");
            holder.habitTitle.setText("No habit");
        }


    }

    @Override
    public int getItemCount() {
        if(habits!=null){
            return habits.size();
        }
        else{
            return 0;
        }

    }

    public void setHabits(List<Habit> habits){
        this.habits=habits;
        notifyDataSetChanged(); //will be replaced
    }

    public Habit getHabitAt(int position){
        return habits.get(position);
    }

    class HabitHolder extends RecyclerView.ViewHolder{
        //private TextView habitName;
        //private ImageView imageView1;
        //private ImageView imageView2;
        //private ImageView imageView3;
        //private ImageView imageView4;

        private TextView habitTitle;
        private TextView habitDesc;
        private ImageView habitImage;

        public HabitHolder(@NonNull View itemView) {
            super(itemView);

            //habitName=itemView.findViewById(R.id.habitName);
            //imageView1=itemView.findViewById(R.id.lastoneday_imageview);
            //imageView2=itemView.findViewById(R.id.lasttwoday_imageview);
            //imageView3=itemView.findViewById(R.id.lastthreeday_imageview);
            //imageView4=itemView.findViewById(R.id.lastfourday_imageview);

            habitTitle=itemView.findViewById(R.id.recyclerview_title_item);
            habitDesc=itemView.findViewById(R.id.recyclerview_desc_item);
            habitImage=itemView.findViewById(R.id.recyclerview_image_item);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(habits.get(position));
                    }
                    return true;
                }
            });

        }
    }


    public interface OnItemLongClickListener{
        void onItemClick(Habit habit);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.listener=listener;
    }

}

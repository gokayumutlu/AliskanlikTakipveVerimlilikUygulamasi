package g.com.atvu;

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


    private List<Habit> habits=new ArrayList<>();

    @NonNull
    @Override
    public HabitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new HabitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitHolder holder, int position) {
        Habit currentHabit=habits.get(position);
        holder.habitName.setText(currentHabit.getHabitName());
        holder.imageView1.setImageResource(R.drawable.checked);
        holder.imageView2.setImageResource(R.drawable.checked);
        holder.imageView3.setImageResource(R.drawable.error);
        holder.imageView4.setImageResource(R.drawable.checked);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public void setHabits(List<Habit> habits){
        this.habits=habits;
        notifyDataSetChanged(); //will be replaced
    }

    class HabitHolder extends RecyclerView.ViewHolder{
        private TextView habitName;
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;
        private ImageView imageView4;

        public HabitHolder(@NonNull View itemView) {
            super(itemView);

            habitName=itemView.findViewById(R.id.habitName);
            imageView1=itemView.findViewById(R.id.lastoneday_imageview);
            imageView2=itemView.findViewById(R.id.lasttwoday_imageview);
            imageView3=itemView.findViewById(R.id.lastthreeday_imageview);
            imageView4=itemView.findViewById(R.id.lastfourday_imageview);

        }
    }


}

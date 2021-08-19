package npp.edu.sg.madpractical2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<User> usersLists;
    private RecyclerViewClickListener listener;

    public recyclerAdapter(ArrayList<User> usersLists, RecyclerViewClickListener listener){
        this.usersLists = usersLists;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView Image;
        private TextView nameTxt;
        private TextView descTxt;

        public  MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.rTxt);
            descTxt = view.findViewById(R.id.rDesc);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = usersLists.get(position).getName();
        String desc = usersLists.get(position).getDescription();
        holder.nameTxt.setText(name);
        holder.descTxt.setText(desc);

    }

    @Override
    public int getItemCount() {
        return usersLists.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}

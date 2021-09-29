package com.erickmxav.dogapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erickmxav.dogapi.R;
import com.erickmxav.dogapi.model.Dog;

import java.util.List;

public class AdapterDog extends RecyclerView.Adapter<AdapterDog.MyViewHolder> {

    private List<Dog> dogs;
    Context context;

    public AdapterDog(List<Dog> dogs, Context context) {
        this.dogs = dogs;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDog.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dog, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDog.MyViewHolder holder, int position) {

        Dog dog = dogs.get(position);
        holder.breed.setText(dog.getMessage());
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

    /*public interface ItemClickListener{
    void onItemClick(Dog dog);
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView breed;

        public MyViewHolder(View itemView) {
            super(itemView);

            breed = itemView.findViewById(R.id.adapterBreed);
        }
    }
}

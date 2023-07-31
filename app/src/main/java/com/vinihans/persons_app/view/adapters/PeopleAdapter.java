package com.vinihans.persons_app.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinihans.persons_app.Application;
import com.vinihans.persons_app.R;
import com.vinihans.persons_app.model.people.People;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{


    private List<People> peopleList;

    public PeopleAdapter(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people,parent,false);
        return new PeopleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        People person = peopleList.get(position);
        holder.nameField.setText(person.getNome());
        holder.telefoneField.setText(person.getTelefone());
        holder.enderecoField.setText(person.getEndereco().getRua() +", "+ person.getEndereco().getBairro()+", "+person.getEndereco().getBairro());
        holder.nameField.setOnClickListener(view -> Application.apiService.delete(person.getId()));
    }

    @Override
    public int getItemCount() {
        if (peopleList == null)
            return 0;
        return peopleList.size();
    }


    public static class PeopleViewHolder extends RecyclerView.ViewHolder{
        public TextView nameField;
        public TextView telefoneField;
        public TextView enderecoField;


        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameField = itemView.findViewById(R.id.textViewName);
            this.telefoneField = itemView.findViewById(R.id.textViewTelefone);
            this.enderecoField = itemView.findViewById(R.id.textViewEndereco);
        }
    }
}

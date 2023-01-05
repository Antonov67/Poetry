package com.example.poetry.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetry.Model.PoemsByAuthor;
import com.example.poetry.R;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder>{
    private List<PoemsByAuthor> poems;

    public OnBoardingAdapter(List<PoemsByAuthor> poems) {
        this.poems = poems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(poems.get(position));
    }

    @Override
    public int getItemCount() {
        return poems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private TextView textPoem;


        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.poem_title);
            textPoem = itemView.findViewById(R.id.poem_text);

        }
        void setOnboardingData(PoemsByAuthor poemsByAuthor){
            textTitle.setText(poemsByAuthor.getPoemTitle());
            textPoem.setText(poemsByAuthor.getPoemText());

        }
    }
}
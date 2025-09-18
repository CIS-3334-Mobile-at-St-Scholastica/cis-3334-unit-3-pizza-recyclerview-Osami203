package cis3334.unit5_pizzarycycle;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView textViewPizzaDescription;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewPizzaDescription = itemView.findViewById(R.id.textViewPizzaDescription);
    }

    public void bind(Pizza pizza) {
        if (pizza != null) {
            textViewPizzaDescription.setText(pizza.toString());
        }
    }
}
package cis3334.unit5_pizzarycycle;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderApadter extends RecyclerView.Adapter<OrderViewHolder>{

    Application application;
    MainViewModel mainViewModel;
    List<Pizza> pizzas = new ArrayList<>();

    public OrderApadter(Application application, MainViewModel mainViewModel){
        this.application = application;
        this.mainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        if (pizzas != null && !pizzas.isEmpty()) {
            Pizza currentPizza = pizzas.get(position);
            holder.bind(currentPizza);
        }

    }

    @Override
    public int getItemCount() {

        return mainViewModel.getAllOrder().getValue().size();
    }
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        notifyDataSetChanged();
    }
}

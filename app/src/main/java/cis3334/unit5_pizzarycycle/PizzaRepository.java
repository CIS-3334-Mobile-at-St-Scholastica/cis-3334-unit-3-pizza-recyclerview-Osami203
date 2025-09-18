package cis3334.unit5_pizzarycycle;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PizzaRepository {
    public static final Double DELIVERY_PRICE = 2.50;
    private final PizzaDao pizzaDao;
    private LiveData<List<Pizza>> pizzasInOrder;          // list of pizzas ordered so far
    private boolean delivery = false;                     // true if customer wants order delivered

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    PizzaRepository(Application application) {
        PizzaDatabase db = PizzaDatabase.getDatabase(application);
        pizzaDao = db.pizzaDao();
        pizzasInOrder = pizzaDao.getAll();
    }

    /***
     * Add an pizza to the order
     * @return the description of the pizza is returned if needed
     */
    public void OrderPizza(Pizza newPizza){
        // ROOM -- Add insert into database
        PizzaDatabase.databaseWriteExecutor.execute(() -> {
            pizzaDao.insert(newPizza);
            //Log.d ("CIS 3334", newPizza.toString());
        });
        //pizzasInOrder.add(newPizza);
        //return newPizza.toString();             // return a description of what was ordered
        return;
    }

    /***
     * Retrieve the full order
     * @return a list of pizzas, each one describe by a single string
     */
    public LiveData<List<Pizza>> getAllOrder() {
        pizzasInOrder = pizzaDao.getAll();
        return pizzasInOrder;
    }

    /***
     * Calculate the total bill for this order
     * @return total bill as Double
     */
    public Double getTotalBill(List<Pizza> pizzas) {
        Double total = 0.0;
        if (pizzas != null) {
            for (Pizza p : pizzas) {
                total += p.getPrice();
            }
        }
        if (delivery) {
            total += DELIVERY_PRICE;
        }
        return total;
    }

    public void setDelivery(boolean deliver) {
        this.delivery = deliver;
    }

    public boolean getDelivery() {
        return delivery;
    }
}

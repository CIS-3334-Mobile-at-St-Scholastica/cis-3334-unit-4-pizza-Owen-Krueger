package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    TextView txtPizzasOrdered;
    Spinner spinnerToppings;
    PizzaOrderInterface pizzaOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up our radio buttons
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        // Set up the Check Boxes
        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        // Set up the TextViews
        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        txtPizzasOrdered = (TextView) findViewById(R.id.textViewPizzasOrdered);
        // Set up the Spinner
        spinnerToppings = (Spinner) findViewById(R.id.spinnerToppings);

        //Pizza order
        pizzaOrder = new PizzaOrder(this);

        //Get prices from Pizza Order object
        rbSmall.append(" -- Price: $" + pizzaOrder.getPrice(Pizza.pizzaSize.SMALL).toString());
        rbMedium.append(" -- Price: $" + pizzaOrder.getPrice(Pizza.pizzaSize.MEDIUM).toString());
        rbLarge.append(" -- Price: $" + pizzaOrder.getPrice(Pizza.pizzaSize.LARGE).toString());

    }

    @Override
    public void updateOrderStatusInView(String orderStatus) {

        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        // ****** For the Assignment, students need to add code here to get information from the UI widgets...

        String orderDescription = "No orders yet";

        // ****** For the Practice Activity, students need to call to OrderPizza here
        //orderDescription = pizzaOrder.OrderPizza("Peperoni", "large", false  );
        String size;
        String topping;
        boolean xtraCheese;
        boolean delivery;

        //Get size by radio button
        if(rbSmall.isChecked()){
            size="small";
        }
        else if(rbMedium.isChecked()){
            size="medium";
        }
        else {
            size="large";
        }

        //Get toppings, extra cheese and delivery from UI
        topping = spinnerToppings.getSelectedItem().toString();
        xtraCheese = chkbxCheese.isChecked();
        delivery = chkbxDelivery.isChecked();

        //Make order with above variables
        orderDescription = pizzaOrder.OrderPizza(topping, size, xtraCheese);

        //If delivery
        pizzaOrder.setDelivery(delivery);

        txtTotal.setText(getResources().getString(R.string.total) + " $" + pizzaOrder.getTotalBill().toString());
        // ****** For the Assignment, students will modify the order to fit the type of pizza the user selects using the UI widgets

        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        // add this pizza to the textview the lists the pizzas
        txtPizzasOrdered.append(orderDescription+"\n");

    }
}

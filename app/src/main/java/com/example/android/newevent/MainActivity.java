package com.example.android.newevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    final static String EVENT_IDS = "http://10.0.2.2/event/get_all_eventID.php";
    final static String GET_ITEMS ="http://10.0.2.2/event/get_all_eventItems.php";
    final static String GET_CUSTOMER ="http://10.0.2.2/event/get_all_customerDetails.php";
    private TextView totalPrice;
    private Button beerBtn;
    private Button redWineBtn;
    private Button whiteWineBtn;
    private Button orangeJucieBtn;
    private Button appleJuiceBtn;
    private Button cranberryBtn;
    private Button champagneBtn;
    private Button menu1Btn;
    private Button menu2Btn;
    private Button menu3Btn;
    private Button menu4Btn;
    private Button fruitJuiceBtn;
    private Button signatureBtn;
    private TextView info;
    final static List<String> eventID = new ArrayList<String>();
    JsonRequestque helper = JsonRequestque.getInstance();
    JSONObject event;
    String customerD = "not here";
    Map<String,Event> eventMap = new TreeMap<String,Event>();
    Item menu1 = new Item("Menu1",50.00,0);
    Item menu2 = new Item("Menu2",120,0);
    Item menu3 = new Item("Menu3",180,0);
    Item menu4 = new Item ("Menu4",250,0);
    Item beer = new Item("Beer",3.50,0);
    Item redWine = new Item("Red Wine", 5.50,0);
    Item whiteWine = new Item("White Wine", 5.50,0);
    Item champagne = new Item("Champagne",30.00,0);
    Item orangeJuice = new Item("Orange Juice", 2.00,0);
    Item appleJuice = new Item("Apple Juice",2.00,0);
    Item cranberryJuice = new Item("Cranberry Jucie",2.00,0);
    Item fruitJuice = new Item("Fruit Juice",2.00,0);
    private ListView myListView;
    Map<String,Item> itemMap = new TreeMap<String, Item>();
    ArrayList<Item>itemList = new ArrayList<Item>();
    ArrayList<String>itemDetails = new ArrayList<String>();
    private double total;
    DecimalFormat df = new DecimalFormat("0.00##");
    String eventIDString;

     ArrayAdapter<Item> itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //postRequest();

        loadEventIDs();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myListView = (ListView) findViewById(R.id.itemListView);
        itemMap.put("Menu 1", menu1);
        itemMap.put("Menu 2", menu2);
        itemMap.put("Menu 3", menu3);
        itemMap.put("Menu 4", menu4);
        itemMap.put("Beer", beer);
        itemMap.put("Red Wine", redWine);
        itemMap.put("White Wine", whiteWine);
        itemMap.put("Champagne", champagne);
        itemMap.put("Orange Juice", orangeJuice);
        itemMap.put("Apple Juice", appleJuice);
        itemMap.put("Cranberry Juice", cranberryJuice);
        itemMap.put("Fruit Juice", fruitJuice);
        beerBtn = (Button) findViewById(R.id.forthBtn);
        whiteWineBtn = (Button) findViewById(R.id.firstBtn);
        redWineBtn = (Button) findViewById(R.id.secondBtn);
        champagneBtn = (Button) findViewById(R.id.thirdBtn);
        orangeJucieBtn = (Button) findViewById(R.id.fifthBtn);
        appleJuiceBtn = (Button) findViewById(R.id.sixthBtn);
        cranberryBtn = (Button) findViewById(R.id.seventhBtn);
        signatureBtn = (Button) findViewById(R.id.ninteenthBtn);
        menu1Btn = (Button) findViewById(R.id.ninthBtn);
        menu2Btn = (Button) findViewById(R.id.tenthBtn);
        menu3Btn = (Button) findViewById(R.id.eleventhBtn);
        menu4Btn = (Button) findViewById(R.id.twelthBtn);
        fruitJuiceBtn = (Button) findViewById(R.id.eighthBtn);

        totalPrice = (TextView) findViewById(R.id.totalPrice);
        info = (TextView) findViewById(R.id.infoTxt);
        itemsAdapter =
                new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1,itemList);

        myListView.setAdapter(itemsAdapter);

        whiteWineBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(whiteWine)) {
                    whiteWine.setQuantity(whiteWine.getQuantity() + 1);
                } else {
                    itemList.add(whiteWine);
                    whiteWine.setQuantity(whiteWine.getQuantity() + 1);
                }
                addTotal(whiteWine.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });

        redWineBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(itemList.contains(redWine)) {
                    redWine.setQuantity(redWine.getQuantity()+1);
                }
                else{
                    itemList.add(redWine);
                    redWine.setQuantity(redWine.getQuantity()+1);
                }
                addTotal(redWine.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        beerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(itemList.contains(beer)) {
                    beer.setQuantity(beer.getQuantity() + 1);
                }
                else{
                    itemList.add(beer);
                    beer.setQuantity(beer.getQuantity()+1);
                }
                addTotal(beer.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        orangeJucieBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(itemList.contains(orangeJuice)) {
                    orangeJuice.setQuantity(orangeJuice.getQuantity()+1);
                }
                else{
                    itemList.add(orangeJuice);
                    orangeJuice.setQuantity(orangeJuice.getQuantity()+1);
                }
                addTotal(orangeJuice.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        appleJuiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(appleJuice)) {
                    appleJuice.setQuantity(appleJuice.getQuantity() + 1);
                } else {
                    itemList.add(appleJuice);
                    appleJuice.setQuantity(appleJuice.getQuantity() + 1);
                }
                addTotal(appleJuice.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        champagneBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(champagne)) {
                    champagne.setQuantity(champagne.getQuantity() + 1);
                } else {
                    itemList.add(champagne);
                    champagne.setQuantity(champagne.getQuantity() + 1);
                }
                addTotal(champagne.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        menu1Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(menu1)) {
                    menu1.setQuantity(menu1.getQuantity() + 1);
                } else {
                    itemList.add(menu1);
                    menu1.setQuantity(menu1.getQuantity() + 1);
                }
                addTotal(menu1.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        menu2Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(menu2)) {
                    menu2.setQuantity(menu2.getQuantity() + 1);
                } else {
                    itemList.add(menu2);
                    menu2.setQuantity(menu2.getQuantity() + 1);
                }
                addTotal(menu2.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        menu3Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(menu3)) {
                    menu1.setQuantity(menu3.getQuantity() + 1);
                } else {
                    itemList.add(menu3);
                    menu3.setQuantity(menu3.getQuantity() + 1);
                }
                addTotal(menu3.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        menu4Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(menu4)) {
                    menu4.setQuantity(menu4.getQuantity() + 1);
                } else {
                    itemList.add(menu4);
                    menu4.setQuantity(menu4.getQuantity() + 1);
                }
                addTotal(menu4.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        cranberryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(cranberryJuice)) {
                    cranberryJuice.setQuantity(cranberryJuice.getQuantity() + 1);
                } else {
                    itemList.add(cranberryJuice);
                    cranberryJuice.setQuantity(cranberryJuice.getQuantity() + 1);
                }
                addTotal(cranberryJuice.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        fruitJuiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (itemList.contains(fruitJuice)) {
                    fruitJuice.setQuantity(fruitJuice.getQuantity() + 1);
                } else {
                    itemList.add(fruitJuice);
                    fruitJuice.setQuantity(fruitJuice.getQuantity() + 1);
                }
                addTotal(fruitJuice.getItemPrice());
                itemsAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setCustomerId(String id)
    {
        this.customerD = id;
    }

    public String getCustomerId()
    {
        return customerD;
    }

    public double getTotal()
    {
        return total;
    }

    public void addTotal(double price)
    {
        total = total +price;
        displayTotal();
    }

    public void displayTotal()
    {
        totalPrice.setText("£" + String.valueOf(df.format(getTotal())));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    MenuItem selItem;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        selItem = item;
        final String selectedItem = selItem.getTitle().toString();
        itemList.clear();
        total = 0.00;
        displayTotal();
        getSupportActionBar().hide();

        /*
        Requests the items linked to the event number from the database and adds them to the items list
         */
        JsonArrayRequest request = new JsonArrayRequest(GET_ITEMS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject items = response.getJSONObject(i);
                        String it = items.getString("EventID");
                        String itemName = items.getString("ItemName");
                        String cus = items.getString("CustomerID");
                        int quant = items.getInt("Quantity");

                        if(it.equals(selectedItem)) {
                            if(itemList.contains(it)) {
                                itemMap.get(itemName).setQuantity(quant);
                                itemMap.get(itemName).setItemPrice(itemMap.get(itemName).getItemPrice()* quant);
                                setCustomerId(cus);
                                eventIDString = it;
                                addTotal(itemMap.get(itemName).getItemPrice());
                                itemsAdapter.notifyDataSetChanged();
                            }
                            else{
                                itemList.add(itemMap.get(itemName));
                                itemMap.get(itemName).setQuantity(quant);
                                itemMap.get(itemName).setItemPrice(itemMap.get(itemName).getItemPrice()* quant);
                                setCustomerId(cus);
                                eventIDString = it;
                                addTotal(itemMap.get(itemName).getItemPrice());
                                itemsAdapter.notifyDataSetChanged();
                            }
                            }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        helper.add(request);
        eventID.clear();
        getCustomer();
        return super.onOptionsItemSelected(item);
    }

    /*
    Requests the customers details witch is related to the event number
     */
    private void getCustomer()
    {
        JsonArrayRequest details = new JsonArrayRequest(GET_CUSTOMER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject customer = response.getJSONObject(i);
                        String cusID = customer.getString("CustomerID");
                        if (cusID.equals(customerD)) {
                            String name = customer.getString("Name");
                            String room = customer.getString("RoomName");
                            int numGuests = customer.getInt("NumberGuests");
                            setInfo(name, room, numGuests);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        helper.add(details);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        int i =0;
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event1).setVisible(true);
            menu.findItem(R.id.action_event1).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event2).setVisible(true);
            menu.findItem(R.id.action_event2).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event3).setVisible(true);
            menu.findItem(R.id.action_event3).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event4).setVisible(true);
            menu.findItem(R.id.action_event4).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event5).setVisible(true);
            menu.findItem(R.id.action_event5).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event6).setVisible(true);
            menu.findItem(R.id.action_event6).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event7).setVisible(true);
            menu.findItem(R.id.action_event7).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event8).setVisible(true);
            menu.findItem(R.id.action_event8).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event9).setVisible(true);
            menu.findItem(R.id.action_event9).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event10).setVisible(true);
            menu.findItem(R.id.action_event10).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event11).setVisible(true);
            menu.findItem(R.id.action_event11).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event12).setVisible(true);
            menu.findItem(R.id.action_event12).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event13).setVisible(true);
            menu.findItem(R.id.action_event13).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event14).setVisible(true);
            menu.findItem(R.id.action_event14).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event15).setVisible(true);
            menu.findItem(R.id.action_event15).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event16).setVisible(true);
            menu.findItem(R.id.action_event16).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event17).setVisible(true);
            menu.findItem(R.id.action_event17).setTitle(eventID.get(i));
            i++;
        }
        if (i < eventID.size())
        {
            menu.findItem(R.id.action_event18).setVisible(true);
            menu.findItem(R.id.action_event18).setTitle(eventID.get(i));
            i++;
        }

        return true;
    }
//Places the cusomers name, room and number of covers onto the display
    private void setInfo(String customerName, String room, int covers)
    {
        String customer = "Name: "+ customerName;
        String roomName = "Room: " + room;
        String numberOfCovers = "Number of cover: " + String.valueOf(covers);
        info.setText(customer + "\n" + roomName + "\n" + numberOfCovers);
    }


    /*
    Loads all of the event ID's from the database into the action toolbar
     */
    private void loadEventIDs() {

       JsonArrayRequest request = new JsonArrayRequest(EVENT_IDS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                for(int i = 0; i < response.length(); i++)
                {
                    event = response.getJSONObject(i);
                    String id = event.getString("EventID");
                    String customerID = event.getString("CustomerID");
                    String room = event.getString("RoomName");
                    int numGuests = event.getInt("NumberGuests");
                    String startDate = event.getString("StartDate");
                    String endDate = event.getString("EndDate");
                    Double total = event.getDouble("TotalPrice");
                            eventID.add(id);
                    eventMap.put(id,new Event(id,customerID,room,numGuests,startDate,endDate,total));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        helper.add(request);
    }

    public ArrayList<String> getItemArray()
    {
        for (int i = 0; i<itemList.size();i++)
        {
            itemDetails.add(String.valueOf(itemList.get(i).getQuantity()) + " x " + itemList.get(i).getItemName() + "£" + String.valueOf(itemList.get(i).getItemPrice()) + " ");
        }
        return itemDetails;
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        Bundle b = new Bundle();


       b.putString("eventID",eventIDString);
        startActivity(intent);
    }

}

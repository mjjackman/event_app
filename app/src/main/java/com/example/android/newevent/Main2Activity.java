package com.example.android.newevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Main2Activity extends AppCompatActivity {

    final static String GET_ITEMS = "http://10.0.2.2/event/get_all_eventItems.php";
    String itemID;
    Item menu1 = new Item("Menu1", 50.00, 0);
    Item menu2 = new Item("Menu2", 120, 0);
    Item menu3 = new Item("Menu3", 180, 0);
    Item menu4 = new Item("Menu4", 250, 0);
    Item beer = new Item("Beer", 3.50, 0);
    Item redWine = new Item("Red Wine", 5.50, 0);
    Item whiteWine = new Item("White Wine", 5.50, 0);
    Item champagne = new Item("Champagne", 30.00, 0);
    Item orangeJuice = new Item("Orange Juice", 2.00, 0);
    Item appleJuice = new Item("Apple Juice", 2.00, 0);
    Item cranberryJuice = new Item("Cranberry Jucie", 2.00, 0);
    Item fruitJuice = new Item("Fruit Juice", 2.00, 0);
    Map<String, Item> itemMap = new TreeMap<String, Item>();
    private ListView itemListView;
    ArrayList<Item> itemList = new ArrayList<Item>();
    ArrayAdapter<Item> itemsAdapter;
    JsonRequestque helper = JsonRequestque.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        JsonRequestque helper = JsonRequestque.getInstance();
        itemListView = (ListView) findViewById(R.id.itemListView);
        itemsAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemList);
        itemListView.setAdapter(itemsAdapter);
        /** get the intent */
        Intent intent = getIntent();

/** get the bundle from intent*/
        Bundle b = intent.getExtras();
/** retrieve the string extra passed */
        //itemID = b.getString("eventID");
      //  itemList.add(itemMap.get(itemID));
    }
}
/*
    private void getItems()
    {
        JsonArrayRequest request = new JsonArrayRequest(GET_ITEMS, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject items = response.getJSONObject(i);
                        String id = items.getString("eventID");
                        String itemName = items.getString("ItemName");
                        int quantity = items.getInt("Quantity");
                        if(id.equals(itemID)) {
                            itemMap.get(itemID).setQuantity(itemMap.get(itemID).getQuantity()+ quantity);
                            itemList.add(itemMap.get(itemID));
                            itemsAdapter.notifyDataSetChanged();
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
    }

}

*/

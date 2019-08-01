package com.example.fetchshopdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   // private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextViewResult=findViewById(R.id.text_view_result);
        Button buttonParse =findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });

    }

    private void jsonParse(){
        String url = "https://shopapp2.azurewebsites.net/api/items";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type category = new TypeToken<List<Item>>(){}.getType();
                final Gson gson = new Gson();
                String res = response.toString();
                List<Item> items = gson.fromJson(res,category);
                for(int i = 0 ; i<response.length(); i++){
                    try {




                      //  String json = gson.toJson(items);

                        JSONObject item = response.getJSONObject(i);
                        int id = item.getInt("id");
                        String name = item.getString("name");
                        String shortDescription = item.getString("shortDescription");
                        double price = item.getDouble("price");
                        boolean isItemOfTheWeek = item.getBoolean("isItemOfTheWeek");
                        String imgPath = item.getString("imgPath");



                       // mTextViewResult.append(items.get(i).toString());
                        //mTextViewResult.append(String.valueOf(id) + ", " + name + ", "+ shortDescription+", "+String.valueOf(price)+", "+String.valueOf(isItemOfTheWeek) + ", " + imgPath +"\n\n");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                final ListView listView = (ListView) findViewById(R.id.listView);
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), items );
                listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}

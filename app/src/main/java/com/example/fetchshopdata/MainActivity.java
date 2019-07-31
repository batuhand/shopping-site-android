package com.example.fetchshopdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult=findViewById(R.id.text_view_result);
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
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0 ; i<response.length(); i++){
                    try {
                        JSONObject item = response.getJSONObject(i);
                        int id = item.getInt("id");
                        String name = item.getString("name");
                        String shortDescription = item.getString("shortDescription");
                        double price = item.getDouble("price");
                        boolean isItemOfTheWeek = item.getBoolean("isItemOfTheWeek");
                        String imgPath = item.getString("imgPath");

                        mTextViewResult.append(String.valueOf(id) + ", " + name + ", "+ shortDescription+", "+String.valueOf(price)+", "+String.valueOf(isItemOfTheWeek) + ", " + imgPath +"\n\n");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
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

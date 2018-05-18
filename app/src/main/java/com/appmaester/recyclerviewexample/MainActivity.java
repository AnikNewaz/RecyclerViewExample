package com.appmaester.recyclerviewexample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appmaester.recyclerviewexample.Adapter.SuperheroAdapter;
import com.appmaester.recyclerviewexample.Model.ListItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SUPERHERO_URL = "http://aniknewaz.cf/marvel/bio.json";
    private RecyclerView rvSuperheroes;
    private RecyclerView.Adapter rvSuperAdapter;
    private List<ListItems> superListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSuperheroes = findViewById(R.id.recView);
        rvSuperheroes.setHasFixedSize(true);
        rvSuperheroes.setLayoutManager(new LinearLayoutManager(this));

        superListItems = new ArrayList<>();

        loadSuperheroRecViewData();

    }

    private void loadSuperheroRecViewData() {

        // Progress Dialog while Data Loading
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progressMsgText));
        progressDialog.show();

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                SUPERHERO_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        //Progress Dialog Dismissal
                        progressDialog.dismiss();

                        // Process the JSON
                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject superhero = response.getJSONObject(i);

                                // Get the current student (json object) data
                                ListItems item = new ListItems(
                                        superhero.getString("name"),
                                        superhero.getString("realname"),
                                        superhero.getString("team"),
                                        superhero.getString("firstappearance"),
                                        superhero.getString("createdby"),
                                        superhero.getString("imageurl")
                                );
                                superListItems.add(item);
                            }
                            rvSuperAdapter = new SuperheroAdapter(superListItems, getApplicationContext());
                            rvSuperheroes.setAdapter(rvSuperAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
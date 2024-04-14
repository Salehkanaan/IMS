package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomAdapter extends BaseAdapter {

    JSONArray dishesData;
    Context context;
    LayoutInflater inflater;
    public CustomAdapter(Context c, JSONArray data){
        context = c;
        dishesData =data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dishesData.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class Holder{
        TextView nameTv;
        TextView priceTv;
        TextView typeTv;
        ImageButton deleteButton;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.row, null);

        Holder holder = new Holder();
        holder.nameTv = rowView.findViewById(R.id.nameTv);
        holder.priceTv = rowView.findViewById(R.id.priceTv);
        holder.typeTv = rowView.findViewById(R.id.typeTv);
        holder.deleteButton = rowView.findViewById(R.id.deleteButton);

        JSONObject obj = dishesData.optJSONObject(position);
        try {
            holder.nameTv.setText(obj.getString("name"));
            holder.priceTv.setText(obj.getString("price"));
            holder.typeTv.setText(obj.getString("type"));
            holder.deleteButton.setTag(obj.getInt("id"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://10.0.2.2/restaurant/deletedish.php?id=" + (int)v.getTag();
                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    ((MainActivity)context).onResume();
                                }
                                else{
                                    Toast.makeText(context, "Delete failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                queue.add(request);
            }
        });


        return rowView;
    }
}

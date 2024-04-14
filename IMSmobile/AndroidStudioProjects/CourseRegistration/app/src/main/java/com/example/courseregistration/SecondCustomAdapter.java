package com.example.courseregistration;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

public class SecondCustomAdapter extends BaseAdapter {
    Context con;
    JSONArray data;
    String username;
    LayoutInflater inflater;

    // constructor
    public SecondCustomAdapter(Context c, JSONArray data, String username) {
        this.con = c;
        this.data = data;
        this.username = username;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        // i is the clicked position and is filled by Android
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.row_for_university_courses, null);

        holder.nametxtv = rowView.findViewById(R.id.ucname);
        holder.descriptiontxtv = rowView.findViewById(R.id.ucdescription);
        holder.creditstxtv = rowView.findViewById(R.id.uccredits);
        holder.addimage = rowView.findViewById(R.id.add);

        JSONObject obj = data.optJSONObject(i);
        try {
            String name = obj.getString("name");
            holder.nametxtv.setText(name);

            String description = obj.getString("description");
            holder.descriptiontxtv.setText(description);

            String credits = obj.getString("credits");
            holder.creditstxtv.setText(credits + " credits");

            holder.addimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://10.0.2.2/university/add_registered_courses.php?username=" + username +
                            "&name=" + name + "&description=" + description + "&credits=" + credits;
                    RequestQueue queue = Volley.newRequestQueue(con);
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (!response.equals("-1")) {
                                ((UniversityCoursesActivity) con).finish();
                            } else {
                                Toast.makeText(con, "Add failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(con,"Error:" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(request);
                }
            });
        } catch (JSONException e) {
        }
        return rowView;
    }

    public class Holder {
        TextView nametxtv, descriptiontxtv, creditstxtv;
        ImageView addimage;
    }
}

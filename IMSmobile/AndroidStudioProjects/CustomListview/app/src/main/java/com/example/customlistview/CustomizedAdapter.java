package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomizedAdapter extends BaseAdapter {
    Context context;
    String[] texts;
    int[] imageIds;
    LayoutInflater inflater;
    public CustomizedAdapter(Context context, String[] texts, int[] imageIds){
        this.context = context;
        this.texts = texts;
        this.imageIds = imageIds;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Holder{
        TextView tv;
        ImageView iv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.row, null);
        Holder holder = new Holder();
        holder.tv = rowView.findViewById(R.id.tv);
        holder.tv.setText(texts[position]);

        holder.iv = rowView.findViewById(R.id.iv);
        holder.iv.setImageResource(imageIds[position]);
        rowView.setTag(position);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, texts[(int)rowView.getTag()], Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }
}

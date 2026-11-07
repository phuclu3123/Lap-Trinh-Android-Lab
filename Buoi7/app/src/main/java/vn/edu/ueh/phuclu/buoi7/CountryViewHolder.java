package vn.edu.ueh.phuclu.buoi7;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private MyAdapter madapter;
    public TextView tid;
    public TextView tcountry;

    public CountryViewHolder(@NonNull View item, MyAdapter adapter) {
        super(item);
        this.madapter = adapter;
        this.tid = item.findViewById(R.id.tid);
        this.tcountry = item.findViewById(R.id.tcountry);
        item.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        String msg = tid.getText() + "|" + tcountry.getText();
        Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
        // Lấy context thông qua vie tương ứng (activity tương ứng)
    }
}
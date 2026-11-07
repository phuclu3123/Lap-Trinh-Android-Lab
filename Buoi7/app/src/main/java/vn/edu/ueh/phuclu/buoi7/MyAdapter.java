package vn.edu.ueh.phuclu.buoi7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List countrylist;
    private LayoutInflater minflater;
    private MyAdapter madapter;

    public MyAdapter(Context context, List list) {
        minflater = LayoutInflater.from(context);
        this.countrylist = list;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = minflater.inflate(
                R.layout.country_layout,
                parent,
                false
        );
        return new CountryViewHolder(item, madapter);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        String country = (String) countrylist.get(position);
        holder.tid.setText((position+1)+"");
        holder.tcountry.setText(country);
    }

    @Override
    public int getItemCount() {
        return countrylist.size();
    }
}
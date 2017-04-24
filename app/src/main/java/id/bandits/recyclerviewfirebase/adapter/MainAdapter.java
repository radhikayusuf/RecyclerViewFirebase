package id.bandits.recyclerviewfirebase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.bandits.recyclerviewfirebase.R;
import id.bandits.recyclerviewfirebase.dao.MainDao;

/**
 * Created by root on 24/04/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainDao> mData = new ArrayList<>();
    private Context mContext;
    private int type;

    static public final int TYPE_LIST = 1;
    static public final int TYPE_GRID = 2;

    public MainAdapter(List<MainDao> mData, int type) {
        this.mData = mData;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(type == TYPE_LIST ? R.layout.main_row_list : R.layout.main_row_grid, parent, false);
        return new ViewHolder(v, type);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        Picasso.with(mContext)
                .load(mData.get(position).getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return null != mData ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView imageView;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            tv_title = (TextView) (type == TYPE_LIST ? itemView.findViewById(R.id.title_row_list) : itemView.findViewById(R.id.title_row_grid));
            imageView = (ImageView) (type == TYPE_LIST ? itemView.findViewById(R.id.img_row_list) : itemView.findViewById(R.id.img_row_grid));
        }
    }
}

package com.example.root.fatweightindice.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.root.fatweightindice.R;
import com.example.root.fatweightindice.bean.Profile;
import com.example.root.fatweightindice.business.DateUtil;
import com.example.root.fatweightindice.controller.FWI;

import java.util.List;

public class ListAdapterHistory extends BaseAdapter {

    private List<Profile> profiles = null;
    private LayoutInflater layoutInflater = null;
    private FWI fwi = null;
    private Context context = null;

    /**
     * public and unique constructor
     * @param profiles
     */
    public ListAdapterHistory(Context context, List<Profile> profiles){
        this.profiles = profiles;
        this.context = context;
        this.layoutInflater  = LayoutInflater.from(context);
        this.fwi = FWI.getInstance();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return profiles.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return profiles.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position; here we have consider it as the position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declaration of a holder
        ViewHolder holder;
        // check the existence of the line
        if(convertView == null){
            holder = new ViewHolder();
            // the line is constructed from a formatter (inflater) link to layout_list_history
            convertView = layoutInflater.inflate(R.layout.layout_list_history, null);
            // every holder's property is link to a graphical property
            holder.btnListDelete = convertView.findViewById(R.id.btnListDelete);
            holder.txtListDate = convertView.findViewById(R.id.txtListDate);
            holder.txtListFWI = convertView.findViewById(R.id.txtListFWI);
            // send the holder to the view
            convertView.setTag(holder);
        } else{
            // recovery of the ViewHolder from the existant view
            holder = (ViewHolder) convertView.getTag();
        }

        // development of the holder's content, ie, of the line
        holder.txtListDate.setText(DateUtil.convertDateToString(profiles.get(position).getDate()));
        holder.txtListFWI.setText(ViewUtil.decimalFormat(profiles.get(position).getFwi()));
        holder.btnListDelete.setTag(position);
        holder.txtListDate.setTag(position);
        holder.txtListFWI.setTag(position);

        // click on delete button management
        deleteListener(holder.btnListDelete);

        // click on the rest of the line.
        txtClickListener(holder.txtListDate);
        txtClickListener(holder.txtListFWI);
        return convertView;
    }

    private void deleteListener(ImageButton imageButton){
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                int position = (int)view.getTag();
                Profile profile = profiles.get(position);
                fwi.deleteProfile(profile);
                notifyDataSetChanged();
            }
        });
    }

    private void txtClickListener(TextView txtview){
        txtview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int position = (int)view.getTag();
                Profile profile = profiles.get(position);
                ((HistoryActivity)context).displayProfile(profile);
            }
        });
    }

    private class ViewHolder{
        private ImageButton btnListDelete;
        TextView txtListDate;
        TextView txtListFWI;
    }
}

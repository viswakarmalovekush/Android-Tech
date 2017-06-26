package mobi.hubbler.assignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import mobi.hubbler.assignment.R;

/**
 * Created by lovekushvishwakarma on 24/06/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private JSONArray jsonUserArr;

    public UserAdapter(JSONArray jsonUserArr) {
        this.jsonUserArr = jsonUserArr;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_childview, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        try {
            JSONObject data = jsonUserArr.getJSONObject(position);
            holder.textName.setText("Name : " + data.getString("Name"));
            holder.textAge.setText("Age : " + data.getString("Age"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return jsonUserArr.length();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textAge;

        public UserViewHolder(View view) {
            super(view);
            textName = (TextView) view.findViewById(R.id.textName);
            textAge = (TextView) view.findViewById(R.id.textAge);
        }
    }
}

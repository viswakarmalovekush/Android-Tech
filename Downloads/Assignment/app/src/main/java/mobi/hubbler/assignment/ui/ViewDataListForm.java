package mobi.hubbler.assignment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import mobi.hubbler.assignment.MainActivity;
import mobi.hubbler.assignment.R;
import mobi.hubbler.assignment.adapters.UserAdapter;
import mobi.hubbler.assignment.utils.AddUpdateCallBack;
import mobi.hubbler.assignment.utils.AppUtils;

/**
 * Created by lovekushvishwakarma on 24/06/17.
 */

public class ViewDataListForm extends Fragment implements View.OnClickListener{
    public static String TAG = "ViewList";

    public UserAdapter adapter;
    public TextView textTotreports;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.userlist_layout, null);
        JSONArray aa=((MainActivity)getActivity()).aar;
        Button buttonAdd = (Button) root.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        textTotreports = (TextView) root.findViewById(R.id.textTotreports);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerList);
        adapter = new UserAdapter(aa);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fragment = new AddDataForm();

        textTotreports.setText("Total Reports : " + ((MainActivity)getActivity()).aar.length());

        return root;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonAdd) {
            FragmentManager fmg = getFragmentManager();
            FragmentTransaction fmt = fmg.beginTransaction();
            fmt.replace(R.id.container, fragment, AddDataForm.TAG);
            fmt.addToBackStack("Add");
            fmt.commit();
        }

    }

    public void updateText(){
        textTotreports.setText("Total Reports : " + ((MainActivity)getActivity()).aar.length());
        adapter.notifyDataSetChanged();
    }

}

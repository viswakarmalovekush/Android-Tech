package mobi.hubbler.assignment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import mobi.hubbler.assignment.ui.ViewDataListForm;
import mobi.hubbler.assignment.utils.AddUpdateCallBack;
import mobi.hubbler.assignment.utils.AppUtils;


public class MainActivity extends AppCompatActivity implements AddUpdateCallBack {

    FragmentTransaction fragmentTransaction;
    ViewDataListForm fragment;

    public JSONArray aar = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new ViewDataListForm();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment, ViewDataListForm.TAG);
        fragmentTransaction.commit();


    }

    @Override
    public void getUpdate(final JSONObject rowdata) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final ViewDataListForm fragment = (ViewDataListForm) getSupportFragmentManager().findFragmentByTag(ViewDataListForm.TAG);
                aar.put(rowdata);
                fragment.updateText();
            }
        }, 0);


    }
}

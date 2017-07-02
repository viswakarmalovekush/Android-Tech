package mobi.hubbler.assignment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mobi.hubbler.assignment.MainActivity;
import mobi.hubbler.assignment.R;
import mobi.hubbler.assignment.models.ValidationInfo;
import mobi.hubbler.assignment.utils.AddUpdateCallBack;
import mobi.hubbler.assignment.utils.AppUtils;

/**
 * Created by lovekushvishwakarma on 24/06/17.
 */

public class AddDataForm extends Fragment implements OnClickListener {

    public static String TAG = "AddData";
    private EditText editName, editAge, editAddress;
    private Spinner spinGender;
    private LinearLayout layoutGender;
    private ValidationInfo validationinfo;

    private AddUpdateCallBack addUpdateCallBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.adddata_form, null);

        addUpdateCallBack=(AddUpdateCallBack)getActivity();
        editName = (EditText) root.findViewById(R.id.editName);
        editAge = (EditText) root.findViewById(R.id.editAge);
        editAddress = (EditText) root.findViewById(R.id.editAddress);
        spinGender = (Spinner) root.findViewById(R.id.spinGender);
        layoutGender = (LinearLayout) root.findViewById(R.id.layoutGender);

        Button buttonDone = (Button) root.findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(this);

        // just change here your input JSON by just changing the file name input_json2 to input_json1
        JSONArray jssonArr = AppUtils.loadJSONFromAsset(getActivity(), "input_json1");

        validationinfo = AppUtils.getValidationInfo(jssonArr, layoutGender);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDone:
                if (AppUtils.performValidation(editName, editAge, validationinfo, getActivity())) {

                    try {
                        getFragmentManager().popBackStack();
                        JSONObject jsonData = new JSONObject();
                        String name = editName.getText().toString();
                        jsonData.put("Name", name);
                        String age = editAge.getText().toString();
                        jsonData.put("Age", age);
                        if (validationinfo.isGenderVisiblity()) {
                            String gender = spinGender.getSelectedItem().toString();
                            jsonData.put("Gender", gender);
                        }
                        String address = editAddress.getText().toString();
                        jsonData.put("Address", address);
                        addUpdateCallBack.getUpdate(jsonData);
                        Log.d("JSON ", jsonData + "");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                break;
        }
    }
}

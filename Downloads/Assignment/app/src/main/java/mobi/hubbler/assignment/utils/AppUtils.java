package mobi.hubbler.assignment.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import mobi.hubbler.assignment.models.ValidationInfo;

/**
 * Created by lovekushvishwakarma on 24/06/17.
 */

public class AppUtils {

    public static JSONArray loadJSONFromAsset(Activity activity, String filename) {
        JSONArray json = null;
        try {
            InputStream is = activity.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new JSONArray(new String(buffer, "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static ValidationInfo getValidationInfo(JSONArray jsonArr, LinearLayout layoutGender) {
        ValidationInfo info = new ValidationInfo();
        try {
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject data = jsonArr.getJSONObject(i);
                String field_name = data.getString("field-name");
                String type = data.getString("type");
                if (field_name.equalsIgnoreCase("name")) {
                    if (data.has("required")) {
                        boolean flag = data.getBoolean("required");
                        info.setNameRequired(true);
                        System.out.println("required " + flag);
                    } else {
                        info.setNameRequired(false);
                    }
                } else if (field_name.equalsIgnoreCase("age")) {
                    if (data.has("min") && data.has("max")) {
                        int min = data.getInt("min");
                        System.out.println("Min " + min);
                        info.setAgeMin(min);

                        int max = data.getInt("max");
                        System.out.println("Min " + max);
                        info.setAgeMax(max);
                        info.setAgeValidation(true);
                    } else {
                        info.setAgeValidation(false);
                    }

                } else if (field_name.equalsIgnoreCase("gender")) {
                    layoutGender.setVisibility(View.VISIBLE);
                    info.setGenderVisiblity(true);
                } else if (field_name.equalsIgnoreCase("address")) {
                    // no validation for address feild
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static boolean performValidation(EditText editName, EditText editAge, ValidationInfo validationinfo, Activity activity) {
        boolean nameFalg, ageFlag;
        if (validationinfo.isNameRequired()) {
            // should not be empty
            if (editName.getText().toString().length() == 0) {
                myToast(activity, "please enter name!");
                nameFalg = false;
                return false;
            } else {
                nameFalg = true;
            }
        } else {
            nameFalg = true;
        }
        if (validationinfo.isAgeValidation()) {
            int age = 0;
            try {
                age = Integer.parseInt(editAge.getText().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (age >= validationinfo.getAgeMin() && age <= validationinfo.getAgeMax()) {
                ageFlag = true;
            } else {
                ageFlag = false;
                myToast(activity, "please enter valide age between " + validationinfo.getAgeMin() + " and " + validationinfo.getAgeMax());
            }
        } else {
            ageFlag = true;
        }
        if (nameFalg && ageFlag) {
            return true;
        } else {
            return false;
        }

    }

    public static void myToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}

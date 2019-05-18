package com.github.shyamking.bmimeter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText weight, height;
    TextView result;
    ImageView remarks;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        result = findViewById(R.id.resultText);
        remarks = findViewById(R.id.remarksImage);
        activity = this;

        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(activity);

                if (weight.getText().toString().isEmpty() ||
                height.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter proper height and weight values", Toast.LENGTH_SHORT).show();
                    return;
                }

                float fWeight, fHeight, BMI;

                fWeight = Float.valueOf(weight.getText().toString());
                fHeight = Float.valueOf(height.getText().toString());

                BMI = fWeight / (fHeight*fHeight);
                result.setText("You have a Body Mass Index of " + BMI + "\n");
                if (BMI < 18.5) {
                    //under
                    remarks.setImageResource(R.mipmap.underweight);
                    result.append("You don\'t wanna look like him, do you?");
                } else if (BMI < 25) {
                    //fit
                    remarks.setImageResource(R.mipmap.fit);
                    result.append("Looking as hot as ever!");
                } else  {
                    //over
                    remarks.setImageResource(R.mipmap.overweight);
                    result.append("Still roun-, I mean work a bit more.");
                }
            }
        });

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}

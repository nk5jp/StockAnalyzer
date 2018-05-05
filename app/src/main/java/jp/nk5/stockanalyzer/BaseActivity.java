package jp.nk5.stockanalyzer;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;

public class BaseActivity extends Activity implements UpdateViewListener {


    public void updateView() {

    }

    protected int getIntegerFromEditText(int id)
    {
        EditText editText = findViewById(id);
        return Integer.parseInt(editText.getText().toString());
    }

    protected String getStringFromEditText(int id)
    {
        EditText editText = findViewById(id);
        return editText.getText().toString();
    }

    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    protected void setIntegerToEditText(int id, int value)
    {
        EditText editText = findViewById(id);
        editText.setText(String.format(Locale.JAPAN, "%d", value));
    }

    protected void setStringToEditText(int id, String value)
    {
        EditText editText = findViewById(id);
        editText.setText(value);
    }

    protected void setStringToTextView(int id, String value)
    {
        TextView textView = findViewById(id);
        textView.setText(value);
    }

}

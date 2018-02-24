package jp.nk5.stockanalyzer;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import jp.nk5.stockanalyzer.viewmodel.UpdateViewListener;

public class BaseActivity extends Activity implements UpdateViewListener {


    public void updateView() {

    }

    protected int getIntegerFromEditText(int id) throws Exception
    {
        EditText editText = findViewById(id);
        return Integer.parseInt(editText.getText().toString());
    }

    protected String getStringFromEditText(int id) throws Exception
    {
        EditText editText = findViewById(id);
        return editText.getText().toString();
    }

    public void showError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}

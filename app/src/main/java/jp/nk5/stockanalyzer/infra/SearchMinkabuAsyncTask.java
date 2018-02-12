package jp.nk5.stockanalyzer.infra;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentStock;

public class SearchMinkabuAsyncTask extends AsyncTask<List<CurrentStock>, Void, List<CurrentStock>> {

    private SearchMinkabuListener listener;
    private final Integer INTEGER_FAILED = -1;

    public SearchMinkabuAsyncTask(SearchMinkabuListener listener)
    {
        this.listener = listener;
    }


    protected void onPostExecute()
    {
        listener.lockUI();
    }

    protected List<CurrentStock> doInBackground(List<CurrentStock>... stocks_array)
    {
        List<CurrentStock> stocks = stocks_array[0];
        HttpURLConnection connection = null;
        String urlFormat = "https://minkabu.jp/stock/%d";

        for (CurrentStock stock : stocks) {

            // 接続の確立
            try {
                URL url = new URL(String.format(urlFormat, stock.getCode()));
                // 接続用HttpURLConnectionオブジェクト作成
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setInstanceFollowRedirects(false);
                connection.setRequestProperty("Accept-Language", "jp");
                // 接続
                connection.connect();
            } catch (Exception e) {
                break;
            }

            //必要な情報の取得
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"))) {
                String result = stock.getCode() + ":" + stock.getName();
                String line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    if (line.contains("stock_label fsl")) {
                        line = reader.readLine();
                        stock.setRemarks(line.split("\\(")[1].split("\\)")[0]);
                    }
                    if (line.contains("stock_price")) {
                        line = reader.readLine();
                        stock.setPrice(
                                Integer.parseInt(
                                    line.split("\\.")[0].replace(" ", "").replace(",", "")
                                )
                        );
                        break;
                    }
                }
            } catch (Exception e) {
                return stocks;
            }
        }
        return stocks;
    }


    protected void onPostExecute(List<CurrentStock> stocks)
    {
        listener.unlockUI();
        listener.updateUI(stocks);
    }

    protected void onCancelled()
    {
        listener.unlockUI();
    }

}

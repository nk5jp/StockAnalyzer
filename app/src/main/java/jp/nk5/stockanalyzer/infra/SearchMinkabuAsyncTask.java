package jp.nk5.stockanalyzer.infra;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentStock;
import jp.nk5.stockanalyzer.domain.Stock;

public class SearchMinkabuAsyncTask extends AsyncTask<Stock, Void, List<CurrentStock>> {

    private SearchMinkabuListener listener;
    private final String UNDEFINED_REMARK = "NONE";
    private final int UNDEFINED_PRICE = -1;

    public SearchMinkabuAsyncTask(SearchMinkabuListener listener)
    {
        this.listener = listener;
    }

    protected void onPostExecute()
    {
        listener.lockUI();
    }

    protected List<CurrentStock> doInBackground(Stock... stocks)
    {
        List<CurrentStock> currentStocks = new ArrayList<CurrentStock>();
        HttpURLConnection connection = null;
        String urlFormat = "https://minkabu.jp/stock/%d";

        for (Stock stock : stocks) {
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
                return null;
            }

            //必要な情報の取得
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"))) {
                String line;
                String remark = UNDEFINED_REMARK;
                int price = UNDEFINED_PRICE;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("stock_label fsl")) {
                        line = reader.readLine();
                        remark = line.split("\\(")[1].split("\\)")[0];
                    }
                    if (line.contains("stock_price")) {
                        line = reader.readLine();
                        price = Integer.parseInt(
                                    line.split("\\.")[0].replace(" ", "").replace(",", "")
                                );
                        break;
                    }
                }
                currentStocks.add(new CurrentStock(stock.getCode(), stock.getName(), price, remark));
            } catch (Exception e) {
                return null;
            }
        }
        return currentStocks;
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

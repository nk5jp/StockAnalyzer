package jp.nk5.stockanalyzer.infra;

import java.util.List;

public interface SearchMinkabuListener {

    void updateUI(List<String> strings);
    void lockUI();
    void unlockUI();

}

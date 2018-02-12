package jp.nk5.stockanalyzer.infra;

import java.util.List;

import jp.nk5.stockanalyzer.domain.CurrentStock;

public interface SearchMinkabuListener {

    void updateUI(List<CurrentStock> stocks);
    void lockUI();
    void unlockUI();

}

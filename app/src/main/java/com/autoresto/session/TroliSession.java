package com.autoresto.session;

import com.autoresto.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class TroliSession {

    private List<TroliData> troliDataList = new ArrayList<>();

    private static TroliSession troliSession = new TroliSession();

    public static TroliSession getInstance() {
        return troliSession;
    }

    public List<TroliData> getTroliDataList() {
        return troliDataList;
    }

    public void setTroliDataList(List<TroliData> troliDataList) {
        this.troliDataList = troliDataList;
    }

    public void addtroliData(TroliData troliData) {
        this.troliDataList.add(troliData);
    }

    public void removetroliData(TroliData troliData, int menu_id) {
        for (int i=0; i <= this.troliDataList.size(); i++ ) {
            if (troliData.getMenu().getId() == menu_id) {
                this.troliDataList.remove(i);
            }
        }
    }

    public void removeAllList() {
        this.troliDataList.clear();
    }
}

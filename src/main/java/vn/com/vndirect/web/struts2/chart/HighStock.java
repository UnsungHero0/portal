package vn.com.vndirect.web.struts2.chart;

import java.util.ArrayList;

import net.sf.json.JSONArray;

public class HighStock {
	/**
	 * First item contains additional data as : symbol, company name ... From second items : transDate, close, high, low, open, volume
	 * */
	private JSONArray data;
	private ArrayList<JSONArray> listData;

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public ArrayList<JSONArray> getListData() {
		return listData;
	}

	public void setListData(ArrayList<JSONArray> listData) {
		this.listData = listData;
	}
}

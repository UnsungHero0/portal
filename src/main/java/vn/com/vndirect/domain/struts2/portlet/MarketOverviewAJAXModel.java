package vn.com.vndirect.domain.struts2.portlet;

import java.util.ArrayList;
import java.util.List;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.wsclient.streamquotes.MarketInfo;
import vn.com.vndirect.wsclient.streamquotes.SecInfo;

@SuppressWarnings("serial")
public class MarketOverviewAJAXModel extends BaseModel {

	private MarketInfo hastcMarket;
	private MarketInfo hostcMarket;
	private MarketInfo upComMarket;
	private MarketInfo vn30Market;
	private MarketInfo hnx30Market;

	private String currentDate;
	private int clickQoute;
	private List<SecInfo> listQuote = new ArrayList<SecInfo>();
	private List<MarketInfo> listMarketInfo = new ArrayList<MarketInfo>();

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public double getHastcCurrentIndex() {
		return hastcMarket == null ? 0 : hastcMarket.getMarketIndex();
	}

	public double getHostcCurrentIndex() {
		return hostcMarket == null ? 0 : hostcMarket.getMarketIndex();
	}

	public double getUpComCurrentIndex() {
		return upComMarket == null ? 0 : upComMarket.getMarketIndex();
	}

	public double getHastcChagIndex() {
		return hastcMarket == null ? 0 : hastcMarket.getChgIndex();
	}

	public double getHostcChagIndex() {
		return hostcMarket == null ? 0 : hostcMarket.getChgIndex();
	}

	public double getUpComChagIndex() {
		return upComMarket == null ? 0 : upComMarket.getChgIndex();
	}

	public double getVn30CurrentIndex() {
		return vn30Market == null ? 0 : vn30Market.getMarketIndex();
	}

	public double getVn30ChagIndex() {
		return vn30Market == null ? 0 : vn30Market.getChgIndex();
	}

	public double getHnx30CurrentIndex() {
		return hnx30Market == null ? 0 : hnx30Market.getMarketIndex();
	}

	public double getHnx30ChagIndex() {
		return hnx30Market == null ? 0 : hnx30Market.getChgIndex();
	}

	public MarketInfo getVn30Market() {
		return vn30Market;
	}

	public void setVn30Market(MarketInfo vn30Market) {
		this.vn30Market = vn30Market;
	}

	/**
	 * @return the hastcMarket
	 */
	public MarketInfo getHastcMarket() {
		return this.hastcMarket;
	}

	/**
	 * @param hastcMarket
	 *            the hastcMarket to set
	 */
	public void setHastcMarket(MarketInfo hastcMarket) {
		this.hastcMarket = hastcMarket;
	}

	/**
	 * @return the hostcMarket
	 */
	public MarketInfo getHostcMarket() {
		return this.hostcMarket;
	}

	/**
	 * @param hostcMarket
	 *            the hostcMarket to set
	 */
	public void setHostcMarket(MarketInfo hostcMarket) {
		this.hostcMarket = hostcMarket;
	}

	public List<SecInfo> getListQuote() {
		return listQuote;
	}

	public void setListQuote(List<SecInfo> listQoute) {
		this.listQuote = listQoute;
	}

	public int getClickQoute() {
		return clickQoute;
	}

	public void setClickQoute(int clickQoute) {
		this.clickQoute = clickQoute;
	}

	public MarketInfo getUpComMarket() {
		return upComMarket;
	}

	public void setUpComMarket(MarketInfo upComMarket) {
		this.upComMarket = upComMarket;
	}

	public List<MarketInfo> getListMarketInfo() {
		return listMarketInfo;
	}

	public void setListMarketInfo(List<MarketInfo> listMarketInfo) {
		this.listMarketInfo = listMarketInfo;
	}

	public MarketInfo getHnx30Market() {
		return hnx30Market;
	}

	public void setHnx30Market(MarketInfo hnx30Market) {
		this.hnx30Market = hnx30Market;
	}

}

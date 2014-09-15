package vn.com.vndirect.domain.struts2.listedmarket;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.extend.MarketOption;
import vn.com.vndirect.wsclient.streamquotes.MarketInfo;

@SuppressWarnings("serial")
public class ListMarketOverviewModel extends BaseModel {
	private MarketInfo hastcMarketInfo;
	private MarketInfo hostcMarketInfo;
	private MarketInfo vn30MarketInfo;
	private MarketInfo hnx30MarketInfo;
	private MarketInfo upComMarketInfo;

	private MarketOption marketOption = new MarketOption();

	/**
	 * @return the marketOption
	 */
	public MarketOption getMarketOption() {
		return marketOption;
	}

	/**
	 * @param marketOption
	 *            the marketOption to set
	 */
	public void setMarketOption(MarketOption marketOption) {
		this.marketOption = marketOption;
	}

	/**
	 * @return the hastcMarketInfo
	 */
	public MarketInfo getHastcMarketInfo() {
		return hastcMarketInfo;
	}

	/**
	 * @param hastcMarketInfo
	 *            the hastcMarketInfo to set
	 */
	public void setHastcMarketInfo(MarketInfo hastcMarketInfo) {
		this.hastcMarketInfo = hastcMarketInfo;
	}

	/**
	 * @return the hostcMarketInfo
	 */
	public MarketInfo getHostcMarketInfo() {
		return hostcMarketInfo;
	}

	/**
	 * @param hostcMarketInfo
	 *            the hostcMarketInfo to set
	 */
	public void setHostcMarketInfo(MarketInfo hostcMarketInfo) {
		this.hostcMarketInfo = hostcMarketInfo;
	}

	/**
	 * @return the upComMarketInfo
	 */
	public MarketInfo getUpComMarketInfo() {
		return upComMarketInfo;
	}

	/**
	 * @param upComMarketInfo
	 *            the upComMarketInfo to set
	 */
	public void setUpComMarketInfo(MarketInfo upComMarketInfo) {
		this.upComMarketInfo = upComMarketInfo;
	}

	public MarketInfo getVn30MarketInfo() {
		return vn30MarketInfo;
	}

	public void setVn30MarketInfo(MarketInfo vn30MarketInfo) {
		this.vn30MarketInfo = vn30MarketInfo;
	}

	public MarketInfo getHnx30MarketInfo() {
		return hnx30MarketInfo;
	}

	public void setHnx30MarketInfo(MarketInfo hnx30MarketInfo) {
		this.hnx30MarketInfo = hnx30MarketInfo;
	}
}

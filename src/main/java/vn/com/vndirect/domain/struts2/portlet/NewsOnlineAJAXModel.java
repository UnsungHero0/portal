package vn.com.vndirect.domain.struts2.portlet;

import java.util.List;

import vn.com.vndirect.commons.utility.Constants;
import vn.com.vndirect.domain.BaseModel;

@SuppressWarnings( { "unchecked", "serial" })
public class NewsOnlineAJAXModel extends BaseModel {

	private String type;
	private int numberItem = Constants.Paging.NUMBER_ITEMS;
	private String showin = "";
	private String display = "";
	private String listSymbol;
	private int pagingIndex = 0;

	private List listIfoNews;
	private String checkInCache;

	public int getNewsSize() {
		return (listIfoNews == null ? 0 : listIfoNews.size());
	}

	/**
	 * Retrieving the News type
	 * 
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Setting news type
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retrieving number item for each page
	 * 
	 * @return the numberItem
	 */
	public int getNumberItem() {
		return this.numberItem;
	}

	/**
	 * Setting number item for each page
	 * 
	 * @param numberItem
	 *            the numberItem to set
	 */
	public void setNumberItem(int numberItem) {
		this.numberItem = numberItem;
	}

	/**
	 * Retrieving news show in window
	 * 
	 * @return the showin
	 */
	public String getShowin() {
		return this.showin;
	}

	/**
	 * Setting news show in window
	 * 
	 * @param showin
	 *            the showin to set
	 */
	public void setShowin(String showin) {
		this.showin = showin;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return this.display;
	}

	/**
	 * @param display
	 *            the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * 
	 * @return the listSymbol
	 */
	public String getListSymbol() {
		return this.listSymbol;
	}

	/**
	 * @param listSymbol
	 *            the listSymbol to set
	 */
	public void setListSymbol(String listSymbol) {
		this.listSymbol = listSymbol;
	}

	/**
	 * Retrieving the result news list
	 * 
	 * @return the listIfoNews
	 */
	public List getListIfoNews() {
		return this.listIfoNews;
	}

	/**
	 * Setting the result news list
	 * 
	 * @param listIfoNews
	 *            the listIfoNews to set
	 */
	public void setListIfoNews(List listIfoNews) {
		this.listIfoNews = listIfoNews;
	}

	/**
	 * Retrieving current paging index
	 * 
	 * @return
	 */
	public int getPagingIndex() {
		return pagingIndex;
	}

	/**
	 * Setting current paging index
	 * 
	 * @param pagingIndex
	 */
	public void setPagingIndex(int pagingIndex) {
		this.pagingIndex = pagingIndex;
	}

	public String getCheckInCache() {
		return checkInCache;
	}

	public void setCheckInCache(String checkInCache) {
		this.checkInCache = checkInCache;
	}

}

package vn.com.vndirect.domain.struts2.newsinfo;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;

@SuppressWarnings( { "unchecked", "serial" })
public class NewsModel extends BaseModel {
	public final static String SHOW_IN_HOME = "Home";
	/* Use for search new */
	private String keyWord;
	/* new type */
	private String type;
	private String newsType;
	/* show in page */
	private String showin = "";
	/* type of display */
	private String display = "";
	/* list symbol */
	private String listSymbol;

	private String pageUrl;

	/* Latest news for new type specific */
	private IfoNews ifoNews;
	/* NewsID provides for view detail news */
	private long newsId;
	
	private long totalPage;
	
	/* Symbol character */
	private String symbols = "";
	/* Search info news */
	private SearchIfoNews searchIfoNews = new SearchIfoNews();
	/* search result */
	private SearchResult searchResult;
	/* search news most */
	private SearchResult searchMostView;

	/* message */
	private String messageFromNewsDate;
	private String messageToNewsDate;

	/* sector group code */
	private String sectorGroupCode;
	/* industry group code */
	private String industryGroupCode;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public SearchIfoNews getSearchIfoNews() {
		return searchIfoNews;
	}

	public void setSearchIfoNews(SearchIfoNews searchIfoNews) {
		this.searchIfoNews = searchIfoNews;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the newsType
	 */
	public String getNewsType() {
		return newsType;
	}

	/**
	 * @param newsType
	 *            the newsType to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getShowin() {
		return showin;
	}

	public void setShowin(String showin) {
		this.showin = showin;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getListSymbol() {
		return listSymbol;
	}

	public void setListSymbol(String listSymbol) {
		this.listSymbol = listSymbol;
	}

	public IfoNews getIfoNews() {
		return ifoNews;
	}

	public void setIfoNews(IfoNews ifoNews) {
		this.ifoNews = ifoNews;
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {

		this.newsId = newsId;
		if (ifoNews == null) {
			ifoNews = new IfoNews();
		}

		ifoNews.setNewsId(new Long(newsId));
	}

	public String getSymbols() {
		return symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	/**
	 * Checking this news shows in home page
	 * 
	 * @return TRUE show in home; FALSE Otherwise
	 */
	public boolean isShowInHome() {
		return SHOW_IN_HOME.equals(this.showin);
	}

	/* message */
	public String getMessageFromNewsDate() {
		return messageFromNewsDate;
	}

	public void setMessageFromNewsDate(String messageFromNewsDate) {
		this.messageFromNewsDate = messageFromNewsDate;
	}

	public String getMessageToNewsDate() {
		return messageToNewsDate;
	}

	public void setMessageToNewsDate(String messageToNewsDate) {
		this.messageToNewsDate = messageToNewsDate;
	}

	/**
	 * @return the sectorGroupCode
	 */
	public String getSectorGroupCode() {
		return sectorGroupCode;
	}

	/**
	 * @param sectorGroupCode
	 *            the sectorGroupCode to set
	 */
	public void setSectorGroupCode(String sectorGroupCode) {
		this.sectorGroupCode = sectorGroupCode;
	}

	/**
	 * @return the industryGroupCode
	 */
	public String getIndustryGroupCode() {
		return industryGroupCode;
	}

	/**
	 * @param industryGroupCode
	 *            the industryGroupCode to set
	 */
	public void setIndustryGroupCode(String industryGroupCode) {
		this.industryGroupCode = industryGroupCode;
	}

	public SearchResult getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public SearchResult getSearchMostView() {
		return searchMostView;
	}

	public void setSearchMostView(SearchResult searchMostView) {
		this.searchMostView = searchMostView;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
}

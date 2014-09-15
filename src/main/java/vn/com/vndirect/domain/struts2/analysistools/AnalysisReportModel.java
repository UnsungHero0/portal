/**
 * 
 */
package vn.com.vndirect.domain.struts2.analysistools;

import java.util.Date;

import vn.com.vndirect.domain.BaseModel;
import vn.com.vndirect.domain.IfoNews;
import vn.com.vndirect.domain.extend.SearchIfoNews;
import vn.com.web.commons.domain.db.SearchResult;

import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * @author Huy
 * 
 */
public class AnalysisReportModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125828439072668348L;
	private IfoNews ifoNews;
	private SearchResult<SearchIfoNews> searchResult;
	private SearchResult<SearchIfoNews> searchMostView;
	private String newsUrl;

	private String newsType;
	private String attachmentId;

	private Date date;

	/**
	 * @return the searchResult
	 */
	public SearchResult<?> getSearchResult() {
		return searchResult;
	}

	/**
	 * @param searchResult
	 *            the searchResult to set
	 */
	public void setSearchResult(SearchResult<SearchIfoNews> searchResult) {
		this.searchResult = searchResult;
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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	@TypeConversion(converter = "vn.com.vndirect.commons.convert.DateTimeConverter")
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the attachmentId
	 */
	public String getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId
	 *            the attachmentId to set
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public IfoNews getIfoNews() {
		return ifoNews;
	}

	public void setIfoNews(IfoNews ifoNews) {
		this.ifoNews = ifoNews;
	}

	public SearchResult<SearchIfoNews> getSearchMostView() {
		return searchMostView;
	}

	public void setSearchMostView(SearchResult<SearchIfoNews> searchMostView) {
		this.searchMostView = searchMostView;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

}

/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Sep 10, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.google.code.ssm.api.CacheKeyMethod;

import vn.com.vndirect.domain.IfoNews;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class SearchIfoNews extends IfoNews {
	/**
	 * 
	 */

	private String strNewsDate;

	private String strFromNewsDate;

	private String strToNewsDate;

	private Date fromNewsDate;

	private Date toNewsDate;

	private Long companyId;

	private String strSymbol;

	private boolean orderByDate;

	private String strNewsId;

	private Collection newsTypeList;

	private String[] listSymbols;

	private String[] listIndustriesCode;

	private String sectorGroupCode;

	private String industryGroupCode;

	private Long hit;

	private String filePath;

	/**
	 * Using caching news for latest news
	 */
	private boolean checkInCache = false;

	public Collection getNewsTypeList() {
		return newsTypeList;
	}

	public void setNewsTypeList(Collection newsTypeList) {
		this.newsTypeList = newsTypeList;
	}

	public String getStrNewsId() {
		return strNewsId;
	}

	public void setStrNewsId(String strNewsId) {
		this.strNewsId = strNewsId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public boolean isOrderByDate() {
		return orderByDate;
	}

	public void setOrderByDate(boolean orderByDate) {
		this.orderByDate = orderByDate;
	}

	public String getStrSymbol() {
		return strSymbol;
	}

	public void setStrSymbol(String strSymbol) {
		this.strSymbol = strSymbol;
	}

	public Date getFromNewsDate() {
		return fromNewsDate;
	}

	public void setFromNewsDate(Date fromNewsDate) {
		this.fromNewsDate = fromNewsDate;
	}

	public String getStrFromNewsDate() {
		return strFromNewsDate;
	}

	public void setStrFromNewsDate(String strFromNewsDate) {
		this.strFromNewsDate = strFromNewsDate;
	}

	public String getStrNewsDate() {
		return strNewsDate;
	}

	public void setStrNewsDate(String strNewsDate) {
		this.strNewsDate = strNewsDate;
	}

	public String getStrToNewsDate() {
		return strToNewsDate;
	}

	public void setStrToNewsDate(String strToNewsDate) {
		this.strToNewsDate = strToNewsDate;
	}

	public Date getToNewsDate() {
		return toNewsDate;
	}

	public void setToNewsDate(Date toNewsDate) {
		this.toNewsDate = toNewsDate;
	}

	/**
	 * @return the listSymbols
	 */
	public String[] getListSymbols() {
		return this.listSymbols;
	}

	/**
	 * @param listSymbols
	 *            the listSymbols to set
	 */
	public void setListSymbols(String[] listSymbols) {
		this.listSymbols = listSymbols;
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

	/**
	 * @return the checkInCache
	 */
	public boolean getCheckInCache() {
		return this.checkInCache;
	}

	/**
	 * @param checkInCache
	 *            the checkInCache to set
	 */
	public void setCheckInCache(boolean checkInCache) {
		this.checkInCache = checkInCache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.companyId == null) ? 0 : this.companyId.hashCode());
		result = prime * result + ((this.fromNewsDate == null) ? 0 : this.fromNewsDate.hashCode());
		result = prime * result + Arrays.hashCode(this.listSymbols);
		result = prime * result + ((this.newsTypeList == null) ? 0 : this.newsTypeList.hashCode());
		result = prime * result + (this.orderByDate ? 1231 : 1237);
		result = prime * result + ((this.strFromNewsDate == null) ? 0 : this.strFromNewsDate.hashCode());
		result = prime * result + ((this.strNewsDate == null) ? 0 : this.strNewsDate.hashCode());
		result = prime * result + ((this.strNewsId == null) ? 0 : this.strNewsId.hashCode());
		result = prime * result + ((this.strSymbol == null) ? 0 : this.strSymbol.hashCode());
		result = prime * result + ((this.strToNewsDate == null) ? 0 : this.strToNewsDate.hashCode());
		result = prime * result + ((this.toNewsDate == null) ? 0 : this.toNewsDate.hashCode());
		result = prime * result + ((this.sectorGroupCode == null) ? 0 : this.sectorGroupCode.hashCode());
		result = prime * result + ((this.industryGroupCode == null) ? 0 : this.industryGroupCode.hashCode());

		return result;
	}

	/**
	 * Use for memcache
	 */
	@CacheKeyMethod
	public String strHashCode() {
		return String.valueOf(hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof SearchIfoNews))
			return false;
		final SearchIfoNews other = (SearchIfoNews) obj;
		if (this.companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!this.companyId.equals(other.companyId))
			return false;
		if (this.fromNewsDate == null) {
			if (other.fromNewsDate != null)
				return false;
		} else if (!this.fromNewsDate.equals(other.fromNewsDate))
			return false;
		if (!Arrays.equals(this.listSymbols, other.listSymbols))
			return false;
		if (this.newsTypeList == null) {
			if (other.newsTypeList != null)
				return false;
		} else if (!this.newsTypeList.equals(other.newsTypeList))
			return false;
		if (this.orderByDate != other.orderByDate)
			return false;
		if (this.strFromNewsDate == null) {
			if (other.strFromNewsDate != null)
				return false;
		} else if (!this.strFromNewsDate.equals(other.strFromNewsDate))
			return false;
		if (this.strNewsDate == null) {
			if (other.strNewsDate != null)
				return false;
		} else if (!this.strNewsDate.equals(other.strNewsDate))
			return false;
		if (this.strNewsId == null) {
			if (other.strNewsId != null)
				return false;
		} else if (!this.strNewsId.equals(other.strNewsId))
			return false;
		if (this.strSymbol == null) {
			if (other.strSymbol != null)
				return false;
		} else if (!this.strSymbol.equals(other.strSymbol))
			return false;
		if (this.strToNewsDate == null) {
			if (other.strToNewsDate != null)
				return false;
		} else if (!this.strToNewsDate.equals(other.strToNewsDate))
			return false;
		if (this.toNewsDate == null) {
			if (other.toNewsDate != null)
				return false;
		} else if (!this.toNewsDate.equals(other.toNewsDate))
			return false;
		return true;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getListIndustriesCode() {
		return listIndustriesCode;
	}

	public void setListIndustriesCode(String[] listIndustriesCode) {
		this.listIndustriesCode = listIndustriesCode;
	}

	// public String getUrlDetail() {
	// return urlDetail;
	// }

	// public void setUrlDetail(String urlDetail) {
	// this.urlDetail = urlDetail;
	// }

}

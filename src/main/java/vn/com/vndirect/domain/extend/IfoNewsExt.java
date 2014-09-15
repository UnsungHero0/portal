/*--------------------------------------------------------------------------*
| Modification Logs:
| DATE          AUTHOR     DESCRIPTION
| ------------------------------------------------
| Sep 7, 2006      TungNQ     First Creation
 *--------------------------------------------------------------------------*/

package vn.com.vndirect.domain.extend;

import vn.com.vndirect.domain.IfoNews;

/**
 * @author tungnq
 * 
 */
@SuppressWarnings( { "unused", "serial" })
public class IfoNewsExt extends IfoNews {
	private String strNewsDate;
	private String strNewsSource;
	private boolean hasVideoAttach;

	public IfoNewsExt() {
	}

	public IfoNewsExt(IfoNews ifoNews) {
		if (ifoNews != null) {
			this.populate(ifoNews, this);
		}
	}

	public IfoNews getIfoNews() throws Exception {
		IfoNews ifoNews = new IfoNews();
		this.populate(this, ifoNews);
		return ifoNews;
	}

	public void populate(IfoNews srcIfoNews, IfoNews desIfoNews) {
		if (srcIfoNews != null && desIfoNews != null) {
			desIfoNews.setNewsId(srcIfoNews.getNewsId());
			desIfoNews.setNewsHeader(srcIfoNews.getNewsHeader());
			desIfoNews.setNewsAbstract(srcIfoNews.getNewsAbstract());
			desIfoNews.setNewsContent(srcIfoNews.getNewsContent());
			desIfoNews.setNewsType(srcIfoNews.getNewsType());

			desIfoNews.setNewsDate(srcIfoNews.getNewsDate());
			desIfoNews.setNewsRank(srcIfoNews.getNewsRank());
			desIfoNews.setStatus(srcIfoNews.getStatus());

			desIfoNews.setCreatedDate(srcIfoNews.getCreatedDate());
			desIfoNews.setCreatedBy(srcIfoNews.getCreatedBy());
			desIfoNews.setModifiedBy(srcIfoNews.getModifiedBy());
			desIfoNews.setModifiedDate(srcIfoNews.getModifiedDate());
			desIfoNews.setLocale(srcIfoNews.getLocale());
			desIfoNews.setNewsResource(srcIfoNews.getNewsResource());

			desIfoNews.setIfoAccessType(srcIfoNews.getIfoAccessType());

			desIfoNews.setIfoCategoryNews(srcIfoNews.getIfoCategoryNews());
			desIfoNews.setAttachmentNews(srcIfoNews.getAttachmentNews());

		}
	}

	public String getStrNewsDate() {
		return strNewsDate;
	}

	public void setStrNewsDate(String strNewsDate) {
		this.strNewsDate = strNewsDate;
	}

	/**
	 * @return the strNewsSource
	 */
	public String getStrNewsSource() {
		return strNewsSource;
	}

	/**
	 * @param strNewsSource
	 *            the strNewsSource to set
	 */
	public void setStrNewsSource(String strNewsSource) {
		this.strNewsSource = strNewsSource;
	}

}

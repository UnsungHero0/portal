package vn.com.vndirect.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.code.ssm.api.CacheKeyMethod;

import vn.com.web.commons.utility.DateUtils;
import vn.com.web.commons.utility.Validation;

@SuppressWarnings( { "unchecked", "serial" })
public class IfoNews extends BaseBean implements java.io.Serializable {
	private Long newsId;
	private IfoAccessType ifoAccessType;
	private String newsType;
	private String newsHeader;
	private String newsAbstract;
	private String newsContent;
	private Date newsDate;
	private Long newsRank;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;
	private String status;
	private String newsResource;
	private String resource;
	private String locale;
	private String isHotNews;
	private Collection ifoCategoryNews = new ArrayList();
	private List attachmentNews = new ArrayList();
	private String description;
	private String isFlvNews;
	private String newsTypeDesc;
	private String urlDetail;

	private Long hit;

	// Constructors

	/**
	 * @return the hit
	 */
	public Long getHit() {
		return hit;
	}

	/**
	 * @param hit
	 *            the hit to set
	 */
	public void setHit(Long hit) {
		this.hit = hit;
	}

	public String getIsFlvNews() {
		return isFlvNews;
	}

	public void setIsFlvNews(String isFlvNews) {
		this.isFlvNews = isFlvNews;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/** default constructor */
	public IfoNews() {
	}

	public IfoNews(Long newsId) {
		this.newsId = newsId;
	}

	/**
	 * Get the list of PDF file
	 * 
	 * @return
	 */
	public List<String> getPdfFileList() {
		int size = getAttachmentNews().size();
		List<String> pdfs = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String link = getAttachmentNews().get(i).getOriginalLink();// .getUriLink();
			if (StringUtils.isNotBlank(link)
					&& (link.toUpperCase().endsWith(".PDF") || link.toUpperCase().endsWith(".DOC") || link.toUpperCase().endsWith(".DOCX") || link.toUpperCase().endsWith(".XLS")
							|| link.toUpperCase().endsWith(".XLSX") || link.toUpperCase().endsWith(".ZIP") || link.toUpperCase().endsWith(".RAR"))) {
				pdfs.add(link);
			}
		}
		return pdfs;
	}

	public List<String> getPdfFileNames() {
		int size = getAttachmentNews().size();
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String filename = getAttachmentNews().get(i).getFileName();
			if (StringUtils.isNotBlank(filename)
					&& (filename.toUpperCase().endsWith(".PDF") || filename.toUpperCase().endsWith(".DOC") || filename.toUpperCase().endsWith(".DOCX") || filename.toUpperCase().endsWith(".XLS")
							|| filename.toUpperCase().endsWith(".XLSX") || filename.toUpperCase().endsWith(".ZIP") || filename.toUpperCase().endsWith(".RAR"))) {
				lst.add(filename);
			}
		}
		return lst;
	}

	/**
	 * Get the list of file name
	 * 
	 * @return list of names
	 */
	public List<String> getFileNames() {
		int size = getAttachmentNews().size();
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String filename = getAttachmentNews().get(i).getFileName();
			if (StringUtils.isNotBlank(filename) && !filename.toUpperCase().endsWith(".FLV")) {
				lst.add(filename);
			}
		}
		return lst;
	}

	/**
	 * Get the list of file name
	 * 
	 * @return list of names
	 */
	public List<String> getAttachmentsId() {
		int size = getAttachmentNews().size();
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			String filename = getAttachmentNews().get(i).getFileName();
			if (StringUtils.isNotBlank(filename) && !filename.toUpperCase().endsWith(".FLV")) {
				lst.add(getAttachmentNews().get(i).getAttachmentsId() + "");
			}
		}
		return lst;
	}

	public List<String> getImagesList() {
		int size = getAttachmentNews().size();
		List<String> imgList = new ArrayList<String>();
		String link;
		String attachmentType;
		for (int i = 0; i < size; i++) {
			link = getAttachmentNews().get(i).getOriginalLink();// .getUriLink();
			if (StringUtils.isNotBlank(link) && (link.toUpperCase().endsWith(".JPG") || link.toUpperCase().endsWith(".GIF") || link.toUpperCase().endsWith(".PNG"))) {
				attachmentType = getAttachmentNews().get(i).getAttachmentType();
				imgList.add(attachmentType + "||" + link);
			}
		}
		return imgList;
	}

	/**
	 * @return the newsTypeDesc
	 */
	public String getNewsTypeDesc() {
		return newsTypeDesc;
	}

	/**
	 * @param newsTypeDesc
	 *            the newsTypeDesc to set
	 */
	public void setNewsTypeDesc(String newsTypeDesc) {
		this.newsTypeDesc = newsTypeDesc;
	}

	/** minimal constructor */
	public IfoNews(IfoAccessType ifoAccessType) {
		this.ifoAccessType = ifoAccessType;
	}

	/** full constructor */
	public IfoNews(IfoAccessType ifoAccessType, String newsType, String newsHeader, String newsAbstract, String newsContent, Date newsDate, Long newsRank, Date createdDate, String createdBy,
			Date modifiedDate, String modifiedBy, String status, Set ifoCompanyNewses) {
		this.ifoAccessType = ifoAccessType;
		this.newsType = newsType;
		this.newsHeader = newsHeader;
		this.newsAbstract = newsAbstract;
		this.newsContent = newsContent;
		this.newsDate = newsDate;
		this.newsRank = newsRank;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.status = status;
	}

	public IfoNews(String newsHeader, String newsAbstract) {
		this.newsHeader = newsHeader;
		this.newsAbstract = newsAbstract;
	}

	public IfoNews(Long newsId, String newsHeader, String newsSource, String newsType, Date newsDate, String status) {
		this.newsId = newsId;
		this.newsResource = newsSource;
		this.newsType = newsType;
		this.newsHeader = newsHeader;
		this.newsDate = newsDate;
		this.status = status;
	}

	// Property accessors

	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public IfoAccessType getIfoAccessType() {
		return this.ifoAccessType;
	}

	public void setIfoAccessType(IfoAccessType ifoAccessType) {
		this.ifoAccessType = ifoAccessType;
	}

	public String getNewsType() {
		return this.newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsHeader() {
		return this.newsHeader;
	}

	public void setNewsHeader(String newsHeader) {
		this.newsHeader = newsHeader;
	}

	public String getNewsAbstract() {
		return this.newsAbstract;
	}

	public void setNewsAbstract(String newsAbstract) {
		this.newsAbstract = newsAbstract;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getNewsDate() {
		return this.newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public Long getNewsRank() {
		return this.newsRank;
	}

	public void setNewsRank(Long newsRank) {
		this.newsRank = newsRank;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		if (locale != null && "vn".equalsIgnoreCase(locale))
			return "VN";
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the resourceNews
	 */
	public String getNewsResource() {
		return newsResource == null ? "" : newsResource;
	}

	/**
	 * @param resourceNews
	 *            the resourceNews to set
	 */
	public void setNewsResource(String resourceNews) {
		this.newsResource = resourceNews;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource == null ? "" : resource;
	}

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * 
	 * @return
	 */
	public String getNewsDateStr() {
		String strDate = "";
		try {
			strDate = DateUtils.dateToString(this.newsDate, DateUtils.Formats.STR_DATE_TIME_FORMAT_DDMMYYYY);
		} catch (Exception e) {
		}
		return strDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getNewsDateTimeStr() {
		String strDate = "";
		try {
			strDate = DateUtils.dateToString(this.newsDate, DateUtils.Formats.STR_TIME_FORMAT);
		} catch (Exception e) {
		}
		return strDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getDisplayNewsDate() {
		String strDate = "";
		try {
			strDate = DateUtils.dateToString(this.newsDate, "dd/MM");
		} catch (Exception e) {
		}
		return strDate;
	}

	/**
	 * 
	 * @return
	 */
	public Collection getIfoCategoryNews() {
		return ifoCategoryNews;
	}

	/**
	 * 
	 * @param ifoCategoryNews
	 */
	public void setIfoCategoryNews(Collection ifoCategoryNews) {
		this.ifoCategoryNews = ifoCategoryNews;
	}

	/**
	 * 
	 * @return
	 */
	public String getStrNewsResource() {
		return (this.newsResource == null) ? "" : newsResource;
	}

	/**
	 * 
	 * @author hanhbq
	 * @return
	 */
	public boolean getHasVideoAtt() {
		// check has video attach
		if (!this.getAttachmentNews().isEmpty()) {
			for (Iterator item = this.getAttachmentNews().iterator(); item.hasNext();) {
				NewsAttachments newsAtt = (NewsAttachments) item.next();
				if (newsAtt != null && !Validation.isEmpty(newsAtt.getOriginalLink()) && newsAtt.getOriginalLink().toUpperCase().endsWith(".FLV")) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * @return
	 */
	public List<NewsAttachments> getAttachmentVideoNews() {
		List<NewsAttachments> newsAttVideos = new ArrayList<NewsAttachments>();
		for (Iterator item = this.getAttachmentNews().iterator(); item.hasNext();) {
			NewsAttachments newsAtt = (NewsAttachments) item.next();
			if (newsAtt != null && !Validation.isEmpty(newsAtt.getOriginalLink()) && newsAtt.getOriginalLink().toUpperCase().endsWith(".FLV")) {
				newsAttVideos.add(newsAtt);
			}
		}
		return newsAttVideos;
	}

	/**
	 * @return
	 */
	public Long getAttachmentVideoHit() {
		Long hit = new Long(0);
		for (Iterator item = this.getAttachmentNews().iterator(); item.hasNext();) {
			NewsAttachments newsAtt = (NewsAttachments) item.next();
			if (newsAtt != null && StringUtils.isNotBlank(newsAtt.getOriginalLink()) && newsAtt.getOriginalLink().toUpperCase().endsWith(".FLV")) {
				hit = newsAtt.getHit();
			}
		}
		return hit;
	}

	/**
	 * @return
	 */
	public String getAttachmentVideoCapacity() {
		String capacity = "";
		for (Iterator item = this.getAttachmentNews().iterator(); item.hasNext();) {
			NewsAttachments newsAtt = (NewsAttachments) item.next();
			if (newsAtt != null && StringUtils.isNotBlank(newsAtt.getOriginalLink()) && newsAtt.getOriginalLink().toUpperCase().endsWith(".FLV")) {
				capacity = newsAtt.getCapacity();
			}
		}
		return capacity == null ? "" : capacity;
	}

	/**
	 * 
	 * @return
	 */
	public List getNewsAttWithoutVideos() {
		List newsAttWithoutVideos = new ArrayList();
		for (Iterator item = this.getAttachmentNews().iterator(); item.hasNext();) {
			NewsAttachments newsAtt = (NewsAttachments) item.next();
			if (newsAtt != null && !Validation.isEmpty(newsAtt.getOriginalLink()) && !newsAtt.getOriginalLink().toUpperCase().endsWith(".FLV")
					&& !newsAtt.getOriginalLink().toUpperCase().endsWith(".JPG") && !newsAtt.getOriginalLink().toUpperCase().endsWith(".GIF")
					&& !newsAtt.getOriginalLink().toUpperCase().endsWith(".PNG")) {
				newsAttWithoutVideos.add(newsAtt);
			}
		}
		return newsAttWithoutVideos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	// public String toString() {
	// ToStringBuilder sb = new ToStringBuilder(this,
	// ToStringStyle.DEFAULT_STYLE).append("createdBy", this.createdBy)
	// .append("createdDate", this.createdDate).append("modifiedBy", this.modifiedBy)
	// .append("modifiedDate", this.modifiedDate).append("newsAbstract", this.newsAbstract)
	// .append("newsContent", this.newsContent).append("newsDate", this.newsDate)
	// .append("newsHeader", this.newsHeader).append("newsId", this.newsId)
	// .append("newsRank", this.newsRank).append("newsResource", this.newsResource)
	// .append("newsType", this.newsType);
	// return sb.toString();
	// }

	public List<NewsAttachments> getAttachmentNews() {
		return attachmentNews;
	}

	public void setAttachmentNews(List<NewsAttachments> attachmentNews) {
		this.attachmentNews = attachmentNews;
	}

	public String getIsHotNews() {
		return isHotNews;
	}

	public void setIsHotNews(String isHotNews) {
		this.isHotNews = isHotNews;
	}

	public boolean isHotVideo() {
		if (this.newsDate == null)
			return false;

		Calendar currentTime = Calendar.getInstance();
		currentTime.setTimeInMillis(System.currentTimeMillis());
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(this.newsDate);
		newDate.add(Calendar.HOUR, 12);
		return currentTime.before(newDate);
	}

	public boolean isHotResearch() {
		if (this.newsDate == null)
			return false;

		Calendar currentTime = Calendar.getInstance();
		currentTime.setTimeInMillis(System.currentTimeMillis());
		Calendar newDate = Calendar.getInstance();
		newDate.setTime(this.newsDate);
		newDate.add(Calendar.HOUR, 48);
		return currentTime.before(newDate);
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
		result = prime * result + ((this.createdBy == null) ? 0 : this.createdBy.hashCode());
		result = prime * result + ((this.createdDate == null) ? 0 : this.createdDate.hashCode());
		result = prime * result + ((this.isHotNews == null) ? 0 : this.isHotNews.hashCode());
		result = prime * result + ((this.locale == null) ? 0 : this.locale.hashCode());
		result = prime * result + ((this.modifiedBy == null) ? 0 : this.modifiedBy.hashCode());
		result = prime * result + ((this.modifiedDate == null) ? 0 : this.modifiedDate.hashCode());
		result = prime * result + ((this.newsAbstract == null) ? 0 : this.newsAbstract.hashCode());
		result = prime * result + ((this.newsContent == null) ? 0 : this.newsContent.hashCode());
		result = prime * result + ((this.newsDate == null) ? 0 : this.newsDate.hashCode());
		result = prime * result + ((this.newsHeader == null) ? 0 : this.newsHeader.hashCode());
		result = prime * result + ((this.newsId == null) ? 0 : this.newsId.hashCode());
		result = prime * result + ((this.newsRank == null) ? 0 : this.newsRank.hashCode());
		result = prime * result + ((this.newsResource == null) ? 0 : this.newsResource.hashCode());
		result = prime * result + ((this.newsType == null) ? 0 : this.newsType.hashCode());
		result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		if (!(obj instanceof IfoNews))
			return false;
		final IfoNews other = (IfoNews) obj;
		if (this.createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!this.createdBy.equals(other.createdBy))
			return false;
		if (this.createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!this.createdDate.equals(other.createdDate))
			return false;
		if (this.isHotNews == null) {
			if (other.isHotNews != null)
				return false;
		} else if (!this.isHotNews.equals(other.isHotNews))
			return false;
		if (this.locale == null) {
			if (other.locale != null)
				return false;
		} else if (!this.locale.equals(other.locale))
			return false;
		if (this.modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!this.modifiedBy.equals(other.modifiedBy))
			return false;
		if (this.modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!this.modifiedDate.equals(other.modifiedDate))
			return false;
		if (this.newsAbstract == null) {
			if (other.newsAbstract != null)
				return false;
		} else if (!this.newsAbstract.equals(other.newsAbstract))
			return false;
		if (this.newsContent == null) {
			if (other.newsContent != null)
				return false;
		} else if (!this.newsContent.equals(other.newsContent))
			return false;
		if (this.newsDate == null) {
			if (other.newsDate != null)
				return false;
		} else if (!this.newsDate.equals(other.newsDate))
			return false;
		if (this.newsHeader == null) {
			if (other.newsHeader != null)
				return false;
		} else if (!this.newsHeader.equals(other.newsHeader))
			return false;
		if (this.newsId == null) {
			if (other.newsId != null)
				return false;
		} else if (!this.newsId.equals(other.newsId))
			return false;
		if (this.newsRank == null) {
			if (other.newsRank != null)
				return false;
		} else if (!this.newsRank.equals(other.newsRank))
			return false;
		if (this.newsResource == null) {
			if (other.newsResource != null)
				return false;
		} else if (!this.newsResource.equals(other.newsResource))
			return false;
		if (this.newsType == null) {
			if (other.newsType != null)
				return false;
		} else if (!this.newsType.equals(other.newsType))
			return false;
		if (this.status == null) {
			if (other.status != null)
				return false;
		} else if (!this.status.equals(other.status))
			return false;
		return true;
	}

	public String getUrlDetail() {
		return urlDetail;
	}

	public void setUrlDetail(String urlDetail) {
		this.urlDetail = urlDetail;
	}
}

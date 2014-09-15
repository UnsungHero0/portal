package vn.com.vndirect.domain;

@SuppressWarnings("serial")
public class NewsAttachments extends BaseBean {
	private Long attachmentsId;
	private Long newsId;
	private String originalLink;
	private String uriLink;
	private String description;
	private String fileName;
	private String attachmentType;
	private Long hit;
	private String capacity;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public String getDes4Js() {
		final String NCR = "&#";
		StringBuffer result = new StringBuffer();
		int i;
		for (i = 0; i < description.length(); i++) {
			result.append(NCR + this.description.codePointAt(i) + ";");
		}

		return result.toString();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAttachmentsId() {
		return attachmentsId;
	}

	public void setAttachmentsId(Long attachmentsId) {
		this.attachmentsId = attachmentsId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getOriginalLink() {
		return originalLink;
	}

	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}

	public String getUriLink() {
		return uriLink;
	}

	public void setUriLink(String uriLink) {
		this.uriLink = uriLink;
	}

	/**
	 * @return the attachmentType
	 */
	public String getAttachmentType() {
		return attachmentType;
	}

	/**
	 * @param attachmentType
	 *            the attachmentType to set
	 */
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

}

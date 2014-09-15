package vn.com.vndirect.domain;

public class CaptchaImageModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3380473101319862016L;
	private String pid;

	private String flavor;

	private String fileName = "_captcha.png";

	/**
	 * @return the flavor
	 */
	public String getFlavor() {
		return flavor;
	}

	/**
	 * @param flavor
	 *            the flavor to set
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return System.nanoTime() + fileName;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

}

/**
 * 
 */
package vn.com.vndirect.commons;

/**
 * @author Huy
 * 
 */
public enum Code {
	SECTOR("SECTOR_CODE"), INDUSTRY("INDUSTRY_CODE");
	private String code;

	private Code(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

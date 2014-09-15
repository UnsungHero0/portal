/**
 * 
 */
package vn.com.vndirect.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import vn.com.web.commons.utility.NumberFormatter;

/**
 * @author tungnq.nguyen
 * 
 */
public class ConvertFileSizeTag extends TagSupport implements IWebTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2611503507083442074L;
	private String size;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String str = null;
		try {
			if (size != null) {
				double dsize = Double.valueOf(size);
				String suffix = "";
				if (StorageSize.Byte.size <= dsize && dsize < StorageSize.K.size) {
					suffix = StorageSize.Byte.desc;
				} else if (StorageSize.K.size <= dsize && dsize < StorageSize.M.size) {
					dsize = (dsize / StorageSize.K.size);
					suffix = StorageSize.K.desc;
				} else if (StorageSize.M.size <= dsize && dsize < StorageSize.G.size) {
					dsize = (dsize / StorageSize.M.size);
					suffix = StorageSize.M.desc;
				} else {
					dsize = (dsize / StorageSize.G.size);
					suffix = StorageSize.G.desc;
				}
				str = NumberFormatter.format(dsize, NumberFormatter.Type.NUMBER, NumberFormatter.Pattern.PATTERN_2);
				str += suffix;
			}
		} catch (Exception e) {
		}
		try {
			pageContext.getOut().print(str == null ? "" : str);
		} catch (Exception e) {
		}
		return SKIP_BODY;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

}
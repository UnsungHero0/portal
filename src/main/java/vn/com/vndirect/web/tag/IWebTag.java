/**
 * 
 */
package vn.com.vndirect.web.tag;

/**
 * @author tungnq.nguyen
 * 
 */
public interface IWebTag {
	String PAGE = "page";
	String REQUEST = "request";

	/**
	 * 
	 * @author tungnq.nguyen
	 * 
	 */
	public enum StorageSize {
		Byte(1, "Byte"), K(1000, "KB"), M(1000 * K.size, "KB"), G(1000 * M.size, "KB");

		public int size;
		public String desc;

		/**
		 * 
		 * @param _size
		 * @param _desc
		 */
		StorageSize(int _size, String _desc) {
			size = _size;
			desc = _desc;
		}
	}
}

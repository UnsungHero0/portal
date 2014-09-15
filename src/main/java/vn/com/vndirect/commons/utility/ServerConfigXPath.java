/**
 * 
 */
package vn.com.vndirect.commons.utility;

/**
 * @author tungnq.nguyen
 * 
 */
public interface ServerConfigXPath {
	public interface FileServer {
		public interface ImgThumbnail {
			String TYPES = "/server-config/files-server/thumbnail/types";
			String FOLDER = "/server-config/files-server/thumbnail/folder";
		}

		public interface Documents {
			String TYPES = "/server-config/files-server/documents/types";
			String FOLDER = "/server-config/files-server/documents/folder";
		}

		public interface Templates {
			String TYPES = "/server-config/files-server/templates/types";
			String FOLDER = "/server-config/files-server/templates/folder";
		}

		public interface Reports {
			String TYPES = "/server-config/files-server/reports/types";
			String FOLDER = "/server-config/files-server/reports/folder";
		}
	}

	public interface PaginInfo {
		String CATEGORY_NUMBER = "/server-config/paging-info/category-number";
	}

	public interface Forum {
		String VIEW_THREAD_URL = "/server-config/forum/view-thread-url";
	}
}

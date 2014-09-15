package vn.com.vndirect.commons.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

/**
 * @author minh.nguyen
 * 
 */
public class VelocityTemplateUtility {
	private static final int EXPIRATION_CACHE_TIME = 3600;
	private static Logger logger = Logger.getLogger(VelocityTemplateUtility.class);

	/**
	 * @param filePath
	 * @param usingCache
	 * @return
	 */
	public static StringReader getTemplateContent(String filePath, Boolean isUsingCache) {
		final String location = "getTemplateContent(filePath:" + filePath + ",isUsingCache:" + isUsingCache + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(location + "::BEGIN");
		}
		StringReader result = null;
		if (StringUtils.isNotEmpty(filePath)) {
			if (!isUsingCache) {
				try {
					result = new StringReader(getFileContent(new File(filePath)));
				} catch (IOException e) {
					logger.error(location, e);
					return null;
				}
			} else {
				result = getTemplateContent(filePath);
			}
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(location + "::END");
		}

		return result;
	}

	/**
	 * @param filePath
	 * @return
	 */
	@ReadThroughSingleCache(namespace = "VelocityTemplate.getTemplateContent@", expiration = EXPIRATION_CACHE_TIME)
	public static StringReader getTemplateContent(@ParameterValueKeyProvider String filePath) {
		final String location = "getTemplateContent(filePath:" + filePath + ")";
		if (logger.isDebugEnabled()) {
			logger.debug(location + "::BEGIN");
		}
		StringReader result = null;
		if (StringUtils.isNotEmpty(filePath)) {
			try {
				result = new StringReader(getFileContent(new File(filePath)));
			} catch (IOException e) {
				logger.error(location, e);
				return null;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug(location + "::END");
		}

		return result;
	}

	/**
	 * getFileContent
	 * 
	 * @param file
	 * @throws IOException
	 */
	private static String getFileContent(final File file) throws IOException {
		FileInputStream fin = null;
		InputStreamReader in = null;
		BufferedReader rdr = null;
		try {
			int i;
			int _MB = 1024; // 1M
			StringBuffer str = new StringBuffer();
			char[] charTmp = new char[_MB];
			fin = new FileInputStream(file);
			in = new InputStreamReader(fin, "UTF-8");
			rdr = new BufferedReader(in);
			while ((i = rdr.read(charTmp, 0, _MB)) != -1) {
				str.append(new String(charTmp, 0, i));
			}
			return str.toString();
		} catch (Exception e) {
			logger.error("getFileContent()::", e);
			return null;
		} finally {
			if (rdr != null) {
				rdr.close();
			}

			if (in != null) {
				in.close();
			}

			if (fin != null) {
				fin.close();
			}
		}
	}
}

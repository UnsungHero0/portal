/**
 * 
 */
package vn.com.vndirect.commons.convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

/**
 * @author Huy
 * 
 */
@SuppressWarnings("rawtypes")
public class DateTimeConverter extends StrutsTypeConverter {

	private static final String DATE_TIME_FORMAT = "dd/MM/yyyy";

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values == null) {
			return null;
		}
		if (values.length != 1) {
			String msg = String.format("Array too big: [%s]", StringUtils.join(values, ", "));
			throw new TypeConversionException(msg);
		}
		try {
			return DateUtils.parseDate(values[0].trim(), new String[] { DATE_TIME_FORMAT });
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (!(o instanceof Date)) {
			String msg = String.format("No Date supplied; object was %s", o.getClass().getCanonicalName());
			throw new TypeConversionException(msg);
		}
		try {
			return new SimpleDateFormat(DATE_TIME_FORMAT).format((Date) o);
		} catch (Exception e) {
			return null;
		}
	}
}

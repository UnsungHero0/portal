package vn.com.vndirect.business;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;

import vn.com.vndirect.commons.reports.WebOpenAccountReportFactory;
import vn.com.vndirectreports.ReportManagerResolver;
import vn.com.vndirectreports.openaccount.PersonalAccount;

public class WebReportManager extends ReportManagerResolver {

	private static Logger logger = Logger.getLogger(WebReportManager.class);

	@Override
	public byte[] openRegOnlineTeleAccount(PersonalAccount acct) {
		final String LOCATION = "::openRegOnlineTeleAccount()";
		try {
			ByteArrayOutputStream out = WebOpenAccountReportFactory.regTeleReport(this.getOpenAccBaseFolder(), acct);
			return out.toByteArray();
		} catch (Exception e) {
			logger.error(LOCATION, e);
		}
		return new byte[0];
	}

	@Override
	public byte[] grantPowerTrading(PersonalAccount acct) {
		final String LOCATION = "::grantPowerTrading()";
		try {
			ByteArrayOutputStream out = WebOpenAccountReportFactory.grantPowerTrading(this.getOpenAccBaseFolder(), acct);
			return out.toByteArray();
		} catch (Exception e) {
			logger.error(LOCATION, e);
		}
		return new byte[0];
	}

	@Override
	public byte[] openPersonalAccount(PersonalAccount acct) {
		final String LOCATION = "::openPersonalAccount()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");
		try {
			ByteArrayOutputStream out = WebOpenAccountReportFactory.openPersonalAccountReport(this.getOpenAccBaseFolder(), acct);
			if (logger.isDebugEnabled())
				logger.debug(LOCATION + ":END");
			return out.toByteArray();
		} catch (Exception e) {
			logger.error(LOCATION, e);
		}
		return new byte[0];
	}
}

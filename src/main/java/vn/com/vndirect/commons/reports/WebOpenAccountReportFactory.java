package vn.com.vndirect.commons.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import vn.com.vndirectreports.Utils;
import vn.com.vndirectreports.openaccount.OpenAccountReportFactory;
import vn.com.vndirectreports.openaccount.PersonalAccount;

public class WebOpenAccountReportFactory extends OpenAccountReportFactory {

	private static Logger logger = Logger.getLogger(WebOpenAccountReportFactory.class);

	private final static String PERSONAL_ACCOUNT_PAGE1_NEW = "phieu-mo-tk-ca-nhan-tv-new";
	private final static String PERSONAL_ACCOUNT_PAGE2_NEW = "phieu-mo-tk-ca-nhan-tv-2-new";
	private final static String COMMON_PAGE1_NEW = "quy-dinh-new";
	private final static String COMMON_PAGE2_NEW = "quy-dinh-2-new";

	public final static String REGISTER_SERVICE_PAGE1 = "phieu-dang-ky-dich-vu-tv";
	public final static String REGISTER_SERVICE_PAGE2 = "phieu-dang-ky-dich-vu-tv-2";
	public final static String REGISTER_SERVICE_PAGE3 = "phieu-dang-ky-dich-vu-tv-3";
	public final static String REGISTER_SERVICE_PAGE4 = "phieu-dang-ky-dich-vu-tv-4";

	private final static String GRANT_POWER_TRADING_PAGE1 = "giay-uy-quyen-giao-dich-tv";
	private final static String GRANT_CONTENT_PAGE1 = "noi-dung-uy-quyen-tv";
	private final static String GRANT_CONTENT_PAGE2 = "noi-dung-uy-quyen-tv-2";
	private final static String GRANT_CONTENT_PAGE3 = "noi-dung-uy-quyen-tv-3";

	public final static String JASPER_FILE_EXTENSION = ".jasper";
	private final static String JRPRINT_FILE_EXTENSION = ".jrprint";

	public static ByteArrayOutputStream openPersonalAccountReport(String baseFolder, PersonalAccount pAcct) throws Exception {
		final String LOCATION = "::openPersonalAccountReport()";
		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":BEGIN");

		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> parameters2 = new HashMap<String, Object>();
		PersonalAccount[] pAcctList = { pAcct };
		JRBeanCollectionDataSource bean = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		JRBeanCollectionDataSource bean2 = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		// fill the report
		FileInputStream is = null;
		FileInputStream is2 = null;
		ByteArrayOutputStream arrayOutputStream = null;
		try {
			/*is = new FileInputStream(Utils.catString(baseFolder, PERSONAL_ACCOUNT_PAGE1_NEW + JASPER_FILE_EXTENSION));
			is2 = new FileInputStream(Utils.catString(baseFolder, PERSONAL_ACCOUNT_PAGE2_NEW + JASPER_FILE_EXTENSION));*/
			List<JasperPrint> printList = new LinkedList<JasperPrint>();
			
			/*ClassLoader cl= new URLClassLoader(new URL[] {new URL(new File(Utils.catString(baseFolder, "")).toURI().toURL().toString())} );
			parameters.put(JRParameter.REPORT_CLASS_LOADER, cl);
			parameters2.put(JRParameter.REPORT_CLASS_LOADER, cl);*/
			JasperPrint jasperPrint = JasperFillManager.fillReport(Utils.catString(baseFolder, PERSONAL_ACCOUNT_PAGE1_NEW + JASPER_FILE_EXTENSION), parameters, bean);
			printList.add(jasperPrint);
			printList.add((JasperPrint) JRLoader.loadObject(Utils
			        .catString(baseFolder, COMMON_PAGE1_NEW + JRPRINT_FILE_EXTENSION)));
			printList.add((JasperPrint) JRLoader.loadObject(Utils
			        .catString(baseFolder, COMMON_PAGE2_NEW + JRPRINT_FILE_EXTENSION)));
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(Utils.catString(baseFolder, PERSONAL_ACCOUNT_PAGE2_NEW + JASPER_FILE_EXTENSION), parameters2, bean2);
			printList.add(jasperPrint2);

			JRExporter exporter = new JRPdfExporter();
			arrayOutputStream = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			exporter.exportReport();
		} catch (Exception e) {
			logger.debug(LOCATION + ":Error: " + e);
			e.printStackTrace();
		} finally {
			Utils.safeClose(is);
			Utils.safeClose(is2);
			Utils.safeClose(arrayOutputStream);
		}

		if (logger.isDebugEnabled())
			logger.debug(LOCATION + ":END: ");
		return arrayOutputStream;
	}

	/**
	 * @param baseFolder
	 * @param pAcct
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream regTeleReport(String baseFolder, PersonalAccount pAcct) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Map<String, Object> parameters2 = new HashMap<String, Object>();
		Map<String, Object> parameters3 = new HashMap<String, Object>();
		Map<String, Object> parameters4 = new HashMap<String, Object>();
		PersonalAccount[] pAcctList = { pAcct };
		JRBeanCollectionDataSource bean = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		JRBeanCollectionDataSource bean2 = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		JRBeanCollectionDataSource bean3 = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		JRBeanCollectionDataSource bean4 = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		// fill the report
		FileInputStream is = null;
		FileInputStream is2 = null;
		FileInputStream is3 = null;
		FileInputStream is4 = null;
		ByteArrayOutputStream arrayOutputStream = null;
		try {
			is = new FileInputStream(Utils.catString(baseFolder, REGISTER_SERVICE_PAGE1 + JASPER_FILE_EXTENSION));
			is2 = new FileInputStream(Utils.catString(baseFolder, REGISTER_SERVICE_PAGE2 + JASPER_FILE_EXTENSION));
			is3 = new FileInputStream(Utils.catString(baseFolder, REGISTER_SERVICE_PAGE3 + JASPER_FILE_EXTENSION));
			is4 = new FileInputStream(Utils.catString(baseFolder, REGISTER_SERVICE_PAGE4 + JASPER_FILE_EXTENSION));
			List<JasperPrint> printList = new LinkedList<JasperPrint>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, parameters, bean);
			printList.add(jasperPrint);
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(is2, parameters2, bean2);
			printList.add(jasperPrint2);
			JasperPrint jasperPrint3 = JasperFillManager.fillReport(is3, parameters3, bean3);
			printList.add(jasperPrint3);
			JasperPrint jasperPrint4 = JasperFillManager.fillReport(is4, parameters4, bean4);
			printList.add(jasperPrint4);

			JRExporter exporter = new JRPdfExporter();
			arrayOutputStream = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			exporter.exportReport();
		} finally {
			Utils.safeClose(is);
			Utils.safeClose(is2);
			Utils.safeClose(is3);
			Utils.safeClose(is4);
			Utils.safeClose(arrayOutputStream);
		}
		return arrayOutputStream;
	}

	/**
	 * @param baseFolder
	 * @param pAcct
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream grantPowerTrading(String baseFolder, PersonalAccount pAcct) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		PersonalAccount[] pAcctList = { pAcct };
		JRBeanCollectionDataSource bean = new JRBeanCollectionDataSource(Arrays.asList(pAcctList));
		// fill the report
		FileInputStream is = null;
		ByteArrayOutputStream arrayOutputStream = null;
		try {
			is = new FileInputStream(Utils.catString(baseFolder, GRANT_POWER_TRADING_PAGE1 + JASPER_FILE_EXTENSION));
			List<JasperPrint> printList = new LinkedList<JasperPrint>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, parameters, bean);
			printList.add(jasperPrint);
			printList.add((JasperPrint) JRLoader.loadObject(Utils.catString(baseFolder, GRANT_CONTENT_PAGE1
			        + JRPRINT_FILE_EXTENSION)));
			printList.add((JasperPrint) JRLoader.loadObject(Utils.catString(baseFolder, GRANT_CONTENT_PAGE2
			        + JRPRINT_FILE_EXTENSION)));
			printList.add((JasperPrint) JRLoader.loadObject(Utils.catString(baseFolder, GRANT_CONTENT_PAGE3
			        + JRPRINT_FILE_EXTENSION)));

			JRExporter exporter = new JRPdfExporter();
			arrayOutputStream = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			exporter.exportReport();
		} finally {
			Utils.safeClose(is);
			Utils.safeClose(arrayOutputStream);
		}
		return arrayOutputStream;
	}

}

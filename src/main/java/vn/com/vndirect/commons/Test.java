package vn.com.vndirect.commons;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JasperFillManager;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		String baseFolder =
		// "D:\\Projects\\VNDirectNew\\3.Development\\VNDirectReports/compiled-templates/openaccount";
		"D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount";

		@SuppressWarnings("unused")
		FileInputStream is = null;
		@SuppressWarnings("unused")
		ByteArrayOutputStream arrayOutputStream = null;
		@SuppressWarnings("unused")
		Map<String, Object> parameters = new HashMap<String, Object>();
		try {
			// is = new FileInputStream(Utils.catString(baseFolder, "quy-dinh.jrxml"));
			// List<JasperPrint> printList = new LinkedList<JasperPrint>();

			// JasperCompileManager.compileReportToFile(
			// "D:\\Projects\\VNDirect COS\\Development\\VNDirectWebNew-Deployed/WebRoot/WEB-INF/reports/openaccount/quy-dinh-temp.jrxml"
			// );
			System.out.println("Begin compile");

			// JasperCompileManager.compileReportToFile(
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-mo-tk-ca-nhan-tv.jrxml",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-mo-tk-ca-nhan-tv.jasper");
			// JasperCompileManager.compileReportToFile(
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv.jrxml",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv.jasper");
			// JasperCompileManager.compileReportToFile(
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv-2.jrxml",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv-2.jasper");
			// JasperCompileManager.compileReportToFile(
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv-3.jrxml",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv-3.jasper");
			// JasperCompileManager.compileReportToFile(
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv-4.jrxml",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\phieu-dang-ky-dich-vu-tv-4.jasper");
			//						
			// JasperFillManager.fillReportToFile("D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\quy-dinh.jasper",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\quy-dinh.jrprint", new HashMap(), new JREmptyDataSource());
			// JasperFillManager.fillReportToFile("D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\quy-dinh-2.jasper",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\quy-dinh-2.jrprint", new HashMap(), new JREmptyDataSource());
			// JasperFillManager.fillReportToFile("D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\quy-dinh-3.jasper",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\quy-dinh-3.jrprint", new HashMap(), new JREmptyDataSource());

			// JasperFillManager.fillReportToFile("D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\noi-dung-uy-quyen-tv.jasper",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\noi-dung-uy-quyen-tv.jrprint", new HashMap(), new JREmptyDataSource());
			// JasperFillManager.fillReportToFile("D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\noi-dung-uy-quyen-tv-2.jasper",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\noi-dung-uy-quyen-tv-2.jrprint", new HashMap(), new JREmptyDataSource());
			// JasperFillManager.fillReportToFile("D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\noi-dung-uy-quyen-tv-3.jasper",
			// "D:\\Projects\\VNDirect COS\\Documents\\Mo TK 20110802\\openaccount\\noi-dung-uy-quyen-tv-3.jrprint", new HashMap(), new JREmptyDataSource());

			//JasperFillManager.fillReportToFile("D:\\duong.le\\Workspaces\\VNDSProject\\VNDirectWebNew\\WebRoot\\WEB-INF\\reports\\openaccount\\quy-dinh-new.jasper",
			//		"D:\\duong.le\\Workspaces\\VNDSProject\\VNDirectWebNew\\WebRoot\\WEB-INF\\reports\\openaccount\\quy-dinh-new.jrprint", new HashMap(), new JREmptyDataSource());

			//JasperFillManager.fillReportToFile("D:\\duong.le\\Workspaces\\VNDSProject\\VNDirectWebNew\\WebRoot\\WEB-INF\\reports\\openaccount\\quy-dinh-2-new.jasper",
			//		"D:\\duong.le\\Workspaces\\VNDSProject\\VNDirectWebNew\\WebRoot\\WEB-INF\\reports\\openaccount\\quy-dinh-2-new.jrprint", new HashMap(), new JREmptyDataSource());

			System.out.println("End fill report");

			// JasperPrintManager.

			// JasperPrint jasperPrint = JasperFillManager.fillReport(is,parameters);
			//			
			// printList.add(jasperPrint);
			// // printList.add((JasperPrint)JRLoader.loadObject(Utils.catString(baseFolder,"quy-dinh-tai-khoan-rieng.jasper")));
			//
			// JRExporter exporter = new JRPdfExporter();
			// arrayOutputStream = new ByteArrayOutputStream();
			// exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,printList);
			// exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,arrayOutputStream);
			// exporter.exportReport();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

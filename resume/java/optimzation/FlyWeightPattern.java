package resume.java.optimzation;

import java.util.HashMap;
import java.util.Map;

public class FlyWeightPattern {
	
	public static void main(String[] args) {
		FlyWeightPattern f = new FlyWeightPattern();
		ReportManagerFactory rmf = f.new ReportManagerFactory();
		IReportManager rm = rmf.getFinancialReportManager("A");
		System.out.println(rm.createReport());
	}

	public interface IReportManager {
		public String createReport();
	}

	public class FinancialReportManager implements IReportManager {
		protected String tenantId = null;

		public FinancialReportManager(String tenantId) {
			this.tenantId = tenantId;
		}

		@Override
		public String createReport() {
			return "This is a financial report";
		}
	}

	public class EmployeeReportManager implements IReportManager {
		protected String tenantId = null;

		public EmployeeReportManager(String tenantId) {
			this.tenantId = tenantId;
		}

		@Override
		public String createReport() {
			return "This is a employee report";
		}
	}

	public class ReportManagerFactory {

		Map<String, IReportManager> financialReportManager = new HashMap<String, IReportManager>();
		Map<String, IReportManager> employeeReportManager = new HashMap<String, IReportManager>();

		IReportManager getFinancialReportManager(String tenantId) {
			IReportManager r = financialReportManager.get(tenantId);
			if (r == null) {
				r = new FinancialReportManager(tenantId);
				financialReportManager.put(tenantId, r);
			}
			return r;
		}

		IReportManager getEmployeeReportReportManager(String tenantId) {
			IReportManager r = employeeReportManager.get(tenantId);
			if (r == null) {
				r = new EmployeeReportManager(tenantId);
				employeeReportManager.put(tenantId, r);
			}
			return r;
		}
	}
}

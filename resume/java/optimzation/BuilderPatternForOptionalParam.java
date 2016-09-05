package resume.java.optimzation;

/*
 * Pros
 * 1. thread safe
 * 2. no position necessary,
 * 3. optional parameters as chains
 * 4. >4 parameters length would be better for usage.
 */
public class BuilderPatternForOptionalParam {
	private String name;
	private Integer empId;
	private String company;
	private Integer passport;
	private String tempAddress;

	private BuilderPatternForOptionalParam() {
	}

	private static class ParametersBuilder {
		private String name;
		private Integer empId;
		private String company;
		private Integer passport;
		private String tempAddress;

		public ParametersBuilder(String name, Integer empId, String company) {
			this.name = name;
			this.empId = empId;
			this.company = company;
		}

		public ParametersBuilder setPassport(Integer passport) {
			this.passport = passport;
			return this;
		}

		public ParametersBuilder setTempAddress(String address) {
			this.tempAddress = address;
			return this;
		}

		public BuilderPatternForOptionalParam build() {
			BuilderPatternForOptionalParam bp = new BuilderPatternForOptionalParam();
			bp.name = this.name;
			bp.empId = this.empId;
			bp.company = this.company;
			bp.passport = this.passport != null ? this.passport : 0;
			bp.tempAddress = this.tempAddress != null ? this.tempAddress : "NA";
			return bp;
		}
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", empId=" + empId + ", company=" + company + ", passport=" + passport
				+ ", tempAddress=" + tempAddress + "]";
	}

	public static void main(String[] args) {
		BuilderPatternForOptionalParam bpfop1 = new BuilderPatternForOptionalParam.ParametersBuilder("Shamik", 100,
				"IBM").build();
		BuilderPatternForOptionalParam bpfop2 = new BuilderPatternForOptionalParam.ParametersBuilder("Akash", 101,
				"IBM").setPassport(1234).setTempAddress("1,bangalore").build();
		System.out.println(bpfop1);
		System.out.println(bpfop2);

	}

}

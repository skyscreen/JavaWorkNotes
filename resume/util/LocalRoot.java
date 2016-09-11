package resume.java.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class LocalRoot {

	public static void main(String[] args) {
		 int DELAY_SEC = 100;
		 System.out.println(MessageFormat.format("Stopping in {0} seconds.", DELAY_SEC));
		 Object[] queNums = new Object[1];
		 queNums[0] = DELAY_SEC;
		 System.out.println(new MessageFormat("Stopping in {0} seconds.", Locale.ROOT).format(queNums));
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT);
		 
		 String strValue="IsAteacher";
		 strValue.toLowerCase(Locale.ROOT);
		 Locale trlocale= new Locale("tr-TR");
		 System.out.println(strValue.toUpperCase(trlocale));
		 
		 
		 DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.ROOT);

	}

}

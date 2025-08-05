import java.lang.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.util.*;

public class TestDateFormat {
	public static void main(String[] args) throws ParseException {
		/*	字符串转时间	*/
		DateFormat Format = new SimpleDateFormat("yyyy MM dd hh:mm:ss");
		String str = "2025 10 1 10:13:12";
		Date CountryDaitl = Format.parse(str);
		System.out.println(CountryDaitl);
		System.out.println(CountryDaitl.getTime());
		/*	时间转字符串	*/
		DateFormat Format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date2 = new Date(786208586L);
		String data2Str = Format2.format(date2);
		System.out.println(data2Str);
		
		//探查周，天
		Date now = new Date();
		DateFormat f1 = new SimpleDateFormat("今年的第D天，第w周。");
		f1.format(now);
		String str3 = f1.format(now);
		System.out.println(str3);
	}
}

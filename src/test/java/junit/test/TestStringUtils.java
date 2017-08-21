package junit.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TestStringUtils {
	@Test
	public void test() {
		System.out.println(StringUtils.isBlank(""));
		System.out.println(StringUtils.isBlank(null));
	}
}

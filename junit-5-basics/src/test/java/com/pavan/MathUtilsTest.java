package com.pavan;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("when running MathUtils")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MathUtilsTest {
	
	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	 static void beforeInit() {
		System.out.println("@BeforeAll");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
	}
	
		
	@Test
	@DisplayName("testing add method")
	@Tag("Math")
	void testAdd() {
		//MathUtils mathUtils = new MathUtils();
		System.out.println("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual,"the add method should add two numbers");
	}

	
	@Test
	@Tag("Math")
	void testDivide() {
		//MathUtils mathUtils = new MathUtils();
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0), "Divide by zero,should throw ArithmeticException");
	}
	
	/**
	 * WARNING: Configuration error: invalid tag syntax in @Tag("{Math,Circle}") declaration on [void com.pavan.MathUtilsTest.testComputeCircleArea()]. 
	 * Tag will be ignored.
	 * @Tag(value = "{Math,Circle}")
	 */
	@Test
	@Tag("Circle")
	void testComputeCircleArea() {
		//MathUtils mathUtils = new MathUtils();
		double expected = 3.14;
		double actual = mathUtils.computeCircleArea(1);
		assertEquals(expected,actual);
		
	}
	
	@Test
	@DisplayName("in progress implementation... disable test")
	@Disabled
	void testDisabled() {
		fail("this test method is disabled");
	}
	
	
	@Test
	@EnabledOnOs(OS.OTHER)
	void testOnSpecificOs() {
		fail();
	}
	
	@Test
	void testAssumeServerDown() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		assertEquals("UP", "UP");
	} 
	
	@Test
	void testAssumeServerUp() {
		boolean isServerUp = true;
		assumeTrue(isServerUp);
		assertEquals("UP", "UP");
	}
	
	
	@Nested
	@DisplayName("multiply")
	@Tag("Math")
	class multiplyTest{
		
		@Test
		@DisplayName("multiply 1")
		void multiply1() {
			assertEquals(4, mathUtils.multiply(2,2));
		}
		
		@Test
		@DisplayName("multiply 2")
		void multiply2() {
			assertEquals(2, mathUtils.multiply(2,1));
		}
		
	}
	
	
	@Test
	@DisplayName("assertAll feature use")
	@Tag("Math")
	void multiply() {
		assertAll(
				() -> assertEquals(6,mathUtils.multiply(3, 2)),
				() -> assertNotEquals(5, mathUtils.multiply(3, 2))
				);
	}
	
	@Test
	@DisplayName("lazy assert msgs")
	@Tag("Math")
	void TestMessageDisplay() {
		int exptected = 0;
		int actual = mathUtils.add(1, -1);
		assertEquals(exptected,actual, () -> "expected is "+exptected+" returned "+actual);
	}
	
	
	@RepeatedTest(value = 4)
	void testRepetition() {
		
	}
	
	/**
	 *the following giving error when we run, findout how to use RepetitionInfo
	@RepeatedTest(2)
	void testRepetition2(RepetitionInfo repetitionInfo) {
		if (repetitionInfo.getCurrentRepetition() == 1) {
			assertEquals(2,mathUtils.add(1, 1));
		}
			
		if (repetitionInfo.getCurrentRepetition() == 2) {
			assertEquals(2,mathUtils.add(1, 1));
		}
			
	}
	*/
	
	@AfterEach
	void cleanup() {
		System.out.println("clean up ....");
	}
	
	@AfterAll
	static void cleanupAll() {
		System.out.println("@AfterAll");
	}

}

/**
 * org.junit.platform.commons.JUnitException: @BeforeAll method 'void com.pavan.MathUtilsTest.beforeInit()' must be static 
 * unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
*/

/**
 * org.junit.platform.commons.JUnitException: @AfterAll method 'void com.pavan.MathUtilsTest.cleanupAll()' must be static 
 * unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
 */

/**
 * org.opentest4j.TestAbortedException: Assumption failed: assumption is not true
 * for assumption we see stacktrace even if it skip the test to know why it's skipped that test
*/

/**
 * org.junit.platform.commons.PreconditionViolationException: Configuration error: @RepeatedTest on method 
 * [void com.pavan.MathUtilsTest.testRepetition()] must be declared with a positive 'value'.
*/


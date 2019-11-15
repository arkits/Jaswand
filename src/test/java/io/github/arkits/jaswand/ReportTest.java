package io.github.arkits.jaswand;

import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportTest {

	private ElementFactory elementFactory;

	@Before
	public void setUp() {
		elementFactory = new ElementFactory();
	}

	/**
	 * Sanity check to make sure a Report can be created and compiled.
	 */
	@Test
	public void emptyReportSanity() {
		Report report = new Report();
		report.setReportTitle("Test");
		report.validateReport();
	}

	/**
	 * A NullPointer must be thrown if Report's title is not set.
	 */
	@Test(expected = NullPointerException.class)
	public void testReportTitleNotSet() {
		Report report = new Report();

		// Should throw NullPointerException
		report.validateReport();

	}

	/**
	 * A IndexOutOfBoundsException must be thrown if elementsToRow is greater than 12.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void tooManyToRows() {

		List<ContainerTag> elementsToRow = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		}

		// Should throw IndexOutOfBoundsException
		elementFactory.row(elementsToRow);

	}

	/**
	 * Functional test of setEnablePoweredByJaswandInFooter
	 */
	@Test
	public void disablePlugTest() throws IOException {

		Report report = new Report("Plain Jane");
		String reportHtml = report.export(null);
		Assert.assertTrue(reportHtml.contains("Powered by"));

		report = new Report("Not a Shill");
		report.setEnablePoweredByJaswandInFooter(false);
		reportHtml = report.export(null);
		Assert.assertFalse(reportHtml.contains("Powered by"));

	}

}

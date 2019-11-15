package io.github.arkits.jaswand;

import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;
import org.junit.Before;
import org.junit.Test;

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
	public void tooManyToRow() {

		List<ContainerTag> elementsToRow = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		}

		// Should throw IndexOutOfBoundsException
		elementFactory.row(elementsToRow);

	}

}

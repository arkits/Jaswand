package io.github.arkits.jaswand;

import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportTest {

	private Map <String, String> reportData;
	private List<String> reportDataHeaders;

	private ElementFactory elementFactory;

	@Before
	public void setUp(){

		reportData = new HashMap<>();
		reportData.put("A", "1 ms");
		reportData.put("B", "2 ms");
		reportData.put("C", "3 ms");
		reportData.put("D", "4 ms");

		reportDataHeaders = new ArrayList<>();
		reportDataHeaders.add("Class Name");
		reportDataHeaders.add("Time Taken");

		elementFactory = new ElementFactory();

	}

	@Test
	public void testReportsGeneration_basic() {

		Report report = new Report("Benchmark Report");
		report.useRoboto = true;

		List<ContainerTag> elementsToRow = new ArrayList<>();
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportTable("Test 1", reportData, reportDataHeaders),
				elementFactory.space(),
				elementFactory.row(elementsToRow)
		);
		report.add(container);

		report.export("out/rendered.html");

	}

	@Test
	public void emptyReportSanity() {
		Report report = new Report();
		report.setReportTitle("Test");
		report.compileReport();
	}


	@Test(expected = NullPointerException.class)
	public void testReportTitleNotSet() {
		Report report = new Report();

		// Should throw NullPointerException
		report.compileReport();

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void tooManyToRow() {

		List<ContainerTag> elementsToRow = new ArrayList<>();

		for(int i = 0; i < 15; i++){
			elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		}

		// Should throw IndexOutOfBoundsException
		elementFactory.row(elementsToRow);

	}

}

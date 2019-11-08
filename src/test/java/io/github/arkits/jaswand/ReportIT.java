package io.github.arkits.jaswand;

import io.github.arkits.jaswand.elements.ChartDataset;
import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportIT {

	private Map<String, String> reportData;
	private List<String> reportDataHeaders;

	private ElementFactory elementFactory;

	@Before
	public void setUp() {

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
	public void renderReportTable() {

		Report report = new Report("Report Table Sample");

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportTable("Test 1", reportData, reportDataHeaders)
		);
		report.add(container);

		report.export("samples/reportTable.html");

	}

	@Test
	public void renderReportCards() {

		Report report = new Report("Report Cards Sample");

		List<ContainerTag> elementsToRow = new ArrayList<>();
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.row(elementsToRow)
		);
		report.add(container);

		report.export("samples/reportCards.html");

	}

	@Test
	public void testReportsGeneration_basic() {

		Report report = new Report("Report Chart Sample");

		report.enableChartsJs = true;

		List<Integer> yAxis = new ArrayList<>();
		yAxis.add(100);
		yAxis.add(200);
		yAxis.add(300);
		yAxis.add(400);
		yAxis.add(500);
		yAxis.add(600);
		yAxis.add(700);
		yAxis.add(800);

		ChartDataset cd = new ChartDataset();
		cd.data.add(50);
		cd.data.add(200);
		cd.data.add(400);
		cd.data.add(20);
		cd.data.add(600);
		cd.data.add(300);
		cd.data.add(200);
		cd.data.add(100);
		cd.fill = false;
		cd.label = "Data 1";
		cd.borderColor = "#3e95cd";

		List<ChartDataset> xAxis = new ArrayList<>();
		xAxis.add(cd);

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportChart("example-chart", "Chart Title", xAxis, yAxis)
		);
		report.add(container);

		report.export("samples/reportChart.html");

	}

}

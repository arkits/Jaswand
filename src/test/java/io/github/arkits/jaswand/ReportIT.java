package io.github.arkits.jaswand;

import com.google.gson.JsonObject;
import io.github.arkits.jaswand.elements.ChartDataset;
import io.github.arkits.jaswand.elements.CollapsibleElement;
import io.github.arkits.jaswand.elements.CollectionElement;
import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class ReportIT {

	private ElementFactory elementFactory;
	private Date reportCreationDate;

	@Before
	public void setUp() {
		elementFactory = new ElementFactory();
		reportCreationDate = new Date(1573258384);
	}

	@Test
	public void renderReportTable() throws IOException {

		Report report = new Report("Offline Report Table Sample");
		report.reportCreationDate = reportCreationDate;

		Map<String, String> reportData;
		List<String> reportDataHeaders;

		reportData = new HashMap<>();
		reportData.put("A", "1 ms");
		reportData.put("B", "2 ms");
		reportData.put("C", "3 ms");
		reportData.put("D", "4 ms");

		reportDataHeaders = new ArrayList<>();
		reportDataHeaders.add("Class Name");
		reportDataHeaders.add("Time Taken");

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportTable("Test 1", reportData, reportDataHeaders)
		);
		report.add(container);

		report.setExportOffline(true);
		report.setEnableChartJs(true);

		report.export("samples");

	}

	@Test
	public void renderReportCards() throws IOException {

		Report report = new Report("Report Cards Sample");
		report.reportCreationDate = reportCreationDate;

		List<ContainerTag> elementsToRow = new ArrayList<>();
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));
		elementsToRow.add(elementFactory.reportCard("Time Taken to Process", "20 ms"));

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.row(elementsToRow)
		);
		report.add(container);

		report.export("samples");

	}

	@Test
	public void renderReportChart() throws IOException {

		Report report = new Report("Report Chart Sample");
		report.reportCreationDate = reportCreationDate;

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

		report.export("samples");

	}

	@Test
	public void renderReportChartWithOptions() throws IOException {

		Report report = new Report("Report Chart Sample with Options");
		report.reportCreationDate = reportCreationDate;

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

		JsonObject options = new JsonObject();

		JsonObject title = new JsonObject();
		title.addProperty("display", true);
		title.addProperty("text", "Report Chart Sample with Options");

		JsonObject legend = new JsonObject();
		legend.addProperty("display", true);
		legend.addProperty("position", "right");

		options.add("title", title);
		options.add("legend", legend);

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportChart("example-chart", xAxis, yAxis, options, 800, 400)
		);
		report.add(container);

		report.export("samples");

	}

	@Test
	public void renderReportCollection() throws IOException {

		Report report = new Report("Report Table Sample");
		report.reportCreationDate = reportCreationDate;

		String collectionHeader = "Header";

		List<CollectionElement> collectionElements = new ArrayList<>();
		collectionElements.add(new CollectionElement("Passed Element Name", "check", "green"));
		collectionElements.add(new CollectionElement("Failed Element Name", "close", "red"));
		collectionElements.add(new CollectionElement("Null Element Name", null, null));

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportCollection(collectionHeader, collectionElements)
		);
		report.add(container);

		report.export("samples");

	}

	@Test
	public void renderReportCollapsible() throws IOException {

		Report report = new Report("Report Collapsible Sample");
		report.reportCreationDate = reportCreationDate;

		List<CollapsibleElement> collapsibleElements = new ArrayList<>();
		collapsibleElements.add(new CollapsibleElement("Pass Element Name", null, "check"));
		collapsibleElements.add(new CollapsibleElement("Failed Element Name", "Failed Element Body", "close"));

		ContainerTag container = elementFactory.container();
		container.with(
				elementFactory.reportCollapsible("Collapsible Header", collapsibleElements),
				elementFactory.line(),
				elementFactory.reportCollapsible(null, collapsibleElements)
		);
		report.add(container);

		report.export("samples");

	}

	@Test
	public void renderCardContainer() throws IOException {

		Report report = new Report("Card Container Sample");
		report.reportCreationDate = reportCreationDate;

		// Table
		Map<String, String> reportData;
		List<String> reportDataHeaders;

		reportData = new HashMap<>();
		reportData.put("A", "1 ms");
		reportData.put("B", "2 ms");
		reportData.put("C", "3 ms");
		reportData.put("D", "4 ms");

		reportDataHeaders = new ArrayList<>();
		reportDataHeaders.add("Class Name");
		reportDataHeaders.add("Time Taken");

		// Chart
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
			elementFactory.cardContainer(
				elementFactory.reportTable("Test 1", reportData, reportDataHeaders)
			),
			elementFactory.cardContainer(
				elementFactory.reportChart("example-chart", "Chart Title", xAxis, yAxis)
			)
		);
		report.add(container);

		report.export("samples");

	}

}

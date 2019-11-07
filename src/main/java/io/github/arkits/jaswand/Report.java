package io.github.arkits.jaswand;

import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.github.arkits.jaswand.Constants.*;
import static j2html.TagCreator.*;

public class Report {

	ContainerTag jaswandReport;

	List<ContainerTag> reportElements;

	String reportTitle;

	Date reportCreationDate;

	boolean useRoboto;

	boolean enableChartsJs;

	public Report() {

		this.reportCreationDate = new Date();

		this.reportElements = new ArrayList<>();

	}

	public Report(String title) {

		this.reportTitle = title;

		this.reportCreationDate = new Date();

		this.reportElements = new ArrayList<>();

	}

	public void setReportTitle(String title) {
		this.reportTitle = title;
	}

	public String getReportTitle() {
		return (this.reportTitle);
	}

	public void add(ContainerTag reportElement) {
		this.reportElements.add(reportElement);
	}

	public String export(String locationToRenderTo) {

		compileReport();

		addFooter();

		String htmlString = jaswandReport.renderFormatted();

		try (FileWriter fileWriter = new FileWriter(locationToRenderTo)) {
			fileWriter.write(htmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (htmlString);
	}

	private void addFooter() {

		ElementFactory elementFactory = new ElementFactory();

		ContainerTag footer = elementFactory.reportFooter(reportCreationDate);
		jaswandReport.with(footer);

	}

	public void compileReport() {

		validate();

		jaswandReport = html(
			head(
				title(this.reportTitle),
				link().withRel("stylesheet").withHref(Style.MATERIALIZE_CSS_URL),
				iff(useRoboto,
						join(link().withRel("stylesheet").withHref(Style.ROBOTO_CSS_URL),
						rawHtml("<style>body {font-family: 'Roboto', sans-serif;}</style>"))),
				rawHtml(Style.STICKY_FOOTER_CSS),
				rawHtml(Style.GREY_BACKGROUND_CSS),
				iff(enableChartsJs,
					script().withSrc(Style.CHARTSJS_URL))
			),
			body(
				main(
					div(attrs(".container"), h3(this.reportTitle)),
					each(reportElements, containerTag -> containerTag)
				)
			)
		);

	}

	private void validate() {
		if (reportTitle == null) {
			throw new NullPointerException("reportTitle can not be null");
		}
	}

}

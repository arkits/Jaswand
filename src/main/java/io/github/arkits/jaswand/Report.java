package io.github.arkits.jaswand;

import io.github.arkits.jaswand.elements.ElementFactory;
import j2html.tags.ContainerTag;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.github.arkits.jaswand.Constants.*;
import static j2html.TagCreator.*;

public class Report {

	/**
	 * Contains the entire HTML Report as a ContainerTag
	 */
	ContainerTag jaswandReport;

	/**
	 * List of Report Elements as ContainerTags
	 */
	List<ContainerTag> reportElements;

	/**
	 * Title of the Report
	 */
	String reportTitle;

	/**
	 * Date when report was created
	 */
	Date reportCreationDate;

	/**
	 * Use Roboto Font when rendering the Report HTML
	 */
	boolean useRoboto;

	/**
	 * Enable ChartJS Functionality
	 */
	boolean enableChartJs;

	/**
	 * Enable Material Icons
	 */
	boolean enableMaterialIcons;

	/**
	 * Enable Materialize CSS JavaScript
	 */
	boolean enableMaterializeCssJs;

	/**
	 * Enable 'Powered by Jaswand' in Footer
	 */
	boolean enablePoweredByJaswandInFooter;

	/**
	 * Export Offline: If enabled, external elements such as stylesheet and css
	 * files will also be exported and linked locally.
	 */
	boolean exportOffline;

	/**
	 * Constructs an empty Report
	 */
	public Report() {
		this.reportCreationDate = new Date();
		this.reportElements = new ArrayList<>();
		this.enableMaterialIcons = true;
		this.enableMaterializeCssJs = true;
		this.exportOffline = false;
		this.enablePoweredByJaswandInFooter = true;
	}

	/**
	 * Constructs an empty Report with supplied Title.
	 * @param title title of the report
	 */
	public Report(String title) {
		this.reportTitle = title;
		this.reportCreationDate = new Date();
		this.reportElements = new ArrayList<>();
		this.enableMaterialIcons = true;
		this.enableMaterializeCssJs = true;
		this.exportOffline = false;
		this.enablePoweredByJaswandInFooter = true;
	}

	/**
	 * Adds a reportElement into the reportElements List
	 * @param reportElement reportElement
	 */
	public void add(ContainerTag reportElement) {
		this.reportElements.add(reportElement);
	}

	/**
	 * Exports the Report into a HTML file at the specified location
	 * @param locationToRenderTo location to render as a relative path
	 * @return export HTML as a String
	 */
	public String export(String locationToRenderTo) throws IOException {

		String workspace = locationToRenderTo + "/" + reportTitle.replace(" ", "");

		makeDir(workspace);

		if(exportOffline){
			exportResources(workspace);
		}

		compileReport();

		addFooter();

		String htmlString = jaswandReport.renderFormatted();

		try (FileWriter fileWriter = new FileWriter(workspace + "/index.html")) {
			fileWriter.write(htmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (htmlString);
	}

	/**
	 * Compiles the Report by assembling the Header, Report Elements and Footer
	 */
	public void compileReport() {

		validate();

		jaswandReport = html(
				head(
						title(this.reportTitle),
						iffElse(exportOffline,
								link().withRel("stylesheet").withHref(Style.MATERIALIZE_CSS_URL),
								link().withRel("stylesheet").withHref(Style.OFFLINE_MATERIALIZE_CSS_URL)),
						iff(useRoboto,
								join(link().withRel("stylesheet").withHref(Style.ROBOTO_CSS_URL),
										rawHtml("<style>body {font-family: 'Roboto', sans-serif;}</style>"))),
						iff(enableMaterialIcons,
								link().withRel("stylesheet").withHref(Style.MATERIAL_ICONS_URL)),
						rawHtml(Style.STICKY_FOOTER_CSS),
						rawHtml(Style.GREY_BACKGROUND_CSS),
						iff(enableChartJs,
								iffElse(exportOffline,
										script().withSrc(ChartJS.CHARTJS_URL),
										script().withSrc(ChartJS.OFFLINE_CHARTJS_URL))
						),
						iff(enableMaterializeCssJs,
								iffElse(exportOffline,
										script().withSrc(Style.MATERIALIZE_JS_URL),
										script().withSrc(Style.OFFLINE_MATERIALIZE_JS_URL))
						),
						script().withType("text/javascript").with(
								rawHtml(Style.MATERIALIZE_JS_COLLAPSIBLE)
						)
				),
				body(
						main(
								div(attrs(".container"), h3(this.reportTitle)),
								each(reportElements, containerTag -> containerTag)
						)
				)
		);

	}

	/**
	 * Adds the HTML Footer
	 */
	private void addFooter() {

		ElementFactory elementFactory = new ElementFactory();

		ContainerTag footer = elementFactory.reportFooter(reportCreationDate, this.enablePoweredByJaswandInFooter);
		jaswandReport.with(footer);

	}

	/**
	 * Validates certain aspects of the Report
	 */
	private void validate() {
		if (reportTitle == null) {
			throw new NullPointerException("reportTitle can not be null");
		}
	}

	/**
	 * Exports internet resources used by the HTML report to the local disk.
	 * Export resources include JavaScript and CSS files of
	 * Materialize CSS and Chart.js
	 * @param locationToExportTo location to export to on the local disk
	 * @throws IOException
	 */
	private void exportResources(String locationToExportTo) throws IOException {

		makeDir(locationToExportTo + "/css");
		makeDir(locationToExportTo + "/js");

		downloadFromUrl(Style.MATERIALIZE_CSS_URL, locationToExportTo + "/css" + "/materialize.css");
		downloadFromUrl(Style.MATERIALIZE_JS_URL, locationToExportTo + "/js" + "/materialize.js");

		if(enableChartJs) {
			downloadFromUrl(ChartJS.CHARTJS_URL, locationToExportTo + "/js" + "/chart.js");
		}

	}

	/**
	 * Utility function to create a directory
	 * @param dir directory to create
	 */
	private void makeDir(String dir){
		File fDir = new File(dir);
		if (!fDir.exists()) {
			fDir.mkdir();
		}
	}

	/**
	 * Downloads the resource to the local disk
	 * @param FILE_URL URL of the file to download
	 * @param FILE_NAME name of the file to export as
	 * @throws IOException
	 */
	private void downloadFromUrl(String FILE_URL, String FILE_NAME) throws IOException {
		try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
		     FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* ------ Getters and Setters ------ */

	public void setReportTitle(String title) {
		this.reportTitle = title;
	}

	public String getReportTitle() {
		return (this.reportTitle);
	}

	public Date getReportCreationDate() {
		return reportCreationDate;
	}

	public void setReportCreationDate(Date reportCreationDate) {
		this.reportCreationDate = reportCreationDate;
	}

	public boolean isUseRoboto() {
		return useRoboto;
	}

	public void setUseRoboto(boolean useRoboto) {
		this.useRoboto = useRoboto;
	}

	public boolean isEnableChartJs() {
		return enableChartJs;
	}

	public void setEnableChartJs(boolean enableChartJs) {
		this.enableChartJs = enableChartJs;
	}

	public boolean isEnableMaterialIcons() {
		return enableMaterialIcons;
	}

	public void setEnableMaterialIcons(boolean enableMaterialIcons) {
		this.enableMaterialIcons = enableMaterialIcons;
	}

	public boolean isEnableMaterializeCssJs() {
		return enableMaterializeCssJs;
	}

	public void setEnableMaterializeCssJs(boolean enableMaterializeCssJs) {
		this.enableMaterializeCssJs = enableMaterializeCssJs;
	}

	public boolean isExportOffline() {
		return exportOffline;
	}

	public void setExportOffline(boolean exportOffline) {
		this.exportOffline = exportOffline;
	}

	public boolean isEnablePoweredByJaswandInFooter() {
		return enablePoweredByJaswandInFooter;
	}

	public void setEnablePoweredByJaswandInFooter(boolean enablePoweredByJaswandInFooter) {
		this.enablePoweredByJaswandInFooter = enablePoweredByJaswandInFooter;
	}

}

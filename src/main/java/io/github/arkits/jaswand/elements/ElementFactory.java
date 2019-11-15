package io.github.arkits.jaswand.elements;

import j2html.tags.ContainerTag;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.arkits.jaswand.Constants;

import static io.github.arkits.jaswand.Constants.*;
import static j2html.TagCreator.*;

public class ElementFactory {

	/**
	 * ElementFactory
	 */
	public ElementFactory() {
	}

	public ContainerTag container() {
		return div(attrs(".container"));
	}

	public ContainerTag row(List<ContainerTag> elements) {

		if (elements.size() > 12) {
			throw new IndexOutOfBoundsException("List of size greater than 12 not allowed");
		}

		int colDivision = 12 / elements.size();

		String colClass = ".col l" + colDivision;

		return div(attrs(".row"), each(elements, element -> div(attrs(colClass), element)));
	}

	public ContainerTag line() {

		ContainerTag line = new ContainerTag("line");
		line.with(br(), hr(), br());

		return line;
	}

	public ContainerTag space() {
		return div(br(), br());
	}

	public ContainerTag reportTable(String blockName, Map<String, String> reportData, List<String> tableHeaders) {

		return div(attrs(".jaswand-table"), h4(blockName),
				table(attrs(".highlight"), thead(tr(each(tableHeaders, headers -> th(headers)))),
						tbody(each(reportData, data -> tr(td(data.getKey()), td(data.getValue()))))));
	}

	public ContainerTag reportCard(String cardTitle, String cardContents) {
		return div(attrs(".card"), div(attrs(".card-content"), p(cardTitle), span(attrs(".card-title"), cardContents)));
	}

	public ContainerTag reportFooter(Date reportCreationDate, boolean showPlug) {

		ContainerTag renderedDateText = p("Rendered on: ").with(code(reportCreationDate.toString()));

		ContainerTag jaswandPlug = p("Powered by ")
				.with(a(attrs(".white-text"), Jaswand.JASWAND_NAME).withHref(Jaswand.JASWAND_GITHUB_URL));

		return footer(attrs(".page-footer red"),
				div(attrs(".container"),
						div(attrs(".row"),
							div(attrs(".col l6"), renderedDateText),
							iff(showPlug, div(attrs(".col l6 right-align"), jaswandPlug))
						)
				)
		);
	}

	public ContainerTag reportChart(
			String chartId,
			String chartTitle,
			List<ChartDataset> xAxis,
			List<Integer> yAxis
	) {
		// Chart Type
		String chartType = "line";
		JsonObject chartParam = Constants.ChartJS.generateChartJsData();
		chartParam.addProperty("type", chartType);

		// Chart Title
		JsonObject title = new JsonObject();
		title.addProperty("display", true);
		title.addProperty("text", chartTitle);

		JsonObject options = new JsonObject();
		options.add("title", title);

		chartParam.add("options", options);

		// Dataset
		JsonObject data = new JsonObject();

		GsonBuilder gb = new GsonBuilder();
		JsonElement labels = gb.create().toJsonTree(yAxis);
		data.add("labels", labels);

		JsonElement datasets = gb.create().toJsonTree(xAxis);
		data.add("datasets", datasets);

		chartParam.add("data", data);

		StringBuilder chartScript = new StringBuilder();
		chartScript.append(String.format("<script>new Chart(document.getElementById('%s'),", chartId));
		chartScript.append(chartParam.toString());
		chartScript.append(");</script>");

		return div(attrs(".jaswand-chart"),
				canvas().withId(chartId).attr("width", "800").attr("height", "450"),
				rawHtml(chartScript.toString()),
				br()
		);
	}

	public ContainerTag reportCollection(String collectionHeader, List<CollectionElement> collectionElements) {
		return div(attrs(".jaswand-collection"),
				ul(attrs(".collection with-header"),
						iff(collectionHeader != null, li(attrs(".collection-header"), h4(collectionHeader))),
						each(collectionElements, collectionElement ->
								li(
										iffElse(collectionElement.getElementIcon() != null
														&& collectionElement.getElementIconColor() != null,
												attrs(".collection-item avatar valign-wrapper"), attrs(".collection-item")),

										iff(collectionElement.getElementIcon() != null
														&& collectionElement.getElementIconColor() != null,
												i(attrs(".material-icons circle " + collectionElement.getElementIconColor()), collectionElement.getElementIcon())),

										span(attrs(".title"), collectionElement.getElementName())

								)
						)
				)
		);
	}

	public ContainerTag reportCollapsible(String collapsibleHeader, List<CollapsibleElement> collapsibleElements) {
		return div(attrs(".jaswand-collapsible"),
			iff(collapsibleHeader != null, h4(collapsibleHeader)) ,
			ul(attrs(".collapsible"),
				each(collapsibleElements, collapsibleElement ->
					li(
						div(attrs(".collapsible-header"),
								i(attrs(".material-icons"), collapsibleElement.getElementIcon()),
								rawHtml(collapsibleElement.getElementName())
						),
						iff(collapsibleElement.getElementBody() != null,
							div(attrs(".collapsible-body"),
								collapsibleElement.getElementBody()
							)
						)
					)
				)
			)
		);
	}
}

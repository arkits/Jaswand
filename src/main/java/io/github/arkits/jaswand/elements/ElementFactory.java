package io.github.arkits.jaswand.elements;

import j2html.tags.ContainerTag;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.github.arkits.jaswand.web.Constants.*;
import static j2html.TagCreator.*;
import static java.lang.StrictMath.floor;

public class ElementFactory {

	/**
	 * ElementFactory
	 */
	public ElementFactory(){
	}

	public ContainerTag container(){
		return div(attrs(".container"));
	}

	public ContainerTag row(List<ContainerTag> elements){

		if(elements.size() > 12){
			throw new IndexOutOfBoundsException("List of size greater than 12 not allowed");
		}

		int colDivision = 12/elements.size();

		String colClass = ".col l" + colDivision;

		return div(attrs(".row"),
				each(elements, element ->
						div(attrs(colClass), element)
				)
		);
	}

	public ContainerTag line(){

		ContainerTag line = new ContainerTag("line");
		line.with(
				br(),
				hr(),
				br()
		);

		return line;
	}

	public ContainerTag space(){
		return div(
				br(), br()
		);
	}

	public ContainerTag reportTable(String blockName,
	                                Map<String, String> reportData,
	                                List<String> tableHeaders){

		return div(attrs(".jaswand-table"),
			h4(blockName),
			table(attrs(".highlight"),
				thead(tr(
					each(tableHeaders, headers ->
						th(headers)
					)
				)),
				tbody(each(reportData, data -> tr(
					td(data.getKey()),
					td(data.getValue())
				)))
			)
		);
	}

	public ContainerTag reportCard(String cardTitle, String cardContents){
		return div(attrs(".card"),
				div(attrs(".card-content"),
						p(cardTitle),
						span(attrs(".card-title"), cardContents)
				));
	}

	public ContainerTag reportFooter(Date reportCreationDate) {

		ContainerTag renderedDateText = p("Rendered on: ").with(code(reportCreationDate.toString()));

		ContainerTag jaswandPlug = p("Powered by ").with(
				a(attrs(".white-text"), Jaswand.JASWAND_NAME).withHref(Jaswand.JASWAND_GITHUB_URL)
		);

		return footer(attrs(".page-footer red"),
				div(attrs(".container"), div(attrs(".row"),
						div(attrs(".col l6"),
							renderedDateText),
						div(attrs(".col l6 right-align"),
							jaswandPlug)
				)));
	}
}

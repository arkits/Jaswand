package io.github.arkits.jaswand;

import com.google.gson.JsonObject;

public class Constants {

	public static class Style {

		public static final String MATERIALIZE_CSS_URL = "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css";
		public static final String MATERIALIZE_JS_URL = "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js";
		public static final String MATERIAL_ICONS_URL = "https://fonts.googleapis.com/icon?family=Material+Icons";
		public static final String ROBOTO_CSS_URL = "https://fonts.googleapis.com/css?family=Roboto&display=swap";

		public static final String STICKY_FOOTER_CSS = "<style>body{display:flex;min-height:100vh;flex-direction:column}main{flex:1 0 auto}</style>";
		public static final String GREY_BACKGROUND_CSS = "<style>body{background-color: #fafafa ;}</style>";

		public static final String MATERIALIZE_JS_COLLAPSIBLE = "document.addEventListener(\"DOMContentLoaded\",function(){var e=document.querySelectorAll(\".collapsible\");M.Collapsible.init(e,{})});";

		public static final String RED_COLOR = "red";
		public static final String GREY_COLOR = "grey";
		public static final String COLOR_DARKEN = "darken-";
		public static final String COLOR_LIGHTEN = "lighten-";

	}

	public static class Jaswand {

		public static final String JASWAND_NAME = "Jaswand";

		public static final String JASWAND_GITHUB_URL = "https://github.com/arkits/jaswand";

	}

	public static class ChartJS {

		public static final String CHARTJS_URL = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.js";

		public static JsonObject generateChartJsData(){

			JsonObject CHART_DATA_JSON = new JsonObject();
			CHART_DATA_JSON.add("type", null);
			CHART_DATA_JSON.add("data", null);
			CHART_DATA_JSON.add("options", null);

			return CHART_DATA_JSON;

		}

		public static JsonObject generateChartJsDataset(){

			JsonObject CHART_DATA_JSON = new JsonObject();
			CHART_DATA_JSON.add("data", null);
			CHART_DATA_JSON.add("label", null);
			CHART_DATA_JSON.add("borderColor", null);
			CHART_DATA_JSON.add("fill", null);

			return CHART_DATA_JSON;

		}


	}

}

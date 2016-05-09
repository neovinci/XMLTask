import java.util.List;

import by.epamlab.beans.Airline;
import by.epamlab.xml.XmlUtils;

public class Running {

	public static void main(String[] args) {
		final String SOURCE_FILE_NAME = "src/by/epamlab/source/airline.xml";
		final String EDIT_FILE_NAME = "src/by/epamlab/source/airline2.xml";
		
//		create beans from XML file
		List<Airline> airlines = XmlUtils.readCatalogFromFile(SOURCE_FILE_NAME);
		
		for(Airline out : airlines) {
			System.out.println(out);
			out.setType("shanged");
		}
		
//		write beans into XML file
		XmlUtils.exportCatalogToXML(EDIT_FILE_NAME, airlines);
	}
}

package ai.clarity.challenge;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import ai.clarity.challenge.pojo.GenericDataLog;

public class LogDataParserTest {

	private static final String JAVA_LANG_STRING_HOST_TO = "java.lang.String:hostTo";
private static final String JAVA_UTIL_DATE_DATE = "java.util.Date:date";
private static final String JAVA_LANG_STRING_HOST_FROM = "java.lang.String:hostFrom";
private static Logger LOGGER = LogManager.getLogger(LogDataParserTest.class);

	private static final String LOG_FILE = "src/test/resources/input-file-test-10000.txt";

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

	@Test
	void readLogDataFromFileTest() throws IOException {

		GenericDataLog dataLog = null;
		List<GenericDataLog> dataLogs = new ArrayList<GenericDataLog>();
		Properties dataLogProperties = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE, StandardCharsets.UTF_8))) {
			String s;

			while ((s = bufferedReader.readLine()) != null) { // null = EOF
//				LOGGER.info("======================\n");
				String[] lineArr = s.split(" ");
				if (lineArr[0] == null) {
					continue;
				}
				Long id = Long.parseLong(lineArr[0]);
//				LOGGER.info("id: "+id);
				
				//Date date = new Date(id * (1000L));
//				LOGGER.info("date: "+date);
				if (lineArr[1] == null) {
					continue;
				}
				String hostFrom = lineArr[1];
//				LOGGER.info("hostFrom: "+hostFrom);
				if (lineArr[2] == null) {
					continue;
				}
				String hostTo = lineArr[2];
//				LOGGER.info("hostTo: "+hostTo);
//				LOGGER.info("======================\n");
				dataLog = new GenericDataLog();
				dataLogProperties = new Properties();
				dataLog.setId(id);
				dataLogProperties.setProperty(JAVA_LANG_STRING_HOST_FROM, hostFrom);
				dataLogProperties.setProperty(JAVA_LANG_STRING_HOST_TO, hostTo);
				dataLog.setProperties(dataLogProperties);
				
				dataLogs.add(dataLog);
				
			}
			

		}
		

		Map<String,Date> hostavaibilityAnalisys = new HashMap<>();
		
		dataLogs.stream().forEach( datalog -> {
//			System.out.println("---\n");
//			System.out.println(datalog.getId());
			datalog.getProperties().entrySet().stream().forEach( props -> {
				String key = props.getValue().toString();
				if(hostavaibilityAnalisys.containsKey(key)) {
					//TODO Check last time of avaibility. Should be at maxumum 5min
					Date lastCheckedTime = hostavaibilityAnalisys.get(key);
				}
				
				hostavaibilityAnalisys.put(props.getValue().toString(), new Date(datalog.getId() * (1000L)));
				System.out.println("---\n");
			});
//			System.out.println("---\n");
		});
		
		dataLogs.stream().forEach(datalog -> {
			
			//System.out.println("datalog.getId(): "+datalog.getId()+" ==> "+datalog.getMappedLogStructure().entrySet().toString());
//		    for (Map.Entry<String, Object> entry : datalog.getMappedLogStructure().entrySet()) {
//		    	LOGGER.info("======================\n");
//		        System.out.println(entry.getKey() + ":" + entry.getValue());
//		        LOGGER.info("======================\n");
//		    }
		    
//			LOGGER.info("======================\n");
//			datalog.getMappedLogStructure().entrySet().stream().forEach(properties -> {
//
//				LOGGER.info("key1: "+properties.getKey());
//				LOGGER.info("value1: "+properties.getValue());
//				
//			});
//			LOGGER.info("======================\n");
		});

		//hostavaibilityAnalisys.put(JAVA_LANG_STRING_HOST_FROM, datalog.get);
		// map.entrySet().stream()
		// .forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));

		// .stream()
//                .map(Map::values)
//                .flatMap(Collection::stream)
//                .flatMap(List::stream)
//.collect(Collectors.groupingBy(FlexiServer::getServerNumber, Collectors.mapping(FlexiServer::getQuantity, Collectors.toList())));

		assertEquals(true, true);
	}

}

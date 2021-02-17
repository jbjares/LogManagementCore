package ai.clarity.challenge.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class LogManagementUtil {
	
	private static boolean isAtleastTwentyMinutesAgo(Date date) {
	    Instant instant = Instant.ofEpochMilli(date.getTime());
	    Instant twentyMinutesAgo = Instant.now().minus(Duration.ofMinutes(5));

	    return instant.isBefore(twentyMinutesAgo);
	}

}

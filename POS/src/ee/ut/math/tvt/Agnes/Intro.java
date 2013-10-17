package ee.ut.math.tvt.Agnes;

import java.util.Date;

import org.apache.log4j.Logger;

public class Intro {
	
	private static final Logger logger = Logger.getLogger(Intro.class);
	
	public static void main(String[] args) {
		new IntroUI();
		
		logger.info("Intro window opened from " + logger.getName() + " at " + new Date());
	}

}

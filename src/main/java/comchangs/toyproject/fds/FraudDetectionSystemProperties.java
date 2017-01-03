package comchangs.toyproject.fds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by comchangs on 02/01/2017.
 */
public class FraudDetectionSystemProperties
{
  private static final Logger logger = LogManager.getLogger(FraudDetectionSystem.class);

  private static int numThreads = 10;

  public static int getNumThreads() {
    return numThreads;
  }

  public static void ReadProperty(String[] PropFile) {

    if (PropFile.length > 0) {
      Properties prop = new Properties();
      String filePath = PropFile[0];

      try {
        logger.info("Start reading properties from " + filePath);
        prop.load(new FileInputStream(filePath));

        numThreads = Integer.parseInt(prop.getProperty("fds.numThreads"));

      } catch (IOException e) {
        logger.error("Cannot read properties");
        System.exit(1);
        e.printStackTrace();
      }

      logger.info("=== Fraud Detection System Properties ===");
      for (String name : prop.stringPropertyNames()) {
        if (name.startsWith("fds")) {
          String value = prop.getProperty(name);
          logger.info("set property [" + name + "] : " + value);
        }
      }
      logger.info("==============================");
    }

  }
}

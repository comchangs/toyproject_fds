package comchangs.toyproject.fds;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by comchangs on 02/01/2017.
 */
public class FraudDetectionSystemProperties
{
  private static String globalLoggerName = null;
  private static int numThreads = 10;

  public static String getGlobalLoggerName() {
    return globalLoggerName;
  }

  public static int getNumThreads() {
    return numThreads;
  }

  static void ReadProperty(String PropFile) {
    Properties prop = new Properties();
    FileInputStream ifStream = null;

    try {
      ifStream = new FileInputStream(PropFile);
      prop.load(ifStream);

      globalLoggerName = prop.getProperty("fds.globalLoggerName");
      numThreads = Integer.parseInt(prop.getProperty("fds.numThreads"));
    } catch (IOException ex) {
      System.out.println("Property file: " + PropFile + " reading error") ;
    } finally {
      try{
        if(ifStream != null) ifStream.close();
      } catch (IOException ex) {
        System.out.println("Can't close the input file") ;
      }
    }

    System.out.println("=== Fraud Detection System Properties ===");
    System.out.println("globalLoggerName: " + globalLoggerName);
    System.out.println("numThreads: " + numThreads);
    System.out.println("==============================");
  }
}

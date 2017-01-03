package comchangs.toyproject.fds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comchangs on 02/01/2017.
 *
 * Assume
 *  - Account Number is in Long.max_value
 *  - Client Number is in Integer.max_value
 *  - Maximum balance is in Long.max_value
 *  - Remittance Bank Name is string
 *  - Remittance Account Holder Name is string
 *
 */
public class FraudDetectionSystem
{
  private static final Logger logger = LogManager.getLogger(FraudDetectionSystem.class);
  private static ExecutorService executorService = null;
  private static LinkedBlockingQueue<Object> queue = null;
  private static ConcurrentHashMap<Long, AccountTrackingInformation> accountTrackingMap = null;

  public static void main(String[] args) {

    logger.info("Starting...");

    FraudDetectionSystemProperties.ReadProperty(args);

    executorService = Executors.newFixedThreadPool(FraudDetectionSystemProperties.getNumThreads());
    queue = new LinkedBlockingQueue<Object>();
    accountTrackingMap = new ConcurrentHashMap<Long, AccountTrackingInformation>();

    // Demo KafkaConsumer
    executorService.execute(new KafkaConsumer(queue));

    for (int i = 0; i < FraudDetectionSystemProperties.getNumThreads(); i++) {
      executorService.execute(new Detector(queue, accountTrackingMap));
      logger.info("Fraud Detection System Thread[" + i + "] is started");
    }

    Thread shutdownHook = new Thread( "shutdown-hook" )
    {
      public void run()
      {
        logger.info( "Starting thread pool shutdown..." );
        executorService.shutdown();
        logger.info( "Thread pool shutdown complete." );
      }
    };

    Runtime.getRuntime().addShutdownHook( shutdownHook );

  }
}

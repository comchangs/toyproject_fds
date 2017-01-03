package comchangs.toyproject.fds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by comchangs on 02/01/2017.
 */
public class FraudDetectionSystem
{
  private static final Logger logger = LogManager.getLogger(FraudDetectionSystem.class);
  private static ExecutorService executorService = null;
  private static LinkedBlockingQueue<Object> queue = null;

  public static void main(String[] args) {

    logger.info("Starting...");

    FraudDetectionSystemProperties.ReadProperty(args);

    executorService = Executors.newFixedThreadPool(FraudDetectionSystemProperties.getNumThreads());
    queue = new LinkedBlockingQueue<Object>();

    // Demo KafkaConsumer
    executorService.execute(new KafkaConsumer(queue));

    for (int i = 0; i < FraudDetectionSystemProperties.getNumThreads(); i++) {
      executorService.execute(new Detector(queue));
      logger.info("Fraud Detection System Thread[" + i + "] is started");
    }

    Thread shutdownHook = new Thread( "shutdown-hook" )
    {
      public void run()
      {
        logger.info( "Starting thread pool shutdown..." );
        executorService.shutdownNow();
        logger.info( "Thread pool shutdown complete." );
      }
    };

    Runtime.getRuntime().addShutdownHook( shutdownHook );

  }
}

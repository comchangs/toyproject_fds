package comchangs.toyproject.fds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comchangs on 02/01/2017.
 */
public class KafkaConsumer implements Runnable
{
  private static final Logger logger = LogManager.getLogger(Detector.class);
  private LinkedBlockingQueue queue;

  public KafkaConsumer(LinkedBlockingQueue queue)
  {
    this.queue = queue;
  }

  private void consume() {
    // Generate sample event
    // TODO: Generate sample event like in event
    try {
      for (int i = 0; i < 1000; i++)
        queue.put("" + i);
    }
    catch (InterruptedException e) {
    }
  }

  @Override
  public void run()
  {
    logger.info("KafkaConsumer thread is running");

    while(true) {
      consume();

      try {
        Thread.sleep(10000);
      }
      catch (InterruptedException e) {
        logger.info( "InterruptedException: " + e.getStackTrace() );
      }
    }
  }
}

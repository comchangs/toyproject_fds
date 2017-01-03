package comchangs.toyproject.fds;

import comchangs.toyproject.fds.event.DepositEvent;
import comchangs.toyproject.fds.event.NewAccountEvent;
import comchangs.toyproject.fds.event.WithdrawEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comchangs on 02/01/2017.
 *
 * This class is KafkaConsumer for a Demonstration
 * It makes sample events and than insert into a queue to detect
 */

public class KafkaConsumer implements Runnable
{
  private static final Logger logger = LogManager.getLogger(Detector.class);
  private static long lastGenerationTimestamp = System.currentTimeMillis();
  private LinkedBlockingQueue queue = null;

  public KafkaConsumer(LinkedBlockingQueue queue)
  {
    this.queue = queue;
  }

  private void consume() {
    // Generate sample event
    /**
     * NewAccountEvent
     *  - accountNumber: 110-345678-123
     *  - clientNumber: 12345
     *  - issuedTimestamp: 1483452666 (01/01/2017 @ 1:23am in UTC)
     *
     * DepositEvent
     *  - accountNumber: 110-345678-123
     *  - clientNumber: 12345
     *  - issuedTimestamp: 1483435041 (01/03/2017 @ 9:17am in UTC)
     *  - depositAmount: 990,000 KRW
     *
     * WithdrawEvent
     *  - accountNumber: 110-345678-123
     *  - clientNumber: 12345
     *  - issuedTimestamp: 1483438921 (01/03/2017 @ 10:22am in UTC)
     *  - withdrawalAmount: 980,000 KRW
     *
     */


    try {

      long difference = System.currentTimeMillis() - lastGenerationTimestamp;

      queue.put(new NewAccountEvent(1483452666l + difference, 110345678123l, 12345));
      queue.put(new DepositEvent(1483435041l + difference, 110345678123l, 12345, 990000l));
      queue.put(new WithdrawEvent(1483438921l + difference, 110345678123l, 12345, 980000l));

      lastGenerationTimestamp = System.currentTimeMillis();
    }
    catch (InterruptedException e) {
      logger.info( "InterruptedException: " + e.getStackTrace() );
      e.printStackTrace();
    }
  }

  public void run()
  {
    logger.info("KafkaConsumer thread is running");

    while(true) {
      consume();
      logger.info("Receiving...");

      try {
        Thread.sleep(10000);
      }
      catch (InterruptedException e) {
        logger.info( "InterruptedException: " + e.getStackTrace() );
        e.printStackTrace();
      }
    }
  }
}

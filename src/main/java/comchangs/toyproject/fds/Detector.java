package comchangs.toyproject.fds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comchangs on 1/3/17.
 */
public class Detector implements Runnable
{
  private static final Logger logger = LogManager.getLogger(Detector.class);
  private LinkedBlockingQueue queue;

  public Detector(LinkedBlockingQueue queue)
  {
    this.queue = queue;
  }

  public void detect() {

    try {
      logger.info(queue.take());
    } catch (InterruptedException e) {
    } catch (Exception e) {
      logger.error("Exception: " + e);
    }
  }

  @Override
  public void run()
  {
    logger.info("Detector thread is running");

    while(true) {
      detect();
    }
  }
}

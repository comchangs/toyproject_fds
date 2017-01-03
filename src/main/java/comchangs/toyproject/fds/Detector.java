package comchangs.toyproject.fds;

import comchangs.toyproject.fds.event.DepositEvent;
import comchangs.toyproject.fds.event.NewAccountEvent;
import comchangs.toyproject.fds.event.RemittanceEvent;
import comchangs.toyproject.fds.event.WithdrawEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comchangs on 1/3/17.
 */
public class Detector implements Runnable
{
  private static final Logger logger = LogManager.getLogger(Detector.class);
  private LinkedBlockingQueue queue = null;
  private ConcurrentHashMap accountTrackingMap = null;

  public Detector(LinkedBlockingQueue queue, ConcurrentHashMap accountTrackingMap)
  {
    this.queue = queue;
    this.accountTrackingMap = accountTrackingMap;
  }

  public void process() {

    try {
      //TODO: DetectionTask by rules
      Object event = queue.take();
      long accountNumber = 0;

      if(accountNumber != 0) {

        if (event instanceof NewAccountEvent) {
          accountNumber = ((NewAccountEvent) event).getAccountNumber();
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((NewAccountEvent) event).getIssuedTimestamp())
          );
        } else if (event instanceof DepositEvent) {
          accountNumber = ((NewAccountEvent) event).getAccountNumber();
          AccountTrackingInformation accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
          accountTrackingInformation.deposit(((DepositEvent) event).getDepositAmount());

          if (isPassRules(accountTrackingInformation)) {
            accountTrackingMap.put(
                accountNumber,
                new AccountTrackingInformation(((DepositEvent) event).getIssuedTimestamp())
            );
          }
        } else if (event instanceof WithdrawEvent) {
          accountNumber = ((NewAccountEvent) event).getAccountNumber();
          AccountTrackingInformation accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
          accountTrackingInformation.withdraw(((WithdrawEvent) event).getWithdrawalAmount());

          if (isPassRules(accountTrackingInformation)) {
            accountTrackingMap.put(
                accountNumber,
                new AccountTrackingInformation(((WithdrawEvent) event).getIssuedTimestamp())
            );
          }
        } else if (event instanceof RemittanceEvent) {
          accountNumber = ((NewAccountEvent) event).getAccountNumber();
          AccountTrackingInformation accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
          accountTrackingInformation.remittance(((RemittanceEvent) event).getRemittanceAmount());

          if (isPassRules(accountTrackingInformation)) {
            accountTrackingMap.put(
                accountNumber,
                new AccountTrackingInformation(((RemittanceEvent) event).getIssuedTimestamp())
            );
          }
        }
      }
    } catch (InterruptedException e) {
      logger.info( "InterruptedException: " + e.getStackTrace() );
    } catch (Exception e) {
      logger.error("Exception: " + e.getStackTrace());
    }
  }

  private boolean isPassRules(AccountTrackingInformation accountTrackingInformation) {

    return true;
  }

  @Override
  public void run()
  {
    logger.info("Detector thread is running");

    while(true) {
      process();
    }
  }
}

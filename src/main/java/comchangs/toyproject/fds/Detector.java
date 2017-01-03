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
      Object event = queue.take();
      logger.debug("Received: " + event);
      long accountNumber = 0;
      AccountTrackingInformation accountTrackingInformation = null;

      if (event instanceof NewAccountEvent) {
        logger.debug("NewAccountEvent: " + accountNumber);
        accountNumber = ((NewAccountEvent) event).getAccountNumber();
        accountTrackingMap.put(
            accountNumber,
            new AccountTrackingInformation(((NewAccountEvent) event).getIssuedTimestamp())
        );
        logger.debug(accountTrackingMap);
      } else if (event instanceof DepositEvent) {
        logger.debug("DepositEvent: " + accountNumber);
        accountNumber = ((DepositEvent) event).getAccountNumber();
        //TODO: if the account still created, how to do
        accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
        accountTrackingInformation.deposit(((DepositEvent) event).getDepositAmount());

        if (isPassRules(accountTrackingInformation)) {
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((DepositEvent) event).getIssuedTimestamp())
          );
        }
      } else if (event instanceof WithdrawEvent) {
        logger.debug("WithdrawEvent: " + accountNumber);
        accountNumber = ((WithdrawEvent) event).getAccountNumber();
        //TODO: if the account still created, how to do
        accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
        accountTrackingInformation.withdraw(((WithdrawEvent) event).getWithdrawalAmount());

        if (isPassRules(accountTrackingInformation)) {
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((WithdrawEvent) event).getIssuedTimestamp())
          );
        }
      } else if (event instanceof RemittanceEvent) {
        logger.debug("RemittanceEvent: " + accountNumber);
        accountNumber = ((RemittanceEvent) event).getAccountNumber();
        //TODO: if the account still created, how to do
        accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
        accountTrackingInformation.remittance(((RemittanceEvent) event).getRemittanceAmount());

        if (isPassRules(accountTrackingInformation)) {
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((RemittanceEvent) event).getIssuedTimestamp())
          );
        }
      }

    } catch (InterruptedException e) {
      logger.info( "InterruptedException: " + e.getStackTrace() );
      e.printStackTrace();
    } catch (Exception e) {
      logger.error("Exception: " + e.getStackTrace());
      e.printStackTrace();
    }
  }

  private boolean isPassRules(AccountTrackingInformation accountTrackingInformation) {
    //TODO: How to check by rules
    logger.info("Pass: " + accountTrackingInformation);
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

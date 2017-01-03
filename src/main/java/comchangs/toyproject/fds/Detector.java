package comchangs.toyproject.fds;

import comchangs.toyproject.fds.event.DepositEvent;
import comchangs.toyproject.fds.event.NewAccountEvent;
import comchangs.toyproject.fds.event.RemittanceEvent;
import comchangs.toyproject.fds.event.WithdrawEvent;
import comchangs.toyproject.fds.rule.RuleA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.api.Rule;
import org.easyrules.api.RuleListener;
import org.easyrules.api.RulesEngine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

/**
 * Created by comchangs on 1/3/17.
 */
public class Detector implements Runnable
{
  private static final Logger logger = LogManager.getLogger(Detector.class);
  private LinkedBlockingQueue queue = null;
  private ConcurrentHashMap accountTrackingMap = null;
  private RuleA ruleA = null;
  private RulesEngine rulesEngine = null;

  public Detector(LinkedBlockingQueue queue, ConcurrentHashMap accountTrackingMap)
  {
    this.queue = queue;
    this.accountTrackingMap = accountTrackingMap;
  }

  public void process() {

    try {
      Object event = queue.take();

      long start = System.currentTimeMillis();
      logger.debug("Received: " + event);
      long accountNumber = 0;
      AccountTrackingInformation accountTrackingInformation = null;

      if (event instanceof NewAccountEvent) {
        accountNumber = ((NewAccountEvent) event).getAccountNumber();
        logger.debug("NewAccountEvent: " + accountNumber);
        accountTrackingMap.put(
            accountNumber,
            new AccountTrackingInformation(((NewAccountEvent) event).getIssuedTimestamp())
        );
        logger.debug(accountTrackingMap);
      } else if (event instanceof DepositEvent) {
        accountNumber = ((DepositEvent) event).getAccountNumber();
        logger.debug("DepositEvent: " + accountNumber);

        while(accountTrackingMap.get(accountNumber) == null) {
          Thread.sleep(5);
        }

        accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
        accountTrackingInformation.deposit(((DepositEvent) event).getIssuedTimestamp(), ((DepositEvent) event).getDepositAmount());

        if (isPassRules("Deposit", accountTrackingInformation)) {
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((DepositEvent) event).getIssuedTimestamp())
          );
        }
      } else if (event instanceof WithdrawEvent) {
        accountNumber = ((WithdrawEvent) event).getAccountNumber();
        logger.debug("WithdrawEvent: " + accountNumber);

        while(accountTrackingMap.get(accountNumber) == null) {
          Thread.sleep(5);
        }

        accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
        accountTrackingInformation.withdraw(((WithdrawEvent) event).getIssuedTimestamp(), ((WithdrawEvent) event).getWithdrawalAmount());

        if (isPassRules("Withdraw", accountTrackingInformation)) {
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((WithdrawEvent) event).getIssuedTimestamp())
          );
        }
      } else if (event instanceof RemittanceEvent) {
        accountNumber = ((RemittanceEvent) event).getAccountNumber();
        logger.debug("RemittanceEvent: " + accountNumber);

        while(accountTrackingMap.get(accountNumber) == null) {
          Thread.sleep(5);
        }

        accountTrackingInformation = (AccountTrackingInformation) accountTrackingMap.get(accountNumber);
        accountTrackingInformation.remittance(((RemittanceEvent) event).getIssuedTimestamp(), ((RemittanceEvent) event).getRemittanceAmount());

        if (isPassRules("Remittance", accountTrackingInformation)) {
          accountTrackingMap.put(
              accountNumber,
              new AccountTrackingInformation(((RemittanceEvent) event).getIssuedTimestamp())
          );
        }
      }

      long end = System.currentTimeMillis();

      System.out.println( "Detection Time : " + (end - start) + "ms" );

    } catch (InterruptedException e) {
      logger.info( "InterruptedException: " + e.getStackTrace() );
      e.printStackTrace();
    } catch (Exception e) {
      logger.error("Exception: " + e.getStackTrace());
      e.printStackTrace();
    }
  }

  private boolean isPassRules(String eventType, AccountTrackingInformation accountTrackingInformation) {
    ruleA = new RuleA(eventType, accountTrackingInformation);
    rulesEngine.registerRule(ruleA);

    rulesEngine.fireRules();

    return true;
  }

  public void run()
  {
    logger.info("Detector thread is running");



    rulesEngine = aNewRulesEngine()
        .named("Fraud Detection")
        .build();

    while(true) {
      process();
    }
  }
}

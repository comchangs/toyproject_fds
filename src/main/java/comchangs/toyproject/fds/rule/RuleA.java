package comchangs.toyproject.fds.rule;

import comchangs.toyproject.fds.AccountTrackingInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.*;

/**
 * Created by comchangs on 02/01/2017.
 */

@Rule(name = "RuleA", description = "When final balance is less than 10,000 KRW by a withdraw in 2 hours, after deposit 90,000 KRW ~ 100 KRW in 7 days, after create new account")
public class RuleA
{
  private static final Logger logger = LogManager.getLogger(RuleA.class);
  private AccountTrackingInformation accountTrackingInformation;
  private String eventType;

  public RuleA()
  {
  }

  public RuleA(String eventType, AccountTrackingInformation accountTrackingInformation)
  {
    this.eventType = eventType;
    this.accountTrackingInformation = accountTrackingInformation;
  }

  @Condition
  public boolean evaluate() {
    return
        eventType.equals("Withdraw") &&
        accountTrackingInformation.getFinalBalance() <= 10000 &&
        accountTrackingInformation.getLastWithdrawTimestamp() - accountTrackingInformation.getLastDepositTimestamp() <= 2 * 60 * 60 * 1000 &&
        accountTrackingInformation.getLastWithdrawTimestamp() - accountTrackingInformation.getAccountCreationTimestamp() <= 7 * 24 * 60 * 60 * 1000;
  }

  @Action
  public void execute() throws Exception {
    logger.info("Detected!: " + accountTrackingInformation);
  }

  public void setAccountTrackingInformation(AccountTrackingInformation accountTrackingInformation)
  {
    this.accountTrackingInformation = accountTrackingInformation;
  }

  public void setEventType(String eventType)
  {
    this.eventType = eventType;
  }
}

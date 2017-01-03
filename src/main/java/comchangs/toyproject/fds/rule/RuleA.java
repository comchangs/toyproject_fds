package comchangs.toyproject.fds.rule;

import comchangs.toyproject.fds.AccountTrackingInformation;
import org.easyrules.annotation.*;

/**
 * Created by comchangs on 02/01/2017.
 */

@Rule(name = "RuleA", description = "When final balance is less than 10,000 KRW by a withdraw in 2 hours, after deposit 90,000 KRW ~ 100 KRW in 7 days, after create new account")
public class RuleA
{
  private AccountTrackingInformation accountTrackingInformation;

  public RuleA()
  {
  }

  public RuleA(AccountTrackingInformation accountTrackingInformation)
  {
    this.accountTrackingInformation = accountTrackingInformation;
  }

  @Condition
  public boolean evaluate() {
    //my rule conditions
    return true;
  }

  @Action
  public void execute() throws Exception {
    //my actions
  }
}

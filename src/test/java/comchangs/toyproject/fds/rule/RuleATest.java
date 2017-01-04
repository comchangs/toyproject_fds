package comchangs.toyproject.fds.rule;

import comchangs.toyproject.fds.AccountTrackingInformation;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by comchangs on 04/01/2017.
 */
public class RuleATest
{
  @Test
  public void testCondition() throws Exception
  {
    AccountTrackingInformation accountTrackingInformation = new AccountTrackingInformation();
    accountTrackingInformation.setAccountCreationTimestamp(1483452666l);
    accountTrackingInformation.setLastDepositTimestamp(1483435041l);
    accountTrackingInformation.setLastDepositAmount(990000l);
    accountTrackingInformation.setLastWithdrawTimestamp(1483438921l);
    accountTrackingInformation.setFinalBalance(10000l);

    RuleA ruleA = new RuleA("Withdraw", accountTrackingInformation);
    Assert.assertTrue(ruleA.evaluate());

    ruleA = new RuleA("Deposit", accountTrackingInformation);
    Assert.assertFalse(ruleA.evaluate());

    accountTrackingInformation.setLastDepositAmount(100000l);
    ruleA = new RuleA("Withdraw", accountTrackingInformation);
    Assert.assertFalse(ruleA.evaluate());
  }
}

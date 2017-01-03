package comchangs.toyproject.fds;

/**
 * Created by comchangs on 1/3/17.
 */
public class AccountTrackingInformation
{
  private long accountCreationTimestamp;
  private long lastDepositTimestamp;
  private long lastWithdrawTimestamp;
  private long FinalBalance;

  public AccountTrackingInformation()
  {
  }

  public AccountTrackingInformation(long accountCreationTimestamp)
  {
    this.accountCreationTimestamp = accountCreationTimestamp;
  }

  public AccountTrackingInformation(
      long accountCreationTimestamp,
      long lastDepositTimestamp,
      long lastWithdrawTimestamp,
      long finalBalance
  )
  {
    this.accountCreationTimestamp = accountCreationTimestamp;
    this.lastDepositTimestamp = lastDepositTimestamp;
    this.lastWithdrawTimestamp = lastWithdrawTimestamp;
    FinalBalance = finalBalance;
  }

  public long getAccountCreationTimestamp()
  {
    return accountCreationTimestamp;
  }

  public void setAccountCreationTimestamp(long accountCreationTimestamp)
  {
    this.accountCreationTimestamp = accountCreationTimestamp;
  }

  public long getLastDepositTimestamp()
  {
    return lastDepositTimestamp;
  }

  public void setLastDepositTimestamp(long lastDepositTimestamp)
  {
    this.lastDepositTimestamp = lastDepositTimestamp;
  }

  public long getLastWithdrawTimestamp()
  {
    return lastWithdrawTimestamp;
  }

  public void setLastWithdrawTimestamp(long lastWithdrawTimestamp)
  {
    this.lastWithdrawTimestamp = lastWithdrawTimestamp;
  }

  public long getFinalBalance()
  {
    return FinalBalance;
  }

  public void setFinalBalance(long finalBalance)
  {
    FinalBalance = finalBalance;
  }

  public void deposit(long depositAmount) {
    FinalBalance += depositAmount;
  }

  public void withdraw(long withdrawalAmount) {
    FinalBalance -= withdrawalAmount;
  }

  public void remittance(long remittanceAmount) {
    FinalBalance -= remittanceAmount;
  }

  @Override
  public String toString()
  {
    return "AccountTrackingInformation{" +
           "accountCreationTimestamp=" + accountCreationTimestamp +
           ", lastDepositTimestamp=" + lastDepositTimestamp +
           ", lastWithdrawTimestamp=" + lastWithdrawTimestamp +
           ", FinalBalance=" + FinalBalance +
           '}';
  }
}

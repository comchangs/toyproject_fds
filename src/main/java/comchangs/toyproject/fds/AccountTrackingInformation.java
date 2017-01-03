package comchangs.toyproject.fds;

/**
 * Created by comchangs on 1/3/17.
 */
public class AccountTrackingInformation
{
  private long accountCreationTimestamp;
  private long lastDepositTimestamp;
  private long lastWithdrawTimestamp;
  private long lastRemittanceTimestamp;
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
      long lastRemittanceTimestamp,
      long finalBalance
  )
  {
    this.accountCreationTimestamp = accountCreationTimestamp;
    this.lastDepositTimestamp = lastDepositTimestamp;
    this.lastWithdrawTimestamp = lastWithdrawTimestamp;
    this.lastRemittanceTimestamp = lastRemittanceTimestamp;
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

  public long getLastRemittanceTimestamp()
  {
    return lastRemittanceTimestamp;
  }

  public void setLastRemittanceTimestamp(long lastRemittanceTimestamp)
  {
    this.lastRemittanceTimestamp = lastRemittanceTimestamp;
  }

  public long getFinalBalance()
  {
    return FinalBalance;
  }

  public void setFinalBalance(long finalBalance)
  {
    FinalBalance = finalBalance;
  }

  public void deposit(long issuedTimestamp, long depositAmount) {
    lastDepositTimestamp = issuedTimestamp;
    FinalBalance += depositAmount;
  }

  public void withdraw(long issuedTimestamp, long withdrawalAmount) {
    lastWithdrawTimestamp = issuedTimestamp;
    FinalBalance -= withdrawalAmount;
  }

  public void remittance(long issuedTimestamp, long remittanceAmount) {
    lastRemittanceTimestamp = issuedTimestamp;
    FinalBalance -= remittanceAmount;
  }

  @Override
  public String toString()
  {
    return "AccountTrackingInformation{" +
           "accountCreationTimestamp=" + accountCreationTimestamp +
           ", lastDepositTimestamp=" + lastDepositTimestamp +
           ", lastWithdrawTimestamp=" + lastWithdrawTimestamp +
           ", lastRemittanceTimestamp=" + lastRemittanceTimestamp +
           ", FinalBalance=" + FinalBalance +
           '}';
  }
}

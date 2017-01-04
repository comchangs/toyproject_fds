package comchangs.toyproject.fds;

/**
 * Created by comchangs on 1/3/17.
 */
public class AccountTrackingInformation
{
  private long accountCreationTimestamp;
  private long lastDepositTimestamp;
  private long lastDepositAmount;
  private long lastWithdrawTimestamp;
  private long lastWithdrawAmount;
  private long lastRemittanceTimestamp;
  private long lastRemittanceAmount;
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
      long lastDepositAmount,
      long lastWithdrawTimestamp,
      long lastWithdrawAmount,
      long lastRemittanceTimestamp,
      long lastRemittanceAmount,
      long finalBalance
  )
  {
    this.accountCreationTimestamp = accountCreationTimestamp;
    this.lastDepositTimestamp = lastDepositTimestamp;
    this.lastDepositAmount = lastDepositAmount;
    this.lastWithdrawTimestamp = lastWithdrawTimestamp;
    this.lastWithdrawAmount = lastWithdrawAmount;
    this.lastRemittanceTimestamp = lastRemittanceTimestamp;
    this.lastRemittanceAmount = lastRemittanceAmount;
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

  public long getLastDepositAmount()
  {
    return lastDepositAmount;
  }

  public void setLastDepositAmount(long lastDepositAmount)
  {
    this.lastDepositAmount = lastDepositAmount;
  }

  public long getLastWithdrawAmount()
  {
    return lastWithdrawAmount;
  }

  public void setLastWithdrawAmount(long lastWithdrawAmount)
  {
    this.lastWithdrawAmount = lastWithdrawAmount;
  }

  public long getLastRemittanceAmount()
  {
    return lastRemittanceAmount;
  }

  public void setLastRemittanceAmount(long lastRemittanceAmount)
  {
    this.lastRemittanceAmount = lastRemittanceAmount;
  }

  public void deposit(long issuedTimestamp, long depositAmount) {
    lastDepositTimestamp = issuedTimestamp;
    lastDepositAmount = depositAmount;
    FinalBalance += depositAmount;
  }

  public void withdraw(long issuedTimestamp, long withdrawalAmount) {
    lastWithdrawTimestamp = issuedTimestamp;
    lastWithdrawAmount = withdrawalAmount;
    FinalBalance -= withdrawalAmount;
  }

  public void remittance(long issuedTimestamp, long remittanceAmount) {
    lastRemittanceTimestamp = issuedTimestamp;
    lastRemittanceAmount = remittanceAmount;
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

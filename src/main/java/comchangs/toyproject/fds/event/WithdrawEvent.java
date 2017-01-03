package comchangs.toyproject.fds.event;

/**
 * Created by comchangs on 02/01/2017.
 */
public class WithdrawEvent
{
  private long issuedTimestamp;
  private long clientNumber;
  private long accountNumber;
  private long withdrawalAmount;

  public WithdrawEvent()
  {
  }

  public WithdrawEvent(long issuedTimestamp, long clientNumber, long accountNumber, long withdrawalAmount)
  {
    this.issuedTimestamp = issuedTimestamp;
    this.clientNumber = clientNumber;
    this.accountNumber = accountNumber;
    this.withdrawalAmount = withdrawalAmount;
  }

  public long getIssuedTimestamp()
  {
    return issuedTimestamp;
  }

  public void setIssuedTimestamp(long issuedTimestamp)
  {
    this.issuedTimestamp = issuedTimestamp;
  }

  public long getClientNumber()
  {
    return clientNumber;
  }

  public void setClientNumber(long clientNumber)
  {
    this.clientNumber = clientNumber;
  }

  public long getAccountNumber()
  {
    return accountNumber;
  }

  public void setAccountNumber(long accountNumber)
  {
    this.accountNumber = accountNumber;
  }

  public long getWithdrawalAmount()
  {
    return withdrawalAmount;
  }

  public void setWithdrawalAmount(long withdrawalAmount)
  {
    withdrawalAmount = withdrawalAmount;
  }

  @Override
  public String toString()
  {
    return "WithdrawEvent{" +
           "issuedTimestamp=" + issuedTimestamp +
           ", clientNumber=" + clientNumber +
           ", accountNumber=" + accountNumber +
           ", withdrawalAmount=" + withdrawalAmount +
           '}';
  }
}

package comchangs.toyproject.fds.model;

/**
 * Created by comchangs on 02/01/2017.
 */
public class DepositEvent
{
  private long issuedTimestamp;
  private int clientNumber;
  private long accountNumber;
  private long depositAmount;

  public DepositEvent()
  {
  }

  public DepositEvent(long issuedTimestamp, int clientNumber, long accountNumber, long depositAmount)
  {
    this.issuedTimestamp = issuedTimestamp;
    this.clientNumber = clientNumber;
    this.accountNumber = accountNumber;
    this.depositAmount = depositAmount;
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

  public void setClientNumber(int clientNumber)
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

  public long getDepositAmount()
  {
    return depositAmount;
  }

  public void setDepositAmount(long depositAmount)
  {
    this.depositAmount = depositAmount;
  }

  @Override
  public String toString()
  {
    return "DepositEvent{" +
           "issuedTimestamp=" + issuedTimestamp +
           ", clientNumber=" + clientNumber +
           ", accountNumber=" + accountNumber +
           ", depositAmount=" + depositAmount +
           '}';
  }
}

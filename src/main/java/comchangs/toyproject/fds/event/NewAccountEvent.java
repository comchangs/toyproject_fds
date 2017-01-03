package comchangs.toyproject.fds.event;

/**
 * Created by comchangs on 02/01/2017.
 */
public class NewAccountEvent
{
  private long accountNumber;
  private int clientNumber;
  private long issuedTimestamp;

  public NewAccountEvent()
  {
  }

  public NewAccountEvent(long accountNumber, int clientNumber, long issuedTimestamp)
  {
    this.accountNumber = accountNumber;
    this.clientNumber = clientNumber;
    this.issuedTimestamp = issuedTimestamp;
  }

  public long getAccountNumber()
  {
    return accountNumber;
  }

  public void setAccountNumber(long accountNumber)
  {
    this.accountNumber = accountNumber;
  }

  public long getClientNumber()
  {
    return clientNumber;
  }

  public void setClientNumber(int clientNumber)
  {
    this.clientNumber = clientNumber;
  }

  public long getIssuedTimestamp()
  {
    return issuedTimestamp;
  }

  public void setIssuedTimestamp(long issuedTimestamp)
  {
    this.issuedTimestamp = issuedTimestamp;
  }

  @Override
  public String toString()
  {
    return "DepositEvent{" +
           "accountNumber=" + accountNumber +
           ", clientNumber=" + clientNumber +
           ", issuedTimestamp=" + issuedTimestamp +
           '}';
  }
}

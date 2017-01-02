package comchangs.toyproject.fds.model;

import java.util.Arrays;

/**
 * Created by comchangs on 02/01/2017.
 */
public class RemittanceEvent
{
  private long issuedTimestamp;
  private int clientNumber;
  private long accountNumber; //It isn't in requirements but I think it should be because a client can hold multiple accounts.
  private long remittanceAccountNumber;
  private long balanceBeforeRemittance;
  private byte[] remittanceBankName;
  private byte[] remittanceAccountHolderName;
  private long remittanceAmount;

  public RemittanceEvent()
  {
  }

  public RemittanceEvent(
      long issuedTimestamp,
      int clientNumber,
      long accountNumber,
      long remittanceAccountNumber,
      long balanceBeforeRemittance,
      byte[] remittanceBankName,
      byte[] remittanceAccountHolderName,
      long remittanceAmount
  )
  {
    this.issuedTimestamp = issuedTimestamp;
    this.clientNumber = clientNumber;
    this.accountNumber = accountNumber;
    this.remittanceAccountNumber = remittanceAccountNumber;
    this.balanceBeforeRemittance = balanceBeforeRemittance;
    this.remittanceBankName = remittanceBankName;
    this.remittanceAccountHolderName = remittanceAccountHolderName;
    this.remittanceAmount = remittanceAmount;
  }

  public long getIssuedTimestamp()
  {
    return issuedTimestamp;
  }

  public void setIssuedTimestamp(long issuedTimestamp)
  {
    this.issuedTimestamp = issuedTimestamp;
  }

  public int getClientNumber()
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

  public long getRemittanceAccountNumber()
  {
    return remittanceAccountNumber;
  }

  public void setRemittanceAccountNumber(long remittanceAccountNumber)
  {
    this.remittanceAccountNumber = remittanceAccountNumber;
  }

  public long getBalanceBeforeRemittance()
  {
    return balanceBeforeRemittance;
  }

  public void setBalanceBeforeRemittance(long balanceBeforeRemittance)
  {
    this.balanceBeforeRemittance = balanceBeforeRemittance;
  }

  public byte[] getRemittanceBankName()
  {
    return remittanceBankName;
  }

  public void setRemittanceBankName(byte[] remittanceBankName)
  {
    this.remittanceBankName = remittanceBankName;
  }

  public byte[] getRemittanceAccountHolderName()
  {
    return remittanceAccountHolderName;
  }

  public void setRemittanceAccountHolderName(byte[] remittanceAccountHolderName)
  {
    this.remittanceAccountHolderName = remittanceAccountHolderName;
  }

  public long getRemittanceAmount()
  {
    return remittanceAmount;
  }

  public void setRemittanceAmount(long remittanceAmount)
  {
    this.remittanceAmount = remittanceAmount;
  }

  @Override
  public String toString()
  {
    return "RemittanceEvent{" +
           "issuedTimestamp=" + issuedTimestamp +
           ", clientNumber=" + clientNumber +
           ", accountNumber=" + accountNumber +
           ", remittanceAccountNumber=" + remittanceAccountNumber +
           ", balanceBeforeRemittance=" + balanceBeforeRemittance +
           ", remittanceBankName=" + Arrays.toString(remittanceBankName) +
           ", remittanceAccountHolderName=" + Arrays.toString(remittanceAccountHolderName) +
           ", remittanceAmount=" + remittanceAmount +
           '}';
  }
}

package com.cremitapp.corebankingsim.model;

public class LedgerEntryRequestData {
  private String fromAccount;
  private String toAccount;
  private String merchantRefId;
  private String bankRefId;

  public String getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
  }

  public String getToAccount() {
    return toAccount;
  }

  public void setToAccount(String toAccount) {
    this.toAccount = toAccount;
  }

  public String getMerchantRefId() {
    return merchantRefId;
  }

  public void setMerchantRefId(String merchantRefId) {
    this.merchantRefId = merchantRefId;
  }

  public String getBankRefId() {
    return bankRefId;
  }

  public void setBankRefId(String bankRefId) {
    this.bankRefId = bankRefId;
  }

  @Override
  public String toString() {
    return "LedgerEntryRequest [fromAccount="
        + fromAccount
        + ", toAccount="
        + toAccount
        + ", merchantRefId="
        + merchantRefId
        + ", bankRefId="
        + bankRefId
        + "]";
  }
}

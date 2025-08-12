package com.cremitapp.corebankingsim.controller;

import com.cremitapp.corebankingsim.model.LedgerEntryRequestData;
import com.cremitapp.corebankingsim.service.CorebankingService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/corebanking")
public class CorebankingController {

  private static final Logger logger = LoggerFactory.getLogger(CorebankingController.class);

  @Autowired private CorebankingService corebankingService;

  @PostMapping("/switch/cdci/outwardTransfer")
  public ResponseEntity<Map<String, Object>> fundTransferViaSwitch(
      @RequestBody LedgerEntryRequestData request) {
    String fromAccount = request.getFromAccount();
    String toAccount = request.getToAccount();
    String merchantRefId = request.getMerchantRefId();
    String bankRefId = request.getBankRefId();

    logger.info(
        "CEFT Outward Transfer request received | fromAccount: [{}] | toAccount: [{}] | merchantRefId: [{}] | bankRefId: [{}]",
        fromAccount,
        toAccount,
        merchantRefId,
        bankRefId);

    String message =
        corebankingService.processSwitchOutwardTransfer(
            fromAccount, toAccount, merchantRefId, bankRefId);

    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", message);
    response.put("merchantRefId", merchantRefId);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/cdci/fundtransferToParkingAccount")
  public ResponseEntity<Map<String, Object>> outwardTransferViaCdci(
      @RequestBody LedgerEntryRequestData request) {
    String fromAccount = request.getFromAccount();
    String toAccount = request.getToAccount();
    String merchantRefId = request.getMerchantRefId();
    String bankRefId = request.getBankRefId();

    logger.info(
        "Internal Fund Transfer request recieved | fromAccount: [{}] | toAccount: [{}] | merchantRefId: [{}] | bankRefId: [{}]",
        fromAccount,
        toAccount,
        merchantRefId,
        bankRefId);

    String message =
        corebankingService.processCdciFundTransfer(
            fromAccount, toAccount, merchantRefId, bankRefId);

    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", message);
    response.put("merchantRefId", merchantRefId);

    return ResponseEntity.ok(response);
  }
}

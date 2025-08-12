package com.cremitapp.corebankingsim.service;

import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class CorebankingService {

    private static final Logger logger = LogManager.getLogger(CorebankingService.class);

    public String processSwitchOutwardTransfer(String fromAccount, String toAccount, String merchantRefId,
            String bankRefId) {P
        final String ceftrReferenceNumber = "CEFT-" + System.currentTimeMillis();
        logger.info(
                "CEFT Outward transfer from {} Intermediate Remittance Account {} to Outward Account {} with merchantRefId: {} and BankRefId: {} initiated.",
                ceftrReferenceNumber, fromAccount, toAccount, merchantRefId, bankRefId);
        int totalDelay = 90000; 
        int interval = 10000;   
        int elapsed = 0;
        while (elapsed < totalDelay) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted during delay", e);
                break;
            }
            elapsed += interval;
            logger.info("Processing CEFT transfer {}... {} seconds elapsed", ceftrReferenceNumber, elapsed / 1000);
        }

        logger.info(
                "CEFT transfer from {} Intermediate Remittance Account {} to Outward Account {} with merchantRefId: {} and BankRefId: {} has been processed successfully.",
                ceftrReferenceNumber, fromAccount, toAccount, merchantRefId, bankRefId);
        return ceftrReferenceNumber;
    }

    public String processCdciFundTransfer(String fromAccount, String toAccount, String merchantRefId,
            String bankRefId) {
        final String iftReferenceNumber = "IFT-" + System.currentTimeMillis();
        logger.info(
                "Internal Fund transfer {} from WISE Remmittance Account {} to Intermediate Remittance Account {} with MerchantRefId: {} and BankRefId: {} initiated.",
                iftReferenceNumber, fromAccount, toAccount, merchantRefId, bankRefId);

        logger.info(
                "Internal Fund transfer {} from WISE Remmittance Account {} to Intermediate Remittance Account {} with MerchantRefId: {} and BankRefId: {} has been processed successfully.",
                iftReferenceNumber, fromAccount, toAccount, merchantRefId, bankRefId);
        return iftReferenceNumber;
    }
}
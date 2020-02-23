/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paypal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import entities.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Cart;

/**
 *
 * @author tuannnh
 */
public class PaypalServices implements Serializable {

    private Payer getPayerInformation(String email) throws Exception {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();

        payerInfo.setEmail(email);
        payer.setPayerInfo(payerInfo);
        return payer;
    }

    private RedirectUrls getRedirectURLs() throws Exception {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(Constants.CANCEL_URL);
        redirectUrls.setReturnUrl(Constants.RETURN_URL);
        return redirectUrls;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    public Payment getPaymentDetails(String paymentId) throws Exception {
        APIContext apiContext = new APIContext(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId)
            throws Exception {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.MODE);

        return payment.execute(apiContext, paymentExecution);
    }

    private List<Transaction> getTransactionInformation(Cart cart) throws Exception {
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(cart.getTotal());

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        Item item;
        for (Product product : cart.getPurchasedItems()) {
            item = new Item();
            item.setCurrency("USD");
            item.setName(product.getName());
            item.setPrice(String.valueOf(product.getPrice()));
            item.setQuantity(String.valueOf(product.getQuantity()));
            items.add(item);
        }
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();

        listTransaction.add(transaction);
        return listTransaction;

    }

    public String authorizePayment(Cart cart) throws Exception {
        Payer payer = getPayerInformation(cart.getCustomer().getEmail());
        RedirectUrls redirectUrls = getRedirectURLs();

        List<Transaction> listTransaction = getTransactionInformation(cart);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.MODE);

        Payment approvedPayment = requestPayment.create(apiContext);
        return getApprovalLink(approvedPayment);

    }

}

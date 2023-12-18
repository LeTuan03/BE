package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.configs.VNPayConfig;
import com.example.volunteer_campaign_management.dtos.VNPayDTO;
import com.example.volunteer_campaign_management.entities.ContributionsEntity;
import com.example.volunteer_campaign_management.repositories.ContributionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/volunteer-campaign-management/api/v1")
public class VNPayController {

    @Autowired
    private ContributionsRepository repository;

    @PutMapping("/pay")
    public String getPay(@RequestBody VNPayDTO vnPayDTO) throws UnsupportedEncodingException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (vnPayDTO.getPrice()) * 100;
        String bankCode = "NCB";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");

        String encodedName = URLEncoder.encode(vnPayDTO.getName(), StandardCharsets.UTF_8.toString());
        String encodedDescription = URLEncoder.encode(vnPayDTO.getDescription(), StandardCharsets.UTF_8.toString());

        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl + "?name=" + encodedName + "&description=" + encodedDescription);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    @GetMapping("/checkPay2")
    public RedirectView checkPaymentStatus(
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("description") String encodedDescription,
            @RequestParam("name") String encodedName,
            @RequestParam("vnp_Amount") String amount

    ) {
        try {

            String decodedName = URLDecoder.decode(encodedName, StandardCharsets.UTF_8.toString());
            String decodedDescription = URLDecoder.decode(encodedDescription, StandardCharsets.UTF_8.toString());

            if ("00".equals(responseCode)) {
                // Thanh toán thành công
                Date currentDate = new Date();

                ContributionsEntity newObj = new ContributionsEntity();
                newObj.setDonationDay(currentDate);


                if (decodedName.isEmpty() && decodedDescription.isEmpty()) {
                    newObj.setDescription("User anonymous đã quyên góp cho chiến dịch mùa hè xanh");
                    newObj.setName("User anonymous");
                } else if (decodedName.isEmpty()) {
                    newObj.setDescription(decodedDescription);
                    newObj.setName("User anonymous");
                } else if (decodedDescription.isEmpty()) {
                    newObj.setDescription(decodedName + " đã quyên góp cho chiến dịch mùa hè xanh");
                    newObj.setName(decodedName);
                } else {
                    newObj.setDescription(decodedDescription);
                    newObj.setName(decodedName);
                }

                newObj.setPrice(Float.parseFloat(amount) / 100.0f);
                repository.save(newObj);
                return new RedirectView(VNPayConfig.urlSuccess);
            } else {
                // Xử lý thanh toán thất bại
                return new RedirectView(VNPayConfig.urlFail);
            }

        }catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return new RedirectView(VNPayConfig.urlFail);
        }
    }






}

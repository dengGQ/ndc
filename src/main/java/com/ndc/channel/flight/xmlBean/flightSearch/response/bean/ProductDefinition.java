package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

import java.util.List;

public class ProductDefinition {

    private String productCode; //onlyFd
    private String productCornerSign;//
    private String productDesc;//
    private String productDescII;//
    private String productName;//
    private String productNotice;
    private List<String> productResource; //[],
    private String promotionCode;
    private String specialCode; //onlyFd",
    private String uRL; //null

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCornerSign() {
        return productCornerSign;
    }

    public void setProductCornerSign(String productCornerSign) {
        this.productCornerSign = productCornerSign;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductDescII() {
        return productDescII;
    }

    public void setProductDescII(String productDescII) {
        this.productDescII = productDescII;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNotice() {
        return productNotice;
    }

    public void setProductNotice(String productNotice) {
        this.productNotice = productNotice;
    }

    public List<String> getProductResource() {
        return productResource;
    }

    public void setProductResource(List<String> productResource) {
        this.productResource = productResource;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }
}

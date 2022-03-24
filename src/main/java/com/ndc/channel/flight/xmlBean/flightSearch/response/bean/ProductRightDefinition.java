package com.ndc.channel.flight.xmlBean.flightSearch.response.bean;

import java.util.List;

public class ProductRightDefinition {

    private ProductDefinition productDefinition;

    private List<RightDefinition> rightDefinitionList;

    public ProductDefinition getProductDefinition() {
        return productDefinition;
    }

    public void setProductDefinition(ProductDefinition productDefinition) {
        this.productDefinition = productDefinition;
    }

    public List<RightDefinition> getRightDefinitionList() {
        return rightDefinitionList;
    }

    public void setRightDefinitionList(List<RightDefinition> rightDefinitionList) {
        this.rightDefinitionList = rightDefinitionList;
    }
}

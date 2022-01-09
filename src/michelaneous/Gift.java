package michelaneous;

import enums.Category;

public class Gift {
    private String productName;
    private Double price;
    private Category category;
    private Integer quantity;

    public Gift() {
        this.productName = null;
        this.price = (double) 0;
        this.category = null;
        this.quantity = 0;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /** getter for the product name */
    public String getProductName() {
        return productName;
    }


    /** setter for the product name */
    public void setProductName(final String productName) {
        this.productName = productName;
    }


    /** getter for the price */
    public Double getPrice() {
        return price;
    }


    /** setter for the price */
    public void setPrice(final Double price) {
        this.price = price;
    }


    /** getter for the category */
    public Category getCategory() {
        return category;
    }


    /** setter for the category */
    public void setCategory(final Category category) {
        this.category = category;
    }
}

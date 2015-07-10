package com.ksufinski.models;

public class ProductItem {

    private final String name;

    public ProductItem(String name){
        this.name = name ;

    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;

        ProductItem productItem= (ProductItem) o;


        if (name != null ? !name.equals(productItem.name) : productItem.name != null) return false;

        return true;
    }
}

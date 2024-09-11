package model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<SelectProduct> currentProducts = new ArrayList<>();
    private int countMoney;

    public void add(SelectProduct selectProduct){
        int index = contains(selectProduct);
        if(index != -1){
            currentProducts.get(index)
                    .setCount(currentProducts.get(index).getCount()+selectProduct.getCount());
        }else {
            currentProducts.add(selectProduct);
        }
        countMoney += selectProduct.getProduct().getPrice() * selectProduct.getCount();
    }
    public void add(Product product, int count){

        currentProducts.add(new SelectProduct(product, count));
        countMoney += product.getPrice()*count;

    }
    public void add(int id, int price, String name, int allCount, int count){
        currentProducts.add(new SelectProduct(new Product(id, price, name, allCount), count));
        countMoney += price * count;
    }
    public List<SelectProduct> getCurrentProducts() {
        return currentProducts;
    }

    public void setCurrentProducts(List<SelectProduct> currentProducts) {
        this.currentProducts = currentProducts;
    }

    public int getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(int countMoney) {
        this.countMoney = countMoney;
    }

    public int contains(SelectProduct selectProduct){
        for(int i = 0; i < currentProducts.size(); i++){
            if(currentProducts.get(i).getProduct().getId() == selectProduct.getProduct().getId()){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(SelectProduct product:currentProducts){
            result.append("ID - ").append(product.getProduct().getId()).append('\n');
            result.append("Name - ").append(product.getProduct().getName()).append("\n").append("Price - ").append(product.getProduct().getPrice())
                    .append("\n").append("Count - ").append(product.getCount()).append("\n====================\n");
        }
        result.append("Total - ").append(countMoney);
        return result.toString();
    }
}

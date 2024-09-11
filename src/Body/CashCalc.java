package Body;

import Repository.BasketRepository;
import model.Basket;
import model.Product;
import Repository.ProductRepository;
import model.SelectProduct;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CashCalc {
    private final BasketRepository basketRepository = new BasketRepository();
    private final ProductRepository productRepository;

    public CashCalc(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
    public String product_selection(float count_money){
        if(basketRepository.getBaskets().containsKey((int)count_money)){
            return toString((int)count_money);
        }
        Basket new_basket = new Basket();

        for (int j = productRepository.getAll_product().size()-1; j >= 0; j--) {
            List<Product> products = new ArrayList<>();
            products.add(productRepository.getAll_product().get(j));
            float sum_price = productRepository.getAll_product().get(j).getPrice();
            for (int i = productRepository.getAll_product().size() - 1; i >= 0; i--) {
                if ((int) productRepository.getAll_product().get(i).getPrice() <= (int) (count_money - sum_price)) {
                    sum_price += productRepository.getAll_product().get(i).getPrice();
                    products.add(productRepository.getAll_product().get(i));
                }
            }
            if(sum_price == count_money){
                new_basket.setCurrentProducts(products);
                new_basket.setCountMoney((int)count_money);
                break;
            }
        }
        basketRepository.add(new_basket, (int)count_money);
        return toString((int)count_money);
    }
    */
    public List<Product> sort(List<Product> unSortedList){
        unSortedList.sort(Comparator.comparingInt(Product::getPrice));
        return unSortedList;
    }
    public String product_selection(int totalPrice){
        productRepository.setAll_product(sort(productRepository.getAll_product()));
        return createBasket(totalPrice).toString();
        //return productRepository.toString();
    }
    public Basket createBasket(int totalPrice){
        int tempTotalPrice = totalPrice;
        Basket basket = new Basket();
        int calcTry = 0;
        while(basket.getCountMoney() != totalPrice) {
            if(basket.getCountMoney() > totalPrice){
                basket = new Basket();
                if(calcTry < productRepository.getAll_product().size()-1) {
                    calcTry++;
                }else{
                    calcTry = 0;
                    totalPrice++;
                }
                tempTotalPrice = totalPrice;
            }
            for (int i = productRepository.getAll_product().size() - 1; i >= 0; i--) {
                if (tempTotalPrice % productRepository.getAll_product().get(i).getPrice() == 0) {
                    SelectProduct temp = findProduct(tempTotalPrice, productRepository.getAll_product().get(i));
                    basket.add(temp);
                    tempTotalPrice -= temp.getCount() * temp.getProduct().getPrice();
                    break;
                }
            }
            if(basket.getCountMoney() != totalPrice){
                tempTotalPrice -= productRepository.getAll_product().get(calcTry).getPrice();
                basket.add(new SelectProduct(productRepository.getAll_product().get(calcTry), 1));
            }
        }
        return basket;
    }
    public SelectProduct findProduct(int tempPrice, Product product){
        int count = 0;
        int basketPrice = 0;
        while (basketPrice < tempPrice){
            basketPrice += product.getPrice();
            count++;
        }
        return new SelectProduct(product, count);
    }
    public String toString(int id) {
        return basketRepository.getBaskets().get(id).toString();
    }

}


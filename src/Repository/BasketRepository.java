package Repository;

import model.Basket;

import java.util.HashMap;
import java.util.Map;

public class BasketRepository {
    private Map<Integer, Basket> baskets = new HashMap<>();

    public void add(Basket basket, int count_money){
        baskets.put(count_money, basket);
    }

    public Map<Integer, Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Map<Integer, Basket> baskets) {
        this.baskets = baskets;
    }
}

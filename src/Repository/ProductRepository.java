package Repository;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static int id = 0;
    private List<Product> all_product = new ArrayList<>();

    public void add(Product product){
        all_product.add(product);
        id++;
    }
    public void add(int id, int price, String name, int count){
        Product newProduct = new Product(id, price, name, count);
        all_product.add(newProduct);
        id++;
    }
    public void delete(int id){
        all_product.remove(id);
    }
    public void delete(String productName){
        all_product.removeIf(product -> product.getName().equals(productName));
    }
    public void delete(Product product){
        all_product.remove(product);
    }

    public void edit(int id, Product product){
        all_product.get(id).setId(product.getId());
        all_product.get(id).setPrice(product.getPrice());
        all_product.get(id).setName(product.getName());
        all_product.get(id).setCount(product.getCount());
    }
    public List<Product> getAll_product() {
        return all_product;
    }

    public void setAll_product(List<Product> all_product) {
        this.all_product = all_product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Product product:all_product){
            sb.append("ID - ").append(product.getId()).append('\n');
            sb.append("Name - ").append(product.getName()).append("\n")
                    .append("Price - ").append(product.getPrice()).append('\n')
                    .append("Count - ").append(product.getCount())
                    .append("\n===================\n");
        }
        return sb.toString();
    }
}

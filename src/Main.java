import Body.CashCalc;
import Repository.ProductRepository;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        String regex = "abcdefghijklmnopqrstuvwxyz";
        int count1 = 0, count10 = 0, count100 = 0;

        for(int i = 0; i < 500; i++){
            Random random = new Random();
            int min = 3, max = 9;
            int length = random.nextInt(max - min + 1) + min;
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < length; j++) {
                sb.append(regex.charAt(random.nextInt(regex.length())));
            }
            int min1 = 8, max1 = 999;
            int random_price = random.nextInt(max1 - min1 + 1) + min1;
            int random_markdown = random.nextInt(28) + 3;
            if(random_price > 200) {
                random_price = random_price/random_markdown;
            }
            if(random_price < 10)count1++;
            else if (random_price < 100) count10++;
            else count100++;

            productRepository.add(i+100000, random_price, sb.toString(), 99);
        }

        System.out.println("<10 = " + count1 + "\n>=10и<100 = " + count10 + "\n>=100 = " + count100 + "\n");
        CashCalc cashCalc = new CashCalc(productRepository);
        //System.out.println(productRepository);
        System.out.println(cashCalc.product_selection(3342));
    }
}
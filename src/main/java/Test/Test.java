package Test;

import Service.*;
import Servicee.Impl.*;
import com.proje.model.*;
import com00proje.repository.Impl.UserRepositoryImpl;
import com__proje.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        BrandService brandService = new BrandServiceImpl();

        CategoryService categoryService = new CategoryServiceImpl();

        UserService userService = new UserServiceImpl();

        ProductService productService = new ProductServiceImpl();

        KuponService kuponService = new KuponServiceImpl();

        /* List<Brand> brands = brandService.findBrands();

        for(Brand brand : brands){
            System.out.println("Brand Id :" + brand.getBrandId());
            System.out.println("Brand name :" + brand.getBrandName());
        } */

        /* Brand brand = brandService.findBrand(3);
        System.out.println(brand); */

        /* List<Category> categories = categoryService.findCategories();

        for(Category category : categories){

            System.out.println(category.getCategoryId() + " - " + category.getCategoryName());
        } */

        /* Category category = categoryService.findCategoryById(1);
        System.out.println(category.getCategoryName()); */

        /* List<Product> products = productService.findProducts();

        for(Product product : products){

            System.out.println(product.getProductId() + " - " + product.getAvaible() + " - " + product.getUnitPrice()
                    + " - " + product.getAddDate() + " - " + product.getUpdateDate() + " - " + product.getCategory().getCategoryName()
                    + " - " + product.getBrand().getBrandName());
        } */

        /* Product product = productService.findProductById(3);
        System.out.println(product); */

        /* List<User> users = userService.findUsers();
        for (User user: users) {
            System.out.println(user);
        } */

        /* User user = userService.findUserById(101);
        System.out.println(user); */

        /* User user = userService.findUserProductsById(100);
        System.out.println(user);
        List<Product> products = user.getProducts();
        System.out.println("Kullanıcının paylaştığı ürünler :");
        for (Product product : products){
            System.out.println("\t" + product);
        } */

        /* User user = new User(102,"Baki","Sen",new Date(new java.util.Date().getTime()),"canavar");
        userService.saveUser(user); */

        /* Product product = new Product(5,"Monster",5200,2,new Date(),null,null,null);
        Category category = categoryService.findCategoryById(2);
        product.setCategory(category);
        Brand brand = brandService.findBrand(5);
        product.setBrand(brand);

        productService.saveProduct(product); */

        /* userService.saveUserProduct(102,5); */

        /* Product product = productService.findProductById(5);
        product.setProductName("Monster Abra i7");

        productService.updateProduct(product); */

        /* Kupon kupon = new Kupon(4,"250TL");
        kuponService.saveKupon(kupon); */


        //kuponService.saveKuponUser(2,100);

        /* Kupon kupon = kuponService.findKuponById(2);
        System.out.println(kupon);
        kupon.setKuponName("180TL");
        kuponService.updateKupon(kupon); */

        //kuponService.removeKupon(4);

        /* List<Kupon> kupons = kuponService.findKupons();
        for (Kupon kupon : kupons){
            System.out.println(kupon);
        } */

        /* Kupon kupon = kuponService.findKuponUsersById(2);
        System.out.println(kupon);
        List<User> users = kupon.getUsers();
        System.out.println("Kuponu kullanan kişiler :");
        for (User user : users){
            System.out.println("\t" + user);
        } */


        User user = userService.findUserProductsById(100);
        List<Product> products = user.getProducts();
        double toplamTutar = 0;
        for (Product product : products) {
            toplamTutar += product.getUnitPrice();
        }
        System.out.println(user.getUserId() + " id'li kullanıcının Toplam tutarı :" + toplamTutar);

        Kupon kupon = kuponService.findKuponUsersById(1);
        System.out.println(kupon);
        List<User> users = kupon.getUsers();
        System.out.println("Kuponu kullanan kişiler :");
        for (User userler : users){
            System.out.println("\t" + userler);
            if (userler.getUserId() == user.getUserId()){

                int indirim = Integer.parseInt(kupon.getKuponName());
                toplamTutar -= indirim;
                System.out.println("İndirimli tutar :" + toplamTutar);
            }
        }



    }
}

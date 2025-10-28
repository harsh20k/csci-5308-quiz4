package org.example.utils;

import org.example.model.Product;
import org.example.model.ShoppingCart;

import java.util.List;

public class Printer {
    public static void checkoutPrinter(ShoppingCart shoppingCart, double total) {
        List<Product> products = shoppingCart.getItems();
        System.out.print("Item\t Qty\t \t \t Price\n");
        System.out.println();
        for (Product p : products) {
            System.out.printf("%s\t x%d\t \t \t \t %.2f\n", p.getName(), p.getQty(), p.getPrice());
        }
        System.out.println();
        System.out.println("==============================");
        System.out.printf("Total\t \t \t \t \t %.2f\n", total);
    }
}

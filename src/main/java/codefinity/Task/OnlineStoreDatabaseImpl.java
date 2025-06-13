package codefinity.Task;

import codefinity.model.Customer;
import codefinity.model.Product;

import java.util.HashMap;
import java.util.Map;

public class OnlineStoreDatabaseImpl implements OnlineStoreDatabase {

    private final Map<Integer, Product> products;
    private final Map<Integer, Customer> customers;

    public OnlineStoreDatabaseImpl() {
        this.products = new HashMap<>();
        this.customers = new HashMap<>();
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    @Override
    public void addProduct(Product product) {
        if (!products.containsKey(product.getId())) {
            products.put(product.getId(), product);
        }
    }

    @Override
    public void updateProduct(int productId, double newPrice, int newQuantity) {
        if (products.containsKey(productId)) {
            products.get(productId).setPrice(newPrice);
            products.get(productId).setQuantity(newQuantity);
        }
    }

    @Override
    public void removeProduct(int productId) {
        products.remove(productId);
    }

    @Override
    public void addCustomer(Customer customer) {
        if (!customers.containsKey(customer.getId())) {
            customers.put(customer.getId(), customer);
        }
    }

    @Override
    public void updateCustomer(int customerId, String newAddress) {
        if (customers.containsKey(customerId)) {
            customers.get(customerId).setAddress(newAddress);
        }
    }

    @Override
    public void removeCustomer(int customerId) {
        customers.remove(customerId);
    }

    @Override
    public void placeOrder(int customerId, int productId, int quantity) {
        if (customers.containsKey(customerId) && products.containsKey(productId)) {
            Product product = products.get(productId);
            Customer customer = customers.get(customerId);
            int availableQuantity = product.getQuantity();

            if (availableQuantity >= quantity) {
                product.setQuantity(availableQuantity - quantity);
                System.out.print("Order placed successfully!\n");
            } else {
                System.out.print("Not enough available quantity for customer " + customer.getName() + " with product " + product.getName() + " and quantity " + quantity);
            }
        } else {
            System.out.print("Failed to place the order. Check the data.\n");
        }
    }

    @Override
    public void displayAllProducts() {
        System.out.print("List of products:\n");
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue().getName()+ " - $" + entry.getValue().getPrice() + "\n");
        }
    }

    @Override
    public void displayAllCustomers() {
        System.out.print("List of customers:\n");
        for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue().getName() + " - " + entry.getValue().getAddress() + "\n");
        }
    }
}

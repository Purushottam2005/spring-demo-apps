/**
 * 
 */
package com.sivalabs.springcart.config;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sivalabs.springcart.entities.Address;
import com.sivalabs.springcart.entities.Category;
import com.sivalabs.springcart.entities.Customer;
import com.sivalabs.springcart.entities.Order;
import com.sivalabs.springcart.entities.OrderItem;
import com.sivalabs.springcart.entities.OrderStatus;
import com.sivalabs.springcart.entities.Payment;
import com.sivalabs.springcart.entities.Product;
import com.sivalabs.springcart.services.CatalogService;
import com.sivalabs.springcart.services.CustomerService;
import com.sivalabs.springcart.services.OrderService;

/**
 * @author Siva
 *
 */
@Component
public class DatabaseInitializer 
{
	@Autowired private CustomerService customerService;
	@Autowired private CatalogService catalogService;
	@Autowired private OrderService orderService;
	 
	@PostConstruct
	public void init()
	{
		initCustommers();
		initCatalog();
		initTestOrders();
	}

	private void initTestOrders() 
	{
		Customer customer = new Customer();
		customer.setFirstName("Siva");
		customer.setLastName("K");
		customer.setEmail("siva@gmail.com");
		customer.setPassword("siva");
		customer.setPhone("90000510456");
		
		Address billingAddress = new Address();
		billingAddress.setContactName("K. Siva Prasad Reddy");
		billingAddress.setAddrLine1("KP");
		billingAddress.setAddrLine2("BalajiNagar");
		billingAddress.setCity("Hyd");
		billingAddress.setState("AP");
		billingAddress.setCountry("India");
		billingAddress.setZipCode("500072");
		
		Address shippingAddress = new Address();
		shippingAddress.setContactName("K. Siva Prasad Reddy");
		shippingAddress.setAddrLine1("KP");
		shippingAddress.setAddrLine2("BalajiNagar");
		shippingAddress.setCity("Hyd");
		shippingAddress.setState("AP");
		shippingAddress.setCountry("India");
		shippingAddress.setZipCode("500072");
		
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		OrderItem item1 = new OrderItem();
		item1.setProduct(catalogService.findProduct(1));
		item1.setQuantity(1);
		
		OrderItem item2 = new OrderItem();
		item2.setProduct(catalogService.findProduct(2));
		item2.setQuantity(2);
		
		orderItems.add(item1);
		orderItems.add(item2);
		
		Payment payment = new Payment();
		payment.setCreditCardNumber("123-456-789");
		payment.setCvv("123");
		payment.setExpiryDate(new Date());
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setBillingAddress(billingAddress);
		order.setShippingAddress(shippingAddress);
		order.setOrderItems(orderItems);
		order.setPayment(payment );
		order.setStatus(OrderStatus.NEW);
		
		orderService.createOrder(order);
		
	}

	private void initCatalog()
	{
		Category c1 = new Category(0,"Java Books","Java Programming Books");
		Category c2 = new Category(0,"MS.NET Books","MS.NET Programming Books");
		Category c3 = new Category(0,"General Books","General Books");
		
		c1 = catalogService.createCategory(c1);
		c2 = catalogService.createCategory(c2);
		c3 = catalogService.createCategory(c3);
		
		
		Product p1 = new Product(0, "Java Persistence with MyBatis 3", "Java Persistence with MyBatis 3", new BigDecimal("250"), "MyBatis3.jpg",c1);
		Product p2 = new Product(0, "PrimeFaces Beginner\'s Guide: RAW", "PrimeFaces Beginner\'s Guide: RAW", new BigDecimal("350"), "PFBG_Raw.jpg",c1);
		Product p3 = new Product(0, "CleanCode", "CleanCode", new BigDecimal("450"), "CleanCode.jpg",c1);
		Product p4 = new Product(0, "HibernateInAction", "HibernateInAction", new BigDecimal("480"), "HibernateInAction.jpg",c1);
		Product p5 = new Product(0, "ProSpring3", "ProSpring3", new BigDecimal("750"), "ProSpring3.jpg",c1);
		Product p6 = new Product(0, "FirstLookatC#4.5", "FirstLookatC#4.5", new BigDecimal("500"), "FirstLookatCSharp4.5.jpg",c2);
		Product p7 = new Product(0, "Visual Studio 2012 Cookbook", "Visual Studio 2012 Cookbook", new BigDecimal("560"), "VS2012Cookbook.jpg",c2);
		Product p8 = new Product(0, "The Monk Who Sold His Ferrari", "The Monk Who Sold His Ferrari", new BigDecimal("150"), "nocover_book.jpg",c3);
		Product p9 = new Product(0, "Who Will Cry When You Die?", "Who Will Cry When You Die?", new BigDecimal("200"), "nocover_book.jpg",c3);
		
		catalogService.createProduct(p1);
		catalogService.createProduct(p2);
		catalogService.createProduct(p3);
		catalogService.createProduct(p4);
		catalogService.createProduct(p5);
		catalogService.createProduct(p6);
		catalogService.createProduct(p7);
		catalogService.createProduct(p8);
		catalogService.createProduct(p9);
		
	}

	private void initCustommers() 
	{
		Customer customer = null;
		customer = new Customer(0,"sivaprasadreddy.k@gmail.com","siva","Siva Prasad Reddy", "Katamreddy","9000510456");
		customerService.createCustomer(customer);
		
		customer = new Customer(0,"admin@gmail.com","admin","Administrator", null,"900000000000");
		customerService.createCustomer(customer);
		
	}
}

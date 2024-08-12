package edu.poly.asm.controller.customer;

import edu.poly.asm.controller.customer.sercurity.CustomerSessionService;
import edu.poly.asm.domain.Customer;
import edu.poly.asm.domain.OrderDetail;
import edu.poly.asm.domain.OrderP;
import edu.poly.asm.model.CartItem;
import edu.poly.asm.service.CustomerService;
import edu.poly.asm.service.OrderDetailService;
import edu.poly.asm.service.OrderPService;
import edu.poly.asm.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("site")
public class CartController {

	@Autowired
	OrderPService orderPService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerSessionService customerSession;

	private List<CartItem> cartItems = new ArrayList<>();

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam("productID") int productID, @RequestParam("productName") String productName,
			@RequestParam("unitPrice") double unitPrice) {
		boolean itemExists = false;
		for (CartItem item : cartItems) {
			if (item.getProductID() == productID) {
				item.setQuantity(item.getQuantity() + 1);
				itemExists = true;
				break;
			}
		}
		if (!itemExists) {
			cartItems.add(new CartItem(productID, productName, 1, unitPrice));
		}
		return "redirect:/site/cart";
	}

	@GetMapping("/cart")
	public String viewCart(Model model) {
		model.addAttribute("cartItems", cartItems);
		return "site/cart/cartItem";
	}

	@PostMapping("/updateQuantity")
	public String updateQuantity(@RequestParam("productId") int productId, @RequestParam("delta") int delta) {
		for (CartItem item : cartItems) {
			if (item.getProductID() == productId) {
				item.setQuantity(item.getQuantity() + delta);
				break;
			}
		}
		return "redirect:/site/cart";
	}

	@PostMapping("/removeItem")
	public String removeItem(@RequestParam("productId") int productId) {
		cartItems.removeIf(item -> item.getProductID() == productId);
		return "redirect:/site/cart";
	}

	// Phương thức tính tổng amount của các CartItem
	private double calculateTotalAmount(List<CartItem> cartItems) {
		double total = 0.0;
		for (CartItem item : cartItems) {
			total += item.getQuantity() * item.getUnitPrice();
		}
		return total;
	}

	@PostMapping("/checkout")
	public String checkout(Model model, @ModelAttribute("customerName") String customerName) {
		// Lấy thông tin customer từ session dựa vào tên
		System.out.println("CustomerID: " +customerSession.getCustomerId());
		Customer customer = customerService.getById(customerSession.getCustomerId());
		if (customer == null) {
			// Xử lý trường hợp không tìm thấy customer
			return "redirect:/site/cart";
		}

		// Tính toán amount dựa trên tổng unitPrice của cartItems
		double totalAmount = calculateTotalAmount(cartItems);

		// Tạo mới OrderP
		OrderP order = new OrderP();
		order.setOrderDate(new Date());
		order.setAmount(totalAmount);
		order.setStatus((short) 1); // Set trạng thái mặc định (ví dụ: đã đặt hàng)
		order.setCustomer(customer);

		// Lưu OrderP vào cơ sở dữ liệu
		order = orderPService.save(order);

		// Tạo OrderDetail cho từng CartItem và lưu vào cơ sở dữ liệu
		for (CartItem cartItem : cartItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderP(order);
			orderDetail.setProduct(productService.getById(cartItem.getProductID()));
			orderDetail.setQuantity(cartItem.getQuantity());
			orderDetail.setUnitPrice(cartItem.getUnitPrice());

			// Lưu OrderDetail vào cơ sở dữ liệu
			orderDetailService.save(orderDetail);
		}

		// Xóa cartItems sau khi đã checkout
		cartItems.clear();

		// Chuyển hướng đến trang hiển thị thông tin đơn hàng
		return "redirect:/site/cart/orderDetail/" + order.getOrderID();
	}
	
	@GetMapping("/cart/orderDetail/{orderId}")
    public String viewOrderDetail(@PathVariable("orderId") Integer orderId, Model model) {
        // Lấy thông tin OrderP từ orderId
        OrderP order = orderPService.getById(orderId);
        if (order == null) {
            return "redirect:/site/cart"; // Xử lý khi không tìm thấy đơn hàng
        }

        // Thêm order vào model để hiển thị trên view
        model.addAttribute("order", order);

        return "site/cart/orderDetail";
    }

}

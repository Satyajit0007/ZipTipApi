package com.masai.Impl;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masai.dao.AddressDao;
import com.masai.dao.AdminDao;
import com.masai.dao.BillDao;
import com.masai.dao.CartDao;
import com.masai.dao.CategoryDao;
import com.masai.dao.OrderDao;
import com.masai.dao.OrderDetailsDao;
import com.masai.dao.PaymentDao;
import com.masai.dao.ProductDao;
import com.masai.dao.SessionDao;
import com.masai.dao.UserDao;
import com.masai.exception.CartException;
import com.masai.exception.CategoryException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Address;
import com.masai.model.Admin;
import com.masai.model.Bill;
import com.masai.model.Cart;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.OrderDetails;
import com.masai.model.Orders;
import com.masai.model.Payment;
import com.masai.model.Product;
import com.masai.model.User;
import com.masai.service.BillService;
import com.masai.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService, BillService{

	@Autowired
	private UserDao uDao;
	
	@Autowired
	private SessionDao sDao;

	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao pDao;
	
	@Autowired
	private PaymentDao paymentDao;
 	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	
	@Override
	public User createUser(User user) throws UserException {
		
		User existingUser = uDao.findByMobileNo(user.getMobileNo());
		
		if(existingUser == null) {
			 uDao.save(user);
			 return user;
		}else {
			throw new UserException("User Already Registered With This Mobile Number");
		}
	}
          
	
	@Override
	public User addAddress(User user, String key) throws UserException {
		  CurrentUserSession loggedInUser = sDao.findByUuid(key);
		    if (loggedInUser == null) {
		        throw new UserException("Please provide a valid key to update a customer");
		    } else {
		           Optional<User> newUser = uDao.findById(loggedInUser.getCurrentUserId());
		           user.setUserId(newUser.get().getUserId());
		           user.setName(newUser.get().getName());
		           user.setMobileNo(newUser.get().getMobileNo());
		           user.setEmail(newUser.get().getEmail());
		           user.setPassword(newUser.get().getPassword());
		        for (Address address : user.getAddress()) {
		            address.setUserAddress(user);
		        }
		        return uDao.save(user);
		    }
	}

	@Override
	public Cart addToCart(String key , Integer id, Integer qty) throws CartException, LoginException, UserException, CategoryException, ProductException {
	   CurrentUserSession validSession = sDao.findByUuid(key);
	   if(validSession == null) {
		   throw new LoginException("Please Log In First..!");
	   }
	
	  Optional<User> user = uDao.findById(validSession.getCurrentUserId());
	  Optional<Product> product = pDao.findById(id);	  
      
	  
	  if(user.isPresent()) {
		  Cart cart = new Cart();
		  if(product.isPresent()) {
			  cart.setItemName(product.get().getItemName());
			  cart.setItemDescription(product.get().getItemDescription());
			  cart.setItemPrice(qty*product.get().getItemPrice());
			  cart.setCategoryName(product.get().getCategory().getCategoryName());
              cart.setResturentName(product.get().getAdmin().getName());
              cart.setResturentId(product.get().getAdmin().getAdminId());
			  cart.setUserMobileNo(user.get().getMobileNo());  
			  cart.setUserName(user.get().getName());
			  cart.setUserId(user.get().getUserId()); 
			  cart.setQty(qty);
			
		      
		  }else {
			  
			  throw new ProductException("Product Not Found..!");
		  }
		  
		  cartDao.save(cart);
		  return cart;
	  }else {
		  
		  throw new UserException("Only User Can Add Item To The Cart");
	  }
		
	}

	@Override
	public OrderDetails getOrderDetails(String key , Integer addressId  ) throws OrderException, LoginException, UserException {
		
		CurrentUserSession validSession = sDao.findByUuid(key);
		   if(validSession == null) {
			   throw new LoginException("Please Log In First..!");
		   }

		   int addressListId = 0;
//		   if(addressId == null) {
//			   addressListId = 0;
//		   }
		   

		   Optional<User> user = uDao.findById(validSession.getCurrentUserId());
		   
		   
		   List<Cart> cartListById = cartDao.findByUserId(validSession.getCurrentUserId());
		
		   List<Address> Findaddress = user.get().getAddress();
		   for(Address add : Findaddress ) {
			   if(add.getAddressId() == addressId) {
				   addressListId = addressId;
			   }
		   }
		   Optional<Address> newAdd = addressDao.findById(addressListId);
		   Address address = newAdd.get();
		   if( cartListById.size() !=0 ) {
			   int totalCartValue = 0;
			   for(Cart cart : cartListById) {
				   totalCartValue += cart.getItemPrice();
			   }

			   
			 OrderDetails orderdetails = new OrderDetails();
			 orderdetails.setUserId(user.get().getUserId());
			 orderdetails.setUserName(user.get().getName());
			 orderdetails.setUserMobile(user.get().getMobileNo());
			 orderdetails.setUserEmail(user.get().getEmail());
			 orderdetails.setAddressId(address.getAddressId());
			 orderdetails.setPincode(address.getPincode());
			 orderdetails.setState(address.getState());
			 orderdetails.setCountry(address.getCountry());
			 orderdetails.setBuildingName(address.getBuildingName());
			 orderdetails.setBuildingNo(address.getBuildingNo());
			 orderdetails.setAddressType(address.getAddressType());
			 orderdetails.setLandMark(address.getLandMark());
			 orderdetails.setCartTotalPrice(totalCartValue);
			 
			 
			 orderdetails.setCartList(cartListById);
			 orderDetailsDao.save(orderdetails);
			 
//			 cartDao.deleteAllInBatch(cartListById); 
			 
//			 orderdetails = orderDetailsDao.save(orderdetails);
//			 orderdetails.setCartList(cartListById);
//			 orderDetailsDao.save(orderdetails);
//           
//			 // Delete the carts
//			 for (Cart cart : cartListById) {
//			        cartDao.delete(cart);
//			 }
//			 
			 
			 
			 return orderdetails;
             
		 }else {
			 throw new OrderException("No Order Found In The Cart..!");
		 }
   		  
		  
	}


   
        
	@Override
	public Bill generateBill(String key) throws UserException, OrderException {
		CurrentUserSession validSession = sDao.findByUuid(key);
		if(validSession == null) {
	       throw new UserException("Please Log In First..!");
		}   
        List<Payment> paymentList = paymentDao.findByUserId(validSession.getCurrentUserId());
        Payment payment = paymentList.get(0);
            
        Optional<User> user = uDao.findById(validSession.getCurrentUserId());
        List<OrderDetails> orderList = orderDetailsDao.getOrderDetailsByUserId(user.get().getUserId());
        OrderDetails order =  orderList.get(0);
       if(payment.isPaymentStatus()) {
        	Bill bill =new Bill();
        	bill.setLocsalDateTime(LocalDateTime.now());
        	
        	if(user.isPresent()) {
        		bill.setUserId(user.get().getUserId());
        		bill.setUserName(user.get().getName());
        		bill.setOrderDetailsId(order.getOrderDetailsId());
        		bill.setUtr(payment.getUTR());
        		bill.setTotalBillPrice(order.getCartTotalPrice());
        	}else {
        		
        		throw new UserException("User Not Foud");
                    	
        	}
        	
        	bill.setUserId(user.get().getUserId());
        	return billDao.save(bill);
        }else {
        	throw new OrderException("Payment Failed"); 
        }   
	        
	}         
            


	
	
	@Override
	public List<Address> getUserAddressById(String uuid) throws LoginException, UserException {
		
		CurrentUserSession validSession = sDao.findByUuid(uuid); 
		   if(validSession == null) {
		       throw new LoginException("Please Log in first");
		   } 
		
		Optional<User> existingUser = uDao.findById(validSession.getCurrentUserId());
		 if(existingUser.isEmpty()) {
		    	throw new UserException("Please Register first");
		    }else {
		    	List<Address> addressList = existingUser.get().getAddress();
	            return addressList;	   
  		    }
	}

	@Override
	public User updateUser(User user, String key) throws UserException {
		CurrentUserSession loggedInUser = sDao.findByUuid(key);
	    if (loggedInUser == null) {
	        throw new UserException("Please provide a valid key to update a customer");
	    } else {
	         
	          
	        for (Address address : user.getAddress()) {
	            address.setUserAddress(user);
	        }
	        return uDao.save(user);
	    }
	}


	@Override
	public OrderDetails getBillByuser(String uniqueKey) throws LoginException, ProductException {
	   CurrentUserSession validSession = sDao.findByUuid(uniqueKey);
	   
	   if(validSession == null) {
		    throw new LoginException("Please Log in first..!");
	   }
	   
	    List<OrderDetails> od =  orderDetailsDao.getAllOrderByUserId(validSession.getCurrentUserId());

   	   List<Cart> cartListById = cartDao.findByUserId(validSession.getCurrentUserId());
	   od.get(od.size()-1).setCartList(cartListById);
	   if(od.size() == 0) {
		   throw new ProductException("No order found in order list");
	   }else {
		   return od.get(od.size() -1);
	   }
	  
	}


	@Override
	public User getUserById(String uniqueKey) throws UserException {
		CurrentUserSession activeSession = sDao.findByUuid(uniqueKey);
		if(activeSession == null) {
			throw new UserException("You are not logged in");
		}else {
			Optional<User> user = uDao.findById(activeSession.getCurrentUserId());
			if(user.isPresent()) {
				User newUser = user.get();
				return newUser;
			}else {
				throw new UserException("User Not Exists");
			}
		}
	}   
	

}

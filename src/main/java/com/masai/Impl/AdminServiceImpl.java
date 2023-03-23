package com.masai.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.xml.catalog.CatalogException;

import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dao.AdminDao;
import com.masai.dao.BillDao;
import com.masai.dao.CategoryDao;
import com.masai.dao.OrderDao;
import com.masai.dao.ProductDao;
import com.masai.dao.SessionDao;
import com.masai.dao.UserDao;
import com.masai.exception.CategoryException;
import com.masai.exception.OrderException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Address;
import com.masai.model.Admin;
import com.masai.model.Bill;
import com.masai.model.Category;
import com.masai.model.CurrentUserSession;
import com.masai.model.Orders;
import com.masai.model.Product;
import com.masai.model.User;
import com.masai.service.AdminService;
import com.masai.service.BillService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao aDao;
	
	@Autowired
	private SessionDao sDao;
	
	@Autowired
	private ProductDao pDao;
	
	@Autowired
	private CategoryDao catDao;
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private BillDao billDao;
	
	@Override
	public Admin registerAdmin(Admin admin) throws UserException {
       Admin existingAdmin = aDao.findByMobileNo(admin.getMobileNo());
		if(existingAdmin == null) {			 
			 aDao.save(admin);
			 return admin;
		}else {
			throw new UserException("User Already Registered With This Mobile Number");
		}     
	}
	
	@Override
	public Product addProduct(Product product, String key) throws ProductException, UserException {
        CurrentUserSession validSession = sDao.findByUuid(key);
		
		if(validSession == null) {
			throw new ProductException("Please Log In First..!");
		}
		
		Optional<Admin> admin =  aDao.findById(validSession.getCurrentUserId());
		
		if(admin.isPresent()) {
			Optional<Category> cat = catDao.findById(product.getCategory().getCategoryId());
			
			if(cat.isPresent()) {
				
				product.setAdmin(admin.get());
				Product newProduct  = pDao.save(product);
				return newProduct;
			}else {
				throw new UserException("Category Not Found..!");
			}
		}
		else {
			throw new UserException("Access Denied..!");
		}		
	}

	@Override
	public List<Product> getProductListByAdminId(String key) throws ProductException, UserException {
		
	return null;
	}

	@Override
	public Orders updateOrderStatus(Orders order, String key, Integer OrderId) throws UserException, OrderException {
		CurrentUserSession validSession = sDao.findByUuid(key);
		if(validSession == null) {
			throw new UserException("Admin Not Logged In, Log In First..!");
		}
		Optional<Orders> orderList =  orderDao.findById(OrderId);
		if(orderList.isPresent()) {
			if(orderList.get().getOrderID() == order.getOrderID()) {
				return orderDao.save(order);
			}else {
				throw new OrderException("No Order Found For this Order Id");
			}		
		}else {
			throw new OrderException("No Order Found For this Order Id");
		}	
	}

	@Override
	public List<Admin> getAllRestaurentList() throws UserException {
		// TODO Auto-generated method stub
		List<Admin> allRestaurent = aDao.findAll();
		return allRestaurent;
	}

	@Override
	public Admin getRestaurentById(Integer adminId) throws UserException {
		
		Optional<Admin> admin = aDao.findById(adminId);
		if(admin.isEmpty()) {
			throw new UserException("Restaurent can not found");
		}else {
			return admin.get();
		}
		
	}

}

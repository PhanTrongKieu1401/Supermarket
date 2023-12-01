package com.example.Supermarket.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Supermarket.model.Product;

public class ProductDAO extends DAO{

	public ProductDAO() {
		super();
	}
	
	public List<Product> searchProduct(String key){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM tblProduct WHERE name LIKE ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%"+key+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setSize(rs.getInt("size"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setProductionBatch(rs.getString("productionBatch"));
				product.setQuantityInStock(rs.getInt("quantityInStock"));
				product.setManufacturingDate(rs.getDate("manufacturingDate"));
				product.setExpiryDate(rs.getDate("expiryDate"));
				products.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Product checkProductById(String key){
		Product product = new Product();
		String sql = "SELECT * FROM tblProduct WHERE id = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setType(rs.getString("type"));
				product.setSize(rs.getInt("size"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getFloat("price"));
				product.setProductionBatch(rs.getString("productionBatch"));
				product.setQuantityInStock(rs.getInt("quantityInStock"));
				product.setManufacturingDate(rs.getDate("manufacturingDate"));
				product.setExpiryDate(rs.getDate("expiryDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public boolean addProduct(Product product){
		boolean result = false;
		String sql = "INSERT INTO tblProduct(name, type, size, description, price, quantityInStock, productionBatch, manufacturingDate, expiryDate) VALUES(?,?,?,?,?,?,?,?,?)";
		try{
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getName());
			ps.setString(2, product.getType());
			ps.setInt(3, product.getSize());
			ps.setString(4, product.getDescription());
			ps.setFloat(5, product.getPrice());
			ps.setInt(6, product.getQuantityInStock());
			ps.setString(7, product.getProductionBatch());
			ps.setDate(8, product.getManufacturingDate());
			ps.setDate(9, product.getExpiryDate());

			ps.executeUpdate();
			result = true;
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				product.setId(generatedKeys.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateProduct(Product product){
		String sql = "UPDATE tblProduct "
				+ "SET name = ?, type = ?, size = ?, description = ?, price = ?, quantityInStock = ?, manufacturingDate = ?, expiryDate = ? "
				+ "WHERE id = ?";
		boolean result = false;
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getType());
			ps.setInt(3, product.getSize());
			ps.setString(4, product.getDescription());
			ps.setFloat(5, product.getPrice());
			ps.setInt(6, product.getQuantityInStock());
			ps.setDate(7, product.getManufacturingDate());
			ps.setDate(8, product.getExpiryDate());
			ps.setInt(9, product.getId());
			ps.executeUpdate();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteProduct(String productId){
		boolean result = false;
		String sql = "delete from tblProduct where tblProduct.id = ?";
		try{	
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, productId);
			ps.executeUpdate();
			result = true;
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

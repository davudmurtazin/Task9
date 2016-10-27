package by.tc.tester.dao.impl.pool;

import by.tc.tester.dao.exception.DAOException;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
	private BlockingQueue<Connection> pool;
	private static ReentrantLock lock = new ReentrantLock();
	private static ConnectionPool instance;
	public static final String URL = "jdbc:mysql://localhost/test";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	public static final Integer POOL_SIZE = 5;
	private boolean flag;
	
	private ConnectionPool(){
		try {
			DriverManager.registerDriver(new Driver());
			pool = new ArrayBlockingQueue<>(POOL_SIZE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		for(int i=0; i<POOL_SIZE; i++) {
			try {
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				if(!pool.offer(connection)) {
					throw new RuntimeException("Can't initialize pool properly");
				}
			} catch (SQLException e) {
				throw new RuntimeException("Couldn't get connection!");
			}
		}
		flag = true;
	}

	public static ConnectionPool getInstance(){
		if(instance==null) {
			try {
				lock.lock();
				instance = new ConnectionPool();
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public Connection getConnection() throws DAOException {
		if(flag) {
			try {
				Connection connection = pool.take();

				return connection;
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new DAOException("Pool is not working!");
		}
	}
	
	public void returnConnection(Connection connection) throws SQLException, InterruptedException{
		if(connection == null){
			return;
		}
		connection.setAutoCommit(true);
		connection.setReadOnly(true);
		
		pool.put(connection);
	}


	public void releasePool() throws DAOException {
		Integer poolSize = 100;

		while(!pool.isEmpty()) {
			try {
				Connection connection = pool.take();
				connection.close();
			} catch (InterruptedException | SQLException e) {
				throw new DAOException("Couldn't release pool!");
			}
		}
	}

	public void releaseConnection(Connection connection) {
		try {
			if(connection != null && !connection.isClosed()) {
				if(!pool.offer(connection)) {
					throw new RuntimeException("Error returning connection to pool");
				}
			} else {
				throw new RuntimeException("Error returning connection to pool");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}

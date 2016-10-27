package by.tc.tester.dao.impl.mysql;

import by.tc.tester.bean.entity.User;
import by.tc.tester.bean.request.user.LoginResponse;
import by.tc.tester.dao.UserDAO;
import by.tc.tester.dao.exception.DAOException;
import by.tc.tester.dao.impl.pool.AbstractDAO;
import by.tc.tester.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;

public class MySQLUserDAO extends AbstractDAO implements UserDAO {
	public static final String INSERT_USER =
			"INSERT INTO `user` (Name, Login, Password, Rights) VALUES (?, ?, ?, ?)";
	public static final String SELECT_PERSON_BY_LOGIN_PASSWORD =
			"SELECT * FROM `user` WHERE Login = ? AND Password = ?;";
	public static final String SELECT_USER =
			"SELECT * FROM `user` WHERE Rights = ?;";

	@Override
	public LoginResponse logination(String login, String password) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		LoginResponse loginResponse = new LoginResponse();
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECT_PERSON_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				loginResponse.setUserID(resultSet.getInt("UserID"));
				loginResponse.setRights(resultSet.getString("Rights"));
				return loginResponse;
			} else {
				loginResponse.setResultMessage("Don't have this user!");
				loginResponse.setErrorStatus(true);
				return loginResponse;
			}
		} catch (DAOException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
	}

	@Override
	public int registration(String name, String login, String password, String rights) throws DAOException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int userID = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, login);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, rights);

			preparedStatement.executeUpdate();

			String query = "SELECT UserID FROM user WHERE Login = '" + login + "' AND "
					+ "Password = '" + password + "';";
			try {
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					userID = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e.getMessage());
			} finally {
				closePreparedStatement(preparedStatement);
				releaseConnection(connection);
			}
		}catch (DAOException e){
			throw new DAOException("Could not in register!");
		}
		return userID;
	}

	@Override
	public ArrayList<User> getUsers(String rights) throws ServiceException, DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<User> users = new ArrayList<>();
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECT_USER);
			preparedStatement.setString(1, rights);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				User user = new User();
				user.setUserID(resultSet.getInt("UserID"));
				user.setName(resultSet.getString("Name"));
				users.add(user);
			}
		} catch (DAOException | SQLException e) {
			throw new DAOException(e);
		} finally {
			closePreparedStatement(preparedStatement);
			releaseConnection(connection);
		}
		return users;
	}
}

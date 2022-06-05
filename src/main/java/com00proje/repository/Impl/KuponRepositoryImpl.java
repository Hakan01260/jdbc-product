package com00proje.repository.Impl;

import com.proje.model.*;
import com0proje.connection.DBCon;
import com__proje.repository.KuponRepository;
import com_proje.model.queries.KuponQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KuponRepositoryImpl implements KuponRepository {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;


    @Override
    public Kupon saveKupon(Kupon kupon) {

        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(KuponQuery.saveKuponQuery);
            preparedStatement.setInt(1,kupon.getKuponId());
            preparedStatement.setString(2, kupon.getKuponName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return kupon;
    }

    @Override
    public boolean saveKuponUser(int kuponId, int userId) {
        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(KuponQuery.saveKupon_UserQuery);
            preparedStatement.setInt(1,kuponId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return true;
    }

    @Override
    public Kupon updateKupon(Kupon kupon) {
        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(KuponQuery.updateKuponQuery);
            preparedStatement.setString(1,kupon.getKuponName());
            preparedStatement.setInt(2,kupon.getKuponId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }

        return kupon;
    }

    @Override
    public boolean removeKupon(int kuponId) {
        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(KuponQuery.deleteKupon_UserQuery);
            preparedStatement.setInt(1,kuponId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(KuponQuery.deleteKuponByIdQuery);
            preparedStatement.setInt(1,kuponId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {

            DBCon.closeConnection(connection);
        }

        return true;
    }

    @Override
    public Kupon findKuponById(int kuponId) {
        connection = DBCon.getConnection();
        Kupon kupon = null;
        try {
            preparedStatement = connection.prepareStatement(KuponQuery.findKuponByIdQuery);
            preparedStatement.setInt(1,kuponId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                int kuponIdd = resultSet.getInt("kuponId");
                String kuponName = resultSet.getString("kuponName");

                kupon = new Kupon(kuponIdd,kuponName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return kupon;
    }

    @Override
    public Kupon findKuponUsersById(int kuponId) {
        connection = DBCon.getConnection();

        Kupon kupon = null;

        try {
            preparedStatement = connection.prepareStatement(KuponQuery.findKuponUsersByIdQuery);
            preparedStatement.setInt(1,kuponId);
            resultSet = preparedStatement.executeQuery();

            boolean durum = true;
            List<User> users = new ArrayList<>();

            while (resultSet.next()){

                if (durum){

                    int kuponIdd = resultSet.getInt("kuponId");
                    String kuponName = resultSet.getString("kuponName");

                    kupon = new Kupon(kuponIdd,kuponName);
                    durum = false;
                }

                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date birthOfDate = resultSet.getDate("birthOfDate");
                String userName = resultSet.getString("userName");

                User user = new User(userId,firstName,lastName,birthOfDate,userName);

                users.add(user);
            }

            kupon.setUsers(users);

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            DBCon.closeConnection(connection);
        }

        return kupon;
    }

    @Override
    public List<Kupon> findKupons() {
        connection = DBCon.getConnection();
        List<Kupon> kupons = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(KuponQuery.findKuponsQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int kuponIdd = resultSet.getInt("kuponId");
                String kuponName = resultSet.getString("kuponName");

                Kupon kupon = new Kupon(kuponIdd,kuponName);
                kupons.add(kupon);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return kupons;
    }
}

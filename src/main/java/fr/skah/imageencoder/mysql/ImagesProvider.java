package fr.skah.imageencoder.mysql;

/*
 *  * @Created on 2021 - 15:39
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagesProvider {

    public boolean hasImage(byte[] bytes) {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement("SELECT bytes FROM images WHERE bytes = ?");
            preparedStatement.setBytes(1, bytes);
            boolean value = preparedStatement.executeQuery().next();
            preparedStatement.close();
            return value;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void addImage(String fileType, String category, String tag, boolean nsfw, byte[] bytes) {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        try {
            final PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement("INSERT INTO images(file_type, category, tag, nsfw, bytes) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, fileType);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, tag);
            preparedStatement.setBoolean(3, nsfw);
            preparedStatement.setBytes(5, bytes);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public byte[] getImage(int id) {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        try {
            final PreparedStatement preparedStatement = connectionManager.getConnection().prepareStatement("SELECT bytes FROM images WHERE id = ?");
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            byte[] bytesImage = null;
            while (resultSet.next()) bytesImage = resultSet.getBytes("bytes");


            preparedStatement.close();
            return bytesImage;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


}

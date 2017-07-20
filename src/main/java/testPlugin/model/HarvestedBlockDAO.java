package testPlugin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HarvestedBlockDAO {

    public static String tableCreation = "CREATE TABLE IF NOT EXISTS harvested_block (\n" +
            " id integer PRIMARY KEY AUTOINCREMENT,\n" +
            " block_type VARCHAR(30),\n" +
            " pos_x integer,\n" +
            " pos_y integer,\n" +
            " pos_z integer,\n" +
            " harvest_date integer);";

    private final String insertStatement = "INSERT INTO harvested_block(block_type,pos_x,pos_y,pos_z,harvest_date)" +
            " values (?, ?, ?, ?, ?);";

    private  final String selectStatement = "SELECT * From harvested_block";

    private final String deleteStatement = "DELETE From harvested_block where id = ?";

    public void insert(HarvestedBlockBean harvestedBlockBean) {

        try {

            Connection conn = DatabaseHandler.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(this.insertStatement);

            pstmt.setString(1, harvestedBlockBean.getBlockType());
            pstmt.setInt(2, harvestedBlockBean.getPosX());
            pstmt.setInt(3, harvestedBlockBean.getPosY());
            pstmt.setInt(4, harvestedBlockBean.getPosZ());
            pstmt.setLong(5, harvestedBlockBean.getHarvestDate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<HarvestedBlockBean> getRegeneratedBlocks() {
        String select = this.selectStatement + " WHERE harvest_date < " + (new Timestamp(System.currentTimeMillis()).getTime()) + ";";
        Connection conn = DatabaseHandler.getConnection();
        List<HarvestedBlockBean> blockBeanList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(select);

            while (resultSet.next()) {
                blockBeanList.add(new HarvestedBlockBean(resultSet.getInt("id"), resultSet.getString("block_type"),
                        resultSet.getInt("pos_x"), resultSet.getInt("pos_y"),
                        resultSet.getInt("pos_z"), resultSet.getLong("harvest_date")));
            }
            return blockBeanList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return blockBeanList;
        }
    }

    public void deleteHarvestedBlock(int id) {
        Connection conn = DatabaseHandler.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(this.deleteStatement);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isBlockPresent(int x,int y,int z) {
        String select = this.selectStatement + " WHERE pos_x = ? AND pos_y = ? AND pos_z = ?;";
        Connection conn = DatabaseHandler.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(select);
            stmt.setInt(1,x);
            stmt.setInt(2,y);
            stmt.setInt(3,z);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            };
            return false;


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}

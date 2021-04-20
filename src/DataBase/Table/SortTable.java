package DataBase.Table;

import DataBase.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SortTable {
    private Table table;
    private PreparedStatement pstmt;
    private DataBase db;
    private Statement stmt;
    private String[][] columns = {{"value", "text"}, {"score", "integer"}} ;


    public SortTable(DataBase db, Table table) throws SQLException {
         this.db = db;
         this.table = table;
         this.pstmt = preparedStatement();

         this.stmt = this.db.getConnection().createStatement();
     }

    public SortTable(DataBase db) throws SQLException {
        this.db = db;

        this.stmt = this.db.getConnection().createStatement();
    }

    /**
     * This method prepare the Statement to write,
     * to be able to write to the database you need to create prepare Statment.
     *
     * The Prepare statement has the table name, the names of the columns and number of values to write.
     *
     * @return - A PreparedStatement instance.
     * @throws SQLException
     */
    private PreparedStatement preparedStatement() throws SQLException {
        String columns = "";
        String values = "";

        for (int i = 0; i < table.getColumns().length; i++) {
            if (i != 0) columns += ",";
            if (i != 0) values += ",";

            columns += table.getColumns()[i][0];
            values += "?";
        }

        String sql = String.format("INSERT INTO %s(%s) VALUES (%s)", this.table.getDbName(), columns, values);
        DataBase db = this.table.getDataBase();

        return db.getConnection().prepareStatement(sql);
    }

    public void sortAll() throws SQLException {
        String[] tables = this.db.getTables();

        int index = 0;
        for (String t: tables) {
            index++;
            System.out.println(t);
            Table table = new Table(this.db, t, this.columns);
            sortBy(table, "score");
        }
    }

    public void sortBy(Table table, String column) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(String.format("SELECT * FROM %s ORDER BY %s ASC",
                table.getDbName(), column));

        String sql = "UPDATE " + table.getDbName() +" SET value = ? , "
                + "score = ? WHERE rowid = ?";

        int rowid = 1;
        PreparedStatement pstmt = this.db.getConnection().prepareStatement(sql);

        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2));
            pstmt.setString(1, rs.getString(1));
            pstmt.setString(2, rs.getString(2));
            pstmt.setInt(3, rowid);

            rowid++;
        }
        pstmt.executeUpdate();

    }

    public void sortBy(String column) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(String.format("SELECT * FROM %s ORDER BY %s ASC",
                this.table.getDbName(), column));

        String sql = "UPDATE " + this.table.getDbName() +" SET value = ? , "
                + "score = ? WHERE rowid = ?";

        int rowid = 1;
        PreparedStatement pstmt = this.db.getConnection().prepareStatement(sql);

        while (rs.next()) {
            pstmt.setString(1, rs.getString(1));
            pstmt.setString(2, rs.getString(2));
            pstmt.setInt(3, rowid);

            rowid++;
        }
        pstmt.executeUpdate();
    }
}

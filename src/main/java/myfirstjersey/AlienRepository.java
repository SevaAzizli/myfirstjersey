package myfirstjersey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {


    Connection conn = null;

    public AlienRepository() {

        try {
            Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
            String connectionUrl = "jdbc:sqlserver://NAZHQ001SG778; databaseName=Test;" ;

                  String user="sa";
                    String password="123456";

            conn = DriverManager.getConnection( connectionUrl,user,password );
            System.out.println( "Connected." );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Problem in classForname or getConnection" );
        }
    }


    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();

        String sql = "select * from alien";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( sql );
            while (rs.next()){
                Alien theAlien = new Alien();
                theAlien.setId(rs.getInt( 1 ) );
                theAlien.setName( rs.getString( 2 ) );
                theAlien.setPoint( rs.getInt( 3 ) );

                aliens.add( theAlien );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aliens;
    }


    public Alien getAlien(int id) {

        Alien theAlien = new Alien();
        String sql = "select * from alien where id="+id;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( sql );
            if (rs.next()){
                theAlien.setId(rs.getInt( 1 ) );
                theAlien.setName( rs.getString( 2 ) );
                theAlien.setPoint( rs.getInt( 3 ) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theAlien;
    }

    public void create(Alien a1) {

        String sql = "insert into alien values(?,?,?) ";
        try{

            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setInt( 1,a1.getId() );
            ps.setString( 2,a1.getName() );
            ps.setInt(3,a1.getPoint()  );
            ps.executeUpdate();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void update(Alien a1) {
        String sql = "update alien set name=?, point =? where id=?";

        try{
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setInt( 3,a1.getId() );
            ps.setString( 1,a1.getName() );
            ps.setInt( 2,a1.getPoint() );

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }



        }

    public void delete(int id) {

        String sql = "delete from alien where id=?";

        try {
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setInt( 1,id );
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

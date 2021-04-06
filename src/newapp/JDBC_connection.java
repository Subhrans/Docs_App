/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*
 */
package newapp;

/**
 *
 * @author Subhransu das
 */
import java.sql.*;
public class JDBC_connection {
    public static void main(String args[]) throws Exception
    {
        String url="jdbc:mysql://localhost:3306/jdbc_database";
        String uname="root";
        String pass="";
//        String myquery="select * from test";
        String myquery="insert into test values(1017,'pallabi',0987)";
        Class.forName("com.mysql.cj.jdbc.Driver"); // load and register the driver to do that build it connector 
        Connection conn=DriverManager.getConnection(url,uname,pass);
        Statement st=conn.createStatement();
//        ResultSet rs=st.executeQuery(myquery); // it return objects from database
        int count=st.executeUpdate(myquery);  //inserting data
        System.out.println("number of rows effected:"+count);
        // printing whole table data
        /*for(;;)
        {
        if(rs.next())
        {
    //        String name=rs.getString("name");
        String table=rs.getInt(1)+":"+rs.getString(2);
        System.out.println(table);
        }
        else
        {
            break;
        }
        }*/
        
        st.close();
        conn.close();
    }
}

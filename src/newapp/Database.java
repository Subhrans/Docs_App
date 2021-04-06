/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;

import java.sql.*;
import java.io.*;



/**
 *
 * @author Subhransu das
 */
public class Database {
    public static void main(String args[]) throws Exception
    {
        
      
        
        
        
        System.out.println("ok");
        
      
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
        
       
    }
}
class userDao{
    Connection  conn=null;
    public boolean Connect()
    {
        try{
            String url="jdbc:mysql://localhost:3306/java_docapp";
            String Admin_name="root";
            String Admin_pass="";
            Class.forName("com.mysql.cj.jdbc.Driver"); // load and register the driver to do that build it connector 
            conn=DriverManager.getConnection(url,Admin_name,Admin_pass);
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
        
           
    }
    public void Insert(String uname,String upassword,String ucontact,String uaddress)
    {
        String myquery="insert into users(uname,first_name,upass, contact,location,image) values(?,?, ?, ?, ?,?) ";
        PreparedStatement pst;
        
        try {
            
            pst = conn.prepareStatement(myquery);
            int pos =uname.indexOf('@');
            System.out.println(pos);
            String fname="";
            int i=0;
            while(i!=pos)
            {
                fname+=uname.charAt(i);
                i++;
            }
            System.out.println(fname);
            pst.setString(1,uname);
            pst.setString(2, fname);
            pst.setString(3,upassword);
            pst.setString(4,ucontact);
            pst.setString(5,uaddress);
            InputStream in = new FileInputStream("C:\\Users\\Subhransu das\\Documents\\NetBeansProjects\\NewApp\\src\\staticFiles\\images\\default_profile_pic_50.png");
            pst.setBlob(6, in);
            int count=pst.executeUpdate();  //inserting data
            System.out.println("number of rows effected:"+count);
            pst.close();
            conn.close();
        } catch (Exception ex) {
                System.out.println(ex);
        }
    }
    
    public void Dinsert(String dname,String username,String password,int age,String specialization,String degree,String chember_location,String opening_time,String closing_time,String weeks,String home_location,String phone_number,int fees)
    {
        
        try
        {
            String myquery="insert into doctors(dname, duname, dpassword, dage, Dspecialization, ddegree, dchember_location, dopen_time, dclose_time, dweek, dhome_loc, dcontact,dfees) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println(myquery);
            Doctors u1=new Doctors();
            
            PreparedStatement pst=conn.prepareStatement(myquery);
            pst.setString(1,dname );
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setInt(4, age);
            pst.setString(5, specialization);
            pst.setString(6, degree);
            pst.setString(7, chember_location);
            pst.setString(8,opening_time );
            pst.setString(9, closing_time);
            pst.setString(10, weeks);
            pst.setString(11, home_location);
            pst.setString(12,phone_number);
            pst.setInt(13, fees);
            int count=pst.executeUpdate();
            pst.close();
            conn.close();
          
        }
        catch(Exception c)
        {
            System.out.println(c);
        }
    }
    public User getUser(String Uname,String Upass)
    {
        
        
        
        
        
        
        try
        {
            String myquery="select uname,upass,image from users where uname=? and upass=? and status=?";
            System.out.println(myquery);
            User u1=new User();
            
            PreparedStatement pst=conn.prepareStatement(myquery);
            pst.setString(1, Uname);
            pst.setString(2, Upass);
            pst.setString(3,"false");
            
            ResultSet rs=pst.executeQuery();
            
            if(rs.next())
            {
                System.out.println("successfull");
                u1.uName=rs.getString(1);
                u1.uPass=rs.getString(2);
                u1.img = rs.getBytes("IMAGE");
                updateUser(Uname,Upass);
            
            }
            else
            {
                System.out.println("invalid password or username");
                return null;
            }
            pst.close();
            conn.close();
            
            return u1;
        }
        catch(Exception c)
        {
//            System.out.println(c);
        }
        return null;
    }
    public void updateUser(String Uname,String Upass)
    {
        try
        {
            String myquery="update users set status=? where uname=? and upass=? and status=?";
            System.out.println(myquery);
            User u1=new User();
            
            PreparedStatement pst=conn.prepareStatement(myquery);
            pst.setString(1, "true");
            pst.setString(2,Uname);
            pst.setString(3,Upass);
            pst.setString(4, "false");
            
           int count=pst.executeUpdate();  //inserting data
            System.out.println("number of rows effected:"+count);
            
//            if(rs.next())
//            {
//                System.out.println("successfull");
//                u1.uName=rs.getString(1);
//                u1.uPass=rs.getString(2);
//                u1.img = rs.getBytes("IMAGE");
//            
//            }
//            else
//            {
//                System.out.println("invalid password or username");
//              
//            }
            pst.close();
            conn.close();
            
      
        }
        catch(Exception c)
        {
            System.out.println(c);
        }
    }
    
    
    public Doctors getDoctors( int fetchd)
    {
        Doctors d1=new Doctors();
        try
        {
            String myquery="select * from doctors where did=?";
            System.out.println(myquery);
            Doctors u1=new Doctors();
            
            PreparedStatement pst=conn.prepareStatement(myquery);
            pst.setInt(fetchd,1);
           ResultSet rs=pst.executeQuery();
            
            if(rs.next())
            {
                System.out.println("successfull");
                d1.did=rs.getInt(1);
                d1.dname=rs.getString(2);
                d1.username=rs.getString(3);
                d1.password=rs.getString(4);
                d1.age=rs.getInt(5);
                d1.specialiazation=rs.getString(6);
                u1.degree=rs.getString(7);
                d1.chember_location=rs.getString(8);
                d1.opening_time=rs.getString(9);
                d1.closin_time=rs.getString(10);
                d1.weeks=rs.getString(11);
                d1.home_location=rs.getString(12);
                d1.phone_number=rs.getString(13);
                d1.fees=rs.getInt(14);
                d1.img=rs.getBytes("IMAGE");
                
                
                
//                u1.img = rs.getBytes("IMAGE");
//                updateUser(Uname,Upass);
            
            }
            else
            {
                System.out.println("invalid password or username");
                return null;
            }
            pst.close();
            conn.close();
            
            return d1;
        }
        catch(Exception c)
        {
//            System.out.println(c);
        }
        return null;
        }
    
    
    
    public void setAppoint(int ID,int DID)
    {
        String myquery="insert into appoint(ID,DID) values(?,?)";
        PreparedStatement pst;
        
        try {
            
            pst = conn.prepareStatement(myquery);
           
            pst.setInt(1,ID);
            pst.setInt(2, DID);
            int count=pst.executeUpdate();  //inserting data
            System.out.println("number of rows effected:"+count);
            pst.close();
            conn.close();
        } catch (Exception ex) {
                System.out.println(ex);
                
        }
     
    }
}






class User{
    int id;
    String uName;
    String uPass;
    byte []img;
}

class Doctors{
    int did;
    String dname;
    String username;
    String password;
    int age;
    String specialiazation;
    String degree;
    String chember_location;
    String opening_time;
    String closin_time;
    String weeks;
    String home_location;
    String phone_number;
    int fees;
    byte []img;
}
class banner{
    byte[] img;
}

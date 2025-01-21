package pullur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class UnEmp {
	public static void main(String[] args) {
		System.out.println("mysql (username : root), (password: root)");
		System.out.println("hi this yaswanth pillari(6305616996) from pullur village");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("enter 1 to insert the details into pullur unemployement database");
		System.out.println("enter 2 to see the all jobless people in pullur village");
		System.out.println("enter to 3  update your details");
		System.out.println("enter 4 to delete when you got job");
		int key = sc.nextInt();
		switch (key) {
		case 1 :UnEmp.insert();
		break;
		case 2 :UnEmp.retrive();
		break;
		case 3 :UnEmp.update();
		break;
		case 4 :UnEmp.delete();
		break;
		default:System.out.println("invalid choice, enter above mentioned number correctly");
		System.out.println("thank you");
		}
		
	}
		//to insert details
		public static void insert() {
			Scanner sc = new Scanner(System.in);
			System.out.println(" first see database before enter the serial no");
			int sno = sc.nextInt();
			System.out.println("enter person name");
			String name = sc.next();
			System.out.println("enter what you know corejava/sql/python");
			String skill = sc.next();
			System.out.println("enter which year passed out");
			int yop = sc.nextInt();
			System.out.println("enter your phone no");
			String branch = sc.next();
			
			Driver d;
			try {
				d = new Driver();
				DriverManager.registerDriver(d);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
				PreparedStatement ps = con.prepareStatement("insert into unemployees values(?,?,?,?,?)");
				ps.setInt(1,sno );
				ps.setString(2,name);
				ps.setString(3,skill);
				ps.setInt(4,yop);
				ps.setString(5, branch);
				
				int row =ps.executeUpdate();
				System.out.println(row+"row inserted");
				ps.close();
				con.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//For Read
		public static void retrive()
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys","root", "root");
				Statement s = con.createStatement();
				ResultSet rs=s.executeQuery("select * from unemployees");
				
				while(rs.next()==true) {
					System.out.println("rocord exists");
				System.out.print(rs.getInt(1)+" , ");
				System.out.print(rs.getString(2)+" , ");
				System.out.print(rs.getString(3)+" , ");
				System.out.print(rs.getString(4)+"  ,");
				System.out.print(rs.getString(5)+" \n ");

				}
				rs.close();
				s.close();
				con.close();
				
				} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			}
		
		
		//UPdate data
		public static void update() {
			Scanner sc = new Scanner(System.in);
			System.out.println("enter your name to update ");
			String name = sc.next();
			System.out.println("enter phone number");
			String phno = sc.next();
			
			System.out.println("enter serial number to update");
			int id = sc.nextInt();
			
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys","root", "root");
				PreparedStatement ps = con.prepareStatement("update unemployees set name =?,phno=? where sno =?");
				
				ps.setString(1,name);
				ps.setString(2, phno);
				ps.setInt(3,id);

						
				int row =ps.executeUpdate();
				System.out.println(row+"row updated");
				ps.close();
				con.close();
				
				} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			}
		
		
		//For Delete the details
		public static void delete() {
			Scanner sc = new Scanner(System.in);
			System.out.println("enter serial number to delete");
				int id =sc.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys","root", "root");
//			Statement s = con.createStatement();
			PreparedStatement ps = con.prepareStatement("delete from unemployees where sno =?");
			ps.setInt(1, id);
			int row=ps.executeUpdate();
			System.out.println(row+"row deleted");
			ps.close();
			con.close();
			
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		sc.close();
		}
		
		
}

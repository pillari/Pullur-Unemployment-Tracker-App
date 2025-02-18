package pullur;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class UnEmp {

	// make global to avoid scanner object creation in every block otheswise we have
	// to create Scanner obj for every block
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("mysql (username : root), (password: root)");
		System.out.println("hi this yaswanth(6305616996) from pullur village");

		boolean flag = true;

		while (flag) {
			System.out.println("enter 1 to Insert the details into pullur unemployement database");
			System.out.println("enter 2 to See the all jobless people in pullur village");
			System.out.println("enter to 3  Update your details");
			System.out.println("enter 4 to Delete");
			System.out.println("enter 5 to Search the data by name like ");
			System.out.println("enter 6 to Search the  by phone number ");

			System.out.println("enter 7 to quit");

			int key = sc.nextInt();
			switch (key) {
			case 1:
				insert();
				break;
			case 2:
				retrive();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				searchbyName();
				break;
			case 6:
				searchbyphoneNo();
			case 7: {
				flag = false;
				break;
			}

			default:
				System.out.println("invalid choice, enter above mentioned number correctly");
			}

		}
	}

	static void searchbyphoneNo() {
		System.out.println("enter phone number ");
		long phno = sc.nextLong();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from unemployees where phno = ?");
			ps.setLong(1, phno);
			ResultSet rs = ps.executeQuery();

			while (rs.next() == true) {

				System.out.print(rs.getInt(1)+" ,");
				System.out.print(rs.getString(2)+" ,");
				System.out.print(rs.getString(3)+" ,");
				System.out.print(rs.getInt(4)+" ,");
				System.out.print(rs.getString(5)+" \n");

			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		

	}

	public static void searchbyName() {
		System.out.println("enter first letter of your name to search ");
		String name = sc.next();
		name = name + "%";

//				String qry = "select * from event where title like ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from unemployees where name like ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next() == true) {

				System.out.print(rs.getInt(1)+" ,");
				System.out.print(rs.getString(2)+" ,");
				System.out.print(rs.getString(3)+" ,");
				System.out.print(rs.getInt(4)+" ,");
				System.out.print(rs.getString(5)+" ,\n");

			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// to insert details
	public static void insert() {
		System.out.println(" first see database before enter the serial no");
		int sno = sc.nextInt();
		System.out.println("enter person name");
		String name = sc.next();
		System.out.println("mention one programming that your proficient in");
		String skill = sc.next();
		System.out.println("enter which year passed out");
		int yop = sc.nextInt();
		System.out.println("enter your phone no");
		String branch = sc.next();

		try {
			Driver d = new Driver();
			DriverManager.registerDriver(d);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
			PreparedStatement ps = con.prepareStatement("insert into unemployees values(?,?,?,?,?)");
			ps.setInt(1, sno);
			ps.setString(2, name);
			ps.setString(3, skill);
			ps.setInt(4, yop);
			ps.setString(5, branch);

			int row = ps.executeUpdate();
			System.out.println(row + "row inserted");
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// For Read
	public static void retrive() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from unemployees");

			while (rs.next() == true) {
				System.out.println("rocord exists");
				System.out.print(rs.getInt(1) + " , ");
				System.out.print(rs.getString(2) + " , ");
				System.out.print(rs.getString(3) + " , ");
				System.out.print(rs.getString(4) + "  ,");
				System.out.print(rs.getString(5) + " \n ");

			}
			rs.close();
			s.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// UPdate data
	public static void update() {
		System.out.println("enter the serial number to update");
		int sno = sc.nextInt();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from unemployees where sno =?");
			ps.setInt(1, sno);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				boolean flag = true;
				while (flag) {
					System.out.println("enter the option to update");
					System.out.println("enter 1 to update name");
					System.out.println("enter 2 to update skills");
					System.out.println("enter 3 to update year of passed out");
					System.out.println("enter 4 to update phone number");
					System.out.println("enter 5 to update exit");
					int key = sc.nextInt();
					switch (key) {
					case 1: {
						System.out.println("enter new title");
						String name = sc.next();
						ps = con.prepareStatement("update unemployees  set name =? where sno = ?");
						ps.setString(1, name);
						ps.setInt(2, sno);
						int row = ps.executeUpdate();
						System.out.println(row + "unemployees name updated");
						break;
					}
					case 2: {
						System.out.println("enter new skills");
						String skills = sc.next();
						ps = con.prepareStatement("update unemployees  set skills =? where sno = ?");
						ps.setString(1, skills);
						ps.setInt(2, sno);
						int row = ps.executeUpdate();
						System.out.println(row + "unemployees skills updated");
						break;
					}
					case 3: {
						System.out.println("enter new year of passeout");
						String yop = sc.next();
						ps = con.prepareStatement("update unemployees  set yop =? where sno = ?");
						ps.setString(1, yop);
						ps.setInt(2, sno);
						int row = ps.executeUpdate();
						System.out.println(row + "unemployees yearof passed out updated");
						break;
					}

					case 4: {
						System.out.println("enter new phone number");
						String phno = sc.next();
						ps = con.prepareStatement("update unemployees  set phno =? where sno = ?");
						ps.setString(1, phno);
						ps.setInt(2, sno);
						int row = ps.executeUpdate();
						System.out.println(row + "unemployees phone number updated");
						break;
					}
					case 5: {
						flag = false;
						break;
					}
					}
				}

			} else
				System.out.println("invalid choice to update");
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	// For Delete the details
	public static void delete() {
		System.out.println("enter serial number to delete");
		int id = sc.nextInt();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pullurunemplys", "root", "root");
//			Statement s = con.createStatement();
			PreparedStatement ps = con.prepareStatement("delete from unemployees where sno =?");
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			System.out.println(row + "row deleted");
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
//		sc.close();

	}


}

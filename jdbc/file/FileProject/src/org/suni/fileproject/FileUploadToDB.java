package org.suni.fileproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileUploadToDB {
	
	public static void main(String[] args)
	{
		String url = "jdbc:mysql://localhost:3306/file_db?user=root&password=root";
		Connection con = null;
		PreparedStatement pstmt = null;
		String filePath = "E:\\sunil\\file\\sunil.jpg";
		
		try(FileInputStream fin = new FileInputStream(new File(filePath)))
		{
			con = DriverManager.getConnection(url);
			pstmt = con.prepareStatement("insert into file_tab values(?,?)");
			
			pstmt.setInt(1, 2);
			pstmt.setBlob(2, fin);
			
			int count = pstmt.executeUpdate();
			System.out.println("no of rows affected: "+count);
			
			
			
		} 
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}//end of finally
		
		
	}//end of main

}//end of class

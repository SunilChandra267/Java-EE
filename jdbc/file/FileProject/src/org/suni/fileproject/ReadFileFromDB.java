package org.suni.fileproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadFileFromDB {
	public static void main(String[] args) throws IOException
	{
		String url = "jdbc:mysql://localhost:3306/file_db?user=root&password=root";
		String filePath = "E:\\sunil\\file\\sun.png";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			rs= stmt.executeQuery("select * from file_tab where id = 2");
			
			if(rs.next())
			{
				Blob blob = rs.getBlob(2);
				InputStream in = blob.getBinaryStream();
				OutputStream out = new FileOutputStream(filePath);
				
				int bytesRead = -1;
				byte[] buffer = new byte[4096];
				while((bytesRead = in.read(buffer))!=-1)
				{
					out.write(buffer, 0, bytesRead);
					
				}
				in.close();
				out.close();
				
				
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally{
			
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
	}

}

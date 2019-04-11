package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class BookTicket extends HttpServlet{
	@SuppressWarnings("serial")	
	Connection con;
	public void init() throws ServletException
		{
			con=DBConnection.getCon();
		}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String tno=req.getParameter("tno");
		String tname=req.getParameter("tname");
		int prs=Integer.parseInt(req.getParameter("prs"));
		int avail=Integer.parseInt(req.getParameter("avail"));
		int Remain=avail-prs;
		int price=prs*599;
		
		try {
			PreparedStatement ps=con.prepareStatement("update Train17 set available=? where tno=?");
			ps.setInt(1, Remain);
			ps.setString(2, tno);
			int k=ps.executeUpdate();
			if(k>0)
			{
				RequestDispatcher rd=req.getRequestDispatcher("Link.html");
				rd.include(req, res);
				
				pw.println("Your Ticket is Booked Successfully<br>");
				pw.println("Train No:"+tno+"<br>");
				pw.println("Train Name:"+tname+"<br>");
				pw.println("<br>No Of Seats Booked:"+prs+"<br>");
				pw.println("<br>Total Price Rs.:"+price+"<br>");
				pw.println("Remaining Seats:"+Remain+"<br>");
				
				
				
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}

package librarySystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
public class LibraryDBMgr {
	public static void editData(String editID,ArrayList input,String editTable) 
	{
		ArrayList<Librarian> ALibrarian=new ArrayList<Librarian>();
		ArrayList<Member> AMember;
		ArrayList<Ebook> AEbook;
		ArrayList<PaperBook> APaperBook=new ArrayList<PaperBook>();
		Connection conn = null;
			try {
					Class.forName("com.mysql.jdbc.Driver");
					String datasource="jdbc:mysql://localhost/library?user=kendy&password=ken033580964";
					conn = DriverManager.getConnection(datasource);
					Statement st = conn.createStatement();
					if(editTable.equals("librarian"))
					{
						Librarian storeLibrarian=new Librarian();
						ALibrarian=input;
						storeLibrarian=ALibrarian.get(0);
						String SQL = String.format("Update librarian Set LibrarianID='%s',LibrarianPassword='%s' Where LibrarianID='%s')",storeLibrarian.getlibrairanID(),storeLibrarian.getlibrarianPassword(),editID);
						st.execute(SQL);
						st.close();
					}
					else if(editTable.equals("member"))
					{
						Member storeMember;
						AMember=input;
						storeMember=AMember.get(0);
						boolean i=storeMember.getright();
						int x;
						if(i)
						{
							x=1;
						}
						else
						{
							x=0;
						}
						System.out.println(storeMember.getmemberID()+storeMember.getmemberPassword()+storeMember.getmemberemail()+storeMember.getnumberOfBorrowBook()+storeMember.getnumberOfOverdueBook()+storeMember.getnumberOfNoticeBook()+x+editID);
						String SQL = String.format("UPDATE member SET memberID='%s',memberPassword='%s',memberName='%s',memberRepublicofChinaNationalID='%s',email='%s',numberofBorrowBook='%d',numberOfOverdueBook='%d',numberOfNoticeBook='%d',memberRight='%d' WHERE memberID='%s'"
								,storeMember.getmemberID()
								,storeMember.getmemberPassword()
								,storeMember.getmemberName()
								,storeMember.memberRepublicofChinaNationalID()
								,storeMember.getmemberemail()
								,storeMember.getnumberOfBorrowBook()
								,storeMember.getnumberOfOverdueBook()
								,storeMember.getnumberOfNoticeBook()
								,x
								,editID
								);
						st.executeUpdate(SQL);
						st.close();
					}
					else if(editTable.equals("paperbook"))
					{
						PaperBook storePaperBook=new PaperBook();
						APaperBook=input;
						storePaperBook=APaperBook.get(0);
						int paperbookid=Integer.parseInt(editID);
						PreparedStatement pstmt;
						String SQL = String.format("Update paperbook Set bookTitle='%s',author='%s',publisher='%s',publicationDate='%s',summary='%s',state='%s',borrower='%s',borrowerTime='%s',price='%d' Where bookID='%d'"
						,storePaperBook.getbookTitle()
						,storePaperBook.getauthor()
						,storePaperBook.getpublisher()
						,storePaperBook.getpublicationDate()
						,storePaperBook.getsummary()
						,storePaperBook.getstate()
						,storePaperBook.getborrower()
						,storePaperBook.getborrowerTime()
						,storePaperBook.getprice()
						,paperbookid
						);
						st.executeUpdate(SQL);
						st.close();
					}
					else if(editTable.equals("ebook"))
					{
						Ebook storeEbook=new Ebook();
						AEbook=input;
						storeEbook=AEbook.get(0);
						int ebookid = Integer.parseInt(editID);
						
						System.out.println(storeEbook.getbookTitle()+storeEbook.getauthor()+storeEbook.getpublisher()+storeEbook.getpublicationDate()+storeEbook.getsummary()+storeEbook.getbookContext());
						String SQL = String.format("Update ebook Set bookTitle='%s',author='%s',publisher='%s',publicationDate='%s',summary='%s',bookContext='%s' Where bookID='%d'"
						,storeEbook.getbookTitle()
						,storeEbook.getauthor()
						,storeEbook.getpublisher()
						,storeEbook.getpublicationDate()
						,storeEbook.getsummary()
				        ,storeEbook.getbookContext()
				        ,ebookid);
						st.executeUpdate(SQL);
						
						st.close();
					}
					
			}
		catch(Exception e)
		{
		System.out.println("�L�k��s");
		}
	}
	
	public static void addData(ArrayList input,String addTable)throws Exception
	{
		ArrayList<Librarian> ALibrarian=new ArrayList<Librarian>();
		ArrayList<Member> AMember=new ArrayList<Member>();
		ArrayList<Ebook> AEbook=new ArrayList<Ebook>();
		ArrayList<PaperBook> APaperBook=new ArrayList<PaperBook>();
		Connection conn = null;
			try {
					Class.forName("com.mysql.jdbc.Driver");
					String datasource="jdbc:mysql://localhost/library?user=kendy&password=ken033580964";
					conn = DriverManager.getConnection(datasource);
					Statement st = conn.createStatement();
					if(addTable.equals("librarian"))
					{
						Librarian storeLibrarian=new Librarian();
						ALibrarian=input;
						storeLibrarian=ALibrarian.get(0);
						String SQL = String.format("INSERT INTO librarian VALUES ('%s', '%s')",storeLibrarian.getlibrairanID(),storeLibrarian.getlibrarianPassword());
						st.execute(SQL);
						st.close();
					}
					else if(addTable.equals("member"))
					{
						Member storeMember=new Member();
						AMember=input;
						storeMember=AMember.get(0);
						boolean i=storeMember.getright();
						int x;
						if(i)
						{
							x=1;
						}
						else
						{
							x=0;
						}
						System.out.println(storeMember.getmemberID()+storeMember.getmemberPassword()+storeMember.getmemberemail()+storeMember.getnumberOfBorrowBook()+storeMember.getnumberOfOverdueBook()+storeMember.getnumberOfNoticeBook()+x);
						String SQL = String.format("INSERT INTO member (memberID,memberPassword,memberName,memberRepublicofChinaNationalID,email,numberOfBorrowBook,numberOfOverdueBook,numberOfNoticeBook,memberRight) VALUES('%s','%s','%s','%s','%s','%d','%d','%d','%d')"
								,storeMember.getmemberID()
								,storeMember.getmemberPassword()
								,storeMember.getmemberName()
								,storeMember.memberRepublicofChinaNationalID()
								,storeMember.getmemberemail()
								,storeMember.getnumberOfBorrowBook()
								,storeMember.getnumberOfOverdueBook()
								,storeMember.getnumberOfNoticeBook()
								,x
								);
						st.execute(SQL);
						st.close();
					}
					else if(addTable.equals("paperbook"))
					{
						PaperBook storePaperBook=new PaperBook();
						APaperBook=input;
						storePaperBook=APaperBook.get(0);
						int x=0;
						String sql = String.format("SELECT MAX(bookID) FROM paperbook");
						st.execute(sql);
						ResultSet rs=st.getResultSet();
						System.out.println(rs);
						while(rs.next())
						{
							x=rs.getInt(1);
						}
						x=x+1;
						InitialGUI.setmaxID(x);
						PreparedStatement pstmt;
						String SQL = "INSERT INTO paperbook (bookID,bookTitle,author,publisher,publicationDate,summary,state,borrower,borrowerTime,price,bookType) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
						pstmt.setInt(1,x);
						pstmt.setString(2,storePaperBook.getbookTitle());
						pstmt.setString(3,storePaperBook.getauthor());
						pstmt.setString(4,storePaperBook.getpublisher());
						pstmt.setString(5,storePaperBook.getpublicationDate());
						pstmt.setString(6,storePaperBook.getsummary());
						pstmt.setString(7,storePaperBook.getstate());
						pstmt.setString(8,storePaperBook.getborrower());
						pstmt.setString(9,storePaperBook.getborrowerTime());
						pstmt.setInt(10,storePaperBook.getprice());
						pstmt.setString(11,storePaperBook.getbookType());
						pstmt.execute();
						
						pstmt.close();
					}
					else if(addTable.equals("ebook"))
					{
						int rid;
						PreparedStatement pstmt ;
						Ebook storeEbook=new Ebook();
						AEbook=input;
						storeEbook=AEbook.get(0);
						String sql = String.format("SELECT MAX(bookID) FROM ebook");
						st.execute(sql);
						ResultSet rs=st.getResultSet();
						int x=0;
						while(rs.next())
						{
							x=rs.getInt(1);
						}
						x=x+1;
						InitialGUI.setmaxID(x);
						System.out.println(storeEbook.getbookTitle()+storeEbook.getauthor()+storeEbook.getpublisher()+storeEbook.getpublicationDate()+storeEbook.getsummary()+storeEbook.getbookContext());
						String SQL = "INSERT INTO ebook (bookID,bookTitle,author,publisher,publicationDate,summary,bookContext,bookType) VALUES (?,?,?,?,?,?,?,?)";
						pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
						pstmt.setInt(1,x);
						pstmt.setString(2,storeEbook.getbookTitle());
						pstmt.setString(3,storeEbook.getauthor());
						pstmt.setString(4,storeEbook.getpublisher());
						pstmt.setString(5,storeEbook.getpublicationDate());
						pstmt.setString(6,storeEbook.getsummary());
				        pstmt.setString(7,storeEbook.getbookContext());
				        pstmt.setString(8,storeEbook.getbookType());
						pstmt.execute();
						pstmt.close();
						st.close();
					}
			}
		catch(Exception e)
		{
		System.out.println("�L�k�g�J");
		}
	}

	
	public static ArrayList searchData(String inputID,String usefunction) throws Exception
	{
		//�j�M�\��(�j�M��,���@�إ\��)
		ArrayList<Object> searchList = new ArrayList<Object>();
		Connection conn = null;
			try {
					Class.forName("com.mysql.jdbc.Driver");
					String datasource="jdbc:mysql://localhost/library?user=kendy&password=ken033580964";
					conn = DriverManager.getConnection(datasource);
					Statement st = conn.createStatement();
					if(usefunction.equals("librarian"))//�j�Mlibrarian
					{
						Librarian r1=new Librarian();
						String SQL = String.format("SELECT * FROM librarian Where LibrarianID = '%s' ",inputID);
						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						if(searchList.isEmpty())
						{
							while(rs.next())
							{
								r1.setLibrarian(rs.getString("LibrarianID"), rs.getString("LibrarianPassword"));
							}
							searchList.add(r1);
						}
						st.close();
					}
					else if(usefunction.equals("member"))//�j�Mmember
					{
						Member m1=new Member();
						String SQL = String.format("SELECT * FROM member Where memberID = '%s' ",inputID);

						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						while(rs.next())
						{
							m1.setMember(rs.getString("memberID"), rs.getString("memberPassword"),rs.getString("memberName"),rs.getString("email"),rs.getString("memberRepublicofChinaNationalID"),rs.getInt("numberofBorrowBook"),rs.getInt("numberOfOverdueBook"),rs.getInt("numberOfNoticeBook"),rs.getBoolean("memberRight"));                            
						}

						searchList.add(m1);
						st.close();
					}
					else if(usefunction.equals("MRID"))//�j�Mmember
					{
						Member m1=new Member();
						String SQL = String.format("SELECT * FROM member Where memberRepublicofChinaNationalID = '%s' ",inputID);

						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						while(rs.next())
						{
							m1.setMember(rs.getString("memberID"), rs.getString("memberPassword"),rs.getString("memberName"),rs.getString("email"),rs.getString("memberRepublicofChinaNationalID"),rs.getInt("numberofBorrowBook"),rs.getInt("numberOfOverdueBook"),rs.getInt("numberOfNoticeBook"),rs.getBoolean("memberRight"));                            
						}

						searchList.add(m1);
						st.close();
					}
					else if(usefunction.equals("searchpaperbook"))//��id�j�Mpaperbook
					{
						PaperBook p1=new PaperBook();
						int paperbookid = Integer.parseInt(inputID);
						String SQL = String.format("SELECT * FROM paperbook Where bookID = '%d' ",paperbookid);
						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						if(searchList.isEmpty())
						{
							while(rs.next())
							{
								
								p1.setPaperBook(rs.getString("bookID"),
										rs.getString("bookTitle"),
										rs.getString("author"),
										rs.getString("publisher"),
										rs.getString("publicationDate"),
										rs.getString("summary"),
										rs.getString("state"),
										rs.getString("borrower"),
										rs.getString("borrowerTime"),
										rs.getInt("price"));  

							}

							searchList.add(p1);
						}
						st.close();
					}
					else if(usefunction.equals("searchpkeyword"))//������r�j�Mpaperbook
					{
						String SQL =  "SELECT * FROM paperbook WHERE bookTitle LIKE '%" + inputID + "%'";
						if(inputID == null || inputID == "") {
						}else {
							st.execute(SQL);
						}
						ResultSet rs=st.getResultSet();
						if(searchList.isEmpty())
						{
							while(rs.next())
							{
								PaperBook p1=new PaperBook();
								p1.setPaperBook(rs.getString("bookID"),
										rs.getString("bookTitle"),
										rs.getString("author"),
										rs.getString("publisher"),
										rs.getString("publicationDate"),
										rs.getString("summary"),
										rs.getString("state"),
										rs.getString("borrower"),
										rs.getString("borrowerTime"),
										rs.getInt("price"));    
								
								searchList.add(p1);
							}
						}
						st.close();
					}
					else if(usefunction.equals("searchebook"))//��id�j�Mebook
					{
						PreparedStatement pstmt ;
						Ebook E1=new Ebook();
						int Ebookid = Integer.parseInt(inputID);
						String SQL = String.format("SELECT * FROM ebook Where bookID = '%d'",Ebookid);
						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						while(rs.next())
						{
							E1.setEbook(rs.getString("bookID"),
									rs.getString("bookTitle"),
									rs.getString("author"),
									rs.getString("publisher"),
									rs.getString("publicationDate"),
									rs.getString("summary"),
									rs.getString("bookContext"));      
						}
						
						System.out.println(E1.getbookID());
						searchList.add(E1);
						st.close();
					}
					else if(usefunction.equals("searchekeyword"))//������r�j�Mebook
					{
						String SQL = "SELECT * FROM ebook Where bookTitle LIKE '%" + inputID + "%'";
						if(inputID == null || inputID == "") {
						}else {
							st.execute(SQL);
						}
						ResultSet rs=st.getResultSet();
						while(rs.next())
						{
							Ebook E1=new Ebook();
							E1.setEbook(rs.getString("bookID"),
									rs.getString("bookTitle"),
									rs.getString("author"),
									rs.getString("publisher"),
									rs.getString("publicationDate"),
									rs.getString("summary"),
									rs.getString("bookContext"));
								searchList.add(E1);
						}
						
						st.close();
					}
					else if(usefunction.equals("CheckOverdueBook"))//�j�Mmember
					{
						String SQL = String.format("SELECT * FROM paperbook Where state = 'borrowed' OR state = 'notice'");
						
						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						while(rs.next())
						{
							PaperBook CODB=new PaperBook();
							CODB.setPaperBook(rs.getString("bookID"),
									rs.getString("bookTitle"),
									rs.getString("author"),
									rs.getString("publisher"),
									rs.getString("publicationDate"),
									rs.getString("summary"),
									rs.getString("state"),
									rs.getString("borrower"),
									rs.getString("borrowerTime"),
									rs.getInt("price"));   
							searchList.add(CODB);
						}
						
						st.close();
					}
					else if(usefunction.equals("GOM"))//�j�Mmember
					{
						
						String SQL = String.format("SELECT * FROM member Where numberOfNoticeBook > 0 OR numberOfOverdueBook > 0");
						
						st.execute(SQL);
						ResultSet rs=st.getResultSet();
						while(rs.next())
						{
							Member m1=new Member();
							m1.setMember(rs.getString("memberID"), rs.getString("memberPassword"),rs.getString("memberName"),rs.getString("email"),rs.getString("memberRepublicofChinaNationalID"),rs.getInt("numberofBorrowBook"),rs.getInt("numberOfOverdueBook"),rs.getInt("numberOfNoticeBook"),rs.getBoolean("memberRight"));   
							searchList.add(m1);
						}
						
						st.close();
					}
			}
		catch(Exception e)
		{
		
		}
			
			return searchList;
	}
	public static void deleteData(String deleteID,String deleteTable)
	{
		Connection conn = null;
			try {
					Class.forName("com.mysql.jdbc.Driver");
					String datasource="jdbc:mysql://localhost/library?user=kendy&password=ken033580964";
					conn = DriverManager.getConnection(datasource);
					
					Statement st = conn.createStatement();
					if(deleteTable.equals("member"))
					{
						String SQL = String.format("DELETE From member Where memberID = '%s'",deleteID);
						st.execute(SQL);
						
					}
					else if(deleteTable.equals("ebook"))
					{
						int i=Integer.valueOf(deleteID);
						String SQL = String.format("DELETE From ebook Where bookID = '%d'",i);
						st.execute(SQL);
						
					}
			}catch(Exception e)
			{
				
			}
	}
}

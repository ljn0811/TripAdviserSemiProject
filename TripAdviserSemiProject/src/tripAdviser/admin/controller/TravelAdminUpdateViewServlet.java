package tripAdviser.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import tripAdviser.admin.model.service.TravelAdminService;
import tripAdviser.travel.product.model.vo.TravelProduct;

/**
 * Servlet implementation class TravelAdminUpdateViewServlet
 */
@WebServlet("/travel/travelAdminUpdateView")
public class TravelAdminUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelAdminUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int trvNo = 0;

		if(!ServletFileUpload.isMultipartContent(request))
		{
			request.setAttribute("msg", "업로드오류");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		//저장경로
		String dir=getServletContext().getRealPath("/images/travel_upload_imgs");
		int maxSize=1024*1024*1024;
		MultipartRequest mr=new MultipartRequest(request, dir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		//DB로직 구성~!
		
		Enumeration e=mr.getFileNames();
		List<String> fileNames=new ArrayList();
		while(e.hasMoreElements())
		{
			fileNames.add(mr.getFilesystemName((String)e.nextElement()));
		}
		System.out.println(fileNames);
		
		
		String title=mr.getParameter("trvTitle");
					/*String ctg=mr.getParameter("trvCtg");*/ /*카테고리(대,소)2개??*/
		String province=mr.getParameter("trvProvince");
		String city=mr.getParameter("trvCity");
		String addr=mr.getParameter("trvAddress");
		String content=mr.getParameter("proContent");
		
		
		
		System.out.println(title);
				/*System.out.println(ctg);*/
		System.out.println(province);
		System.out.println(city);
		System.out.println(addr);
		System.out.println(content);
		
		
		
		TravelProduct tp=new TravelProduct();
		
		tp.setTrvNo(trvNo);
		tp.setTrvTitle(title);
				/*tp.setTrvSmallCtg(ctg);*/
		tp.setTrvProvince(province);
		tp.setTrvCity(city);
		tp.setTrvAddress(addr);
		tp.setAlbumUrls(fileNames);
		tp.setTrvReview(content);
		
		
		
		
		int result=new TravelAdminService().updateAdmin(tp);
	
		System.out.println(result);
		
		String msg=""; 
		String loc="/travel/TravelAdminListView";       /*/travel/travelProductDetail?trvNo=*/ 
		String view="/views/common/msg.jsp";
		
		if(result>0)
		{
			msg="여행지상품수정성공";
		}
		else
		{
			msg="여행지상품수정실패";
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

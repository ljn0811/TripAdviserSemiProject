package tripAdviser.travel.search.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import tripAdviser.travel.product.model.vo.TravelProduct;
import tripAdviser.travel.search.model.service.travelSearchService;
import static common.JDBCTemplate.*;


public class travelSearchDao {
	private Properties prop = new Properties();

	public travelSearchDao() {

		try {
			String fileName = travelSearchDao.class.getResource("search-query.properties").getPath();
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public double getStarAve(Connection conn, int trvNo)
	{
		double StarAve=0.0;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql= prop.getProperty("getStarRate");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,trvNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				StarAve=(rs.getDouble(1));
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("여행코드:"+trvNo+" 평점:"+StarAve);
			
		return StarAve;
	}
	
	
	public List<TravelProduct> travelSearchAll(Connection conn) {
	
		List<TravelProduct> list = new ArrayList<TravelProduct>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectProductAll");
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			 
			while (rs.next()) {
				double aveStarRate = new travelSearchService().getStarAve(rs.getInt("trv_No"));
				//double aveStarRate = rs.getDouble("point");
				TravelProduct tp=new TravelProduct();
				tp.setTrvNo(rs.getInt("trv_No"));
				tp.setTrvTitle(rs.getString("trv_Title"));
				tp.setTrvRepresentPic(rs.getString("trv_Represent_Pic"));
				tp.setTrvProvince(rs.getString("trv_Province"));
				tp.setTrvCity(rs.getString("trv_city"));
				tp.setTrvAddress(rs.getString("trv_address"));
				tp.setTrvDateStart(rs.getDate("trv_date_start"));
				tp.setTrvDateEnd(rs.getDate("trv_date_end"));
				tp.setTrvReview(rs.getString("trv_review"));
				tp.setTrvSmallCtg(rs.getString("trv_small_ctg_code"));
				tp.setTrvGps(rs.getString("trv_gps"));
				tp.setTrvDate(rs.getDate("trv_write_date"));
				tp.setMemberId(rs.getString("member_id"));
				tp.setAvgStarRate(aveStarRate);
				list.add(tp);
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	public List<TravelProduct> travelSearchCt(Connection conn, int categoryNo) {
		
		List<TravelProduct> list = new ArrayList<TravelProduct>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		switch(categoryNo)
		{
		case 0:sql=prop.getProperty("selectProductCT0");break;//전체베스트
		case 1:sql=prop.getProperty("selectProductCT1");break;//휴식거리 베스트
		case 2:sql=prop.getProperty("selectProductCT2");break;//즐길거리베스트
		case 3:sql=prop.getProperty("selectProductCT3");break;//먹을거리베스트
		case 4:sql=prop.getProperty("selectProduct1");break;//호텔베스트
		case 5:sql=prop.getProperty("selectProduct2");break;//게스트하우스베스트
		case 6:sql=prop.getProperty("selectProduct3");break;//템플스테이베스트
		case 7:sql=prop.getProperty("selectProduct4");break;//볼거리베스트
		case 8:sql=prop.getProperty("selectProduct5");break;//놀거리베스트
		case 9:sql=prop.getProperty("selectProduct6");break;//정식베스트
		case 10:sql=prop.getProperty("selectProduct7");break;//향토음식베스트
		case 11:sql=prop.getProperty("selectProduct8");break;//길거리음식베스트
	    default:sql=prop.getProperty("selectProductAll");break;
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			 
			while (rs.next()) {
				//double aveStarRate = new travelSearchService().getStarAve(rs.getInt("trv_No"));
				double aveStarRate = 0.0;
				if(rs.getString("point")!=null)
				{
					aveStarRate = rs.getDouble("point");
				}		
				TravelProduct tp=new TravelProduct();
				tp.setTrvNo(rs.getInt("trv_No"));
				tp.setTrvTitle(rs.getString("trv_Title"));
				tp.setTrvRepresentPic(rs.getString("trv_Represent_Pic"));
				tp.setTrvProvince(rs.getString("trv_Province"));
				tp.setTrvCity(rs.getString("trv_city"));
				tp.setTrvAddress(rs.getString("trv_address"));
				//tp.setTrvDateStart(rs.getDate("trv_date_start"));
				//tp.setTrvDateEnd(rs.getDate("trv_date_end"));
				//tp.setTrvReview(rs.getString("trv_review"));
				//tp.setTrvSmallCtg(rs.getString("trv_small_ctg_code"));
				//tp.setTrvGps(rs.getString("trv_gps"));
				//tp.setTrvDate(rs.getDate("trv_write_date"));
				//tp.setMemberId(rs.getString("member_id"));
				tp.setAvgStarRate(aveStarRate);
				list.add(tp);
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	
}

package com.dance.vos.app.event;


import com.dance.DanceConstant;
import com.dance.entity.TblDanceEvent;
import jsontag.annotation.JsonTagAnnotation;
import jsontag.annotation.onlineapi.JtOnlineApi;
import jsontag.annotation.pagination.JsonTagPaginationAction;
import jsontag.annotation.pagination.PaginationField;
import jsontag.annotation.validation.MyValidation;
import jsontag.bean.pagination.Pagination;
import jsontag.dao.JsonTagTemplateDaoImpl;
import jsontag.exception.JsonTagException;
import jsontag.interf.IDoService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JtOnlineApi(describe="",name="")
@JsonTagAnnotation(actionValue="/search",namespace="app/event")

public class SearchEvent extends JsonTagTemplateDaoImpl implements IDoService<Object> {

	
	@MyValidation(exceptionDesc="name格式异常")
	private String name;

	@MyValidation(exceptionDesc="keyword格式异常")
	private String keyword;

	@MyValidation(exceptionDesc="eventType格式异常")
	private String eventType;

	
	@MyValidation(exceptionDesc="stageDate格式异常")
	private Date stageDate;


	@MyValidation(exceptionDesc="currentPage格式异常")
	private Integer currentPage;

	@MyValidation(exceptionDesc="pageSize格式异常")
	private Integer pageSize;


	@Override
	public Object doService() throws Exception {
		if(null==currentPage){
			currentPage=1;
		}

		if(null==pageSize || pageSize>50) {
			pageSize=5;
		}



		Query queryCount;
		Query query;

		List<String> names=new ArrayList<String>();
		List<Object> vals=new ArrayList<Object>();

		String sqlCount=" select count(tde.id) from TblDanceEvent tde,TblDanceUser tdu where tdu.userId=tde.createUserId ";

		String sql=" select "
				+ " new TblDanceEvent(tde.id,tde.name,tde.createUserId,tde.createDateTime,"
				+ " tde.address,tde.stageDate,tde.beginTime,tde.endTime, "
				+ " tde.eventType,tde.maxPersonNum,tde.status,"
				+ " tdu.aka,tdu.avatarUrl) "
				+ " from TblDanceEvent tde,TblDanceUser tdu where tdu.userId=tde.createUserId ";



		String where="  and tde.status= "+DanceConstant.STATUS_2_YES ;

		if(StringUtils.isNotEmpty(eventType)){
			names.add("eventType");
			vals.add(eventType);
			where=where+" and tde.eventType=:eventType ";
		}
		if(StringUtils.isNotEmpty(keyword)){
			names.add("keyword1");
			vals.add("%"+keyword+"%");
			names.add("keyword2");
			vals.add("%"+keyword+"%");
			names.add("keyword3");
			vals.add("%"+keyword+"%");
			where=where+" and (tde.name like:keyword1 or tde.address like:keyword2 or tde.info like:keyword3) ";
		}


		queryCount=jsonTagDao.getCurrentSession()
				.createQuery(sqlCount+where);

		query=jsonTagDao.getCurrentSession().createQuery(sql+where+
				 " order by tde.id desc ");


		query.setFirstResult((currentPage-1)*pageSize);
		query.setMaxResults(pageSize);
		setQueryParameter(queryCount,names,vals);
		setQueryParameter(query,names,vals);



		Long count=(long) queryCount.list().get(0);

		if(count==0){
			return new Pagination(new ArrayList<TblDanceEvent>(),0,currentPage,pageSize);
		}

		if((currentPage-1)*pageSize>=count){
			throw new JsonTagException("已经最后一页了");
		}


		List<TblDanceEvent> events=query.list();

        Query queryAttach=jsonTagDao.getCurrentSession().createQuery("from TblDanceEventAttach where eventId=?");
        queryAttach.setFirstResult(0);
        queryAttach.setMaxResults(3);


 		for(TblDanceEvent tde:events){
            queryAttach.setParameter(0,tde.getId());
			tde.setAttachs(queryAttach.list());
		}



		return new Pagination(events,count.intValue(),currentPage,pageSize);
	}

	private static void setQueryParameter(Query q, List<String> names, List<Object> vals){
		for(int i=0;i<names.size();i++){
			q.setParameter(names.get(i), vals.get(i));
		}
	}

	public String getName() {
		return name;

	}

	public String getEventType() {
		return eventType;
	}

	public Date getStageDate() {
		return stageDate;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setStageDate(Date stageDate) {
		this.stageDate = stageDate;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}

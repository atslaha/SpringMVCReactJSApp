package il.co.springmvc.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import il.co.springmvc.dao.LinesStatisticsDAO;
import il.co.springmvc.entities.LinesStatistics;

/**
 * LinesStatisticsDAOImpl represents DAO for the domain class LinesStatistics. 
 * @author Artem Meleshko
 * @version 1.0 2017
 */
@Repository("linesDAO")
@Transactional
public class LinesStatisticsDAOImpl implements LinesStatisticsDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public LinesStatistics findId(Integer line_id) throws RuntimeException {
		LinesStatistics line = new LinesStatistics();
		line = (LinesStatistics) sessionFactory.openSession().get(LinesStatistics.class, line_id);
		return line;
	}

	@Override
	@Transactional
	public void createLineStatistic(LinesStatistics lineStatistic) throws RuntimeException {
		Session session = sessionFactory.openSession();
		session.save(lineStatistic);
	}

	@Override
	public void updateLineStatistic(LinesStatistics lineStatistic) throws IllegalArgumentException, RuntimeException {
		sessionFactory.openSession().saveOrUpdate(lineStatistic);
		
	}

	@Override
	@Transactional
	public void deleteById(LinesStatistics lineStatistic) throws RuntimeException {
		Transaction tx=null;
		Session session = null;
		try {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.delete(lineStatistic);
		tx.commit();
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {

	        if (!tx.wasCommitted()) {
	           tx.rollback();
	        }
	        session.flush(); 
	        session.close();
	    }

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinesStatistics> listLines() throws RuntimeException {
		return sessionFactory.openSession().createCriteria(LinesStatistics.class).list();
		//return session.getCurrentSession().createQuery("from stat").list();
		
	}

}

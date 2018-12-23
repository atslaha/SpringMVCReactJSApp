/**
 * 
 */
package il.co.springmvc.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import il.co.springmvc.dao.LinesStatisticsDAO;
import il.co.springmvc.entities.LinesStatistics;
import il.co.springmvc.services.LinesStatisticsServices;

/**
 * LinesStatisticsServicesImpl represents Services for the domain class LinesStatistics.
 * @author Artem Meleshko
 * @version 1.0 2017
 *
 */
@Service("linesService")
public class LinesStatisticsServicesImpl implements LinesStatisticsServices {
	
	@Autowired
	LinesStatisticsDAO lineDAO;

	@Override
	public LinesStatistics findId(Integer line_id) throws RuntimeException {
		return (LinesStatistics)lineDAO.findId(line_id);
	}

	@Override
	public void createLineStatistic(LinesStatistics lineStatistic) throws RuntimeException {
		lineDAO.createLineStatistic(lineStatistic);
	}

	@Override
	public void updateLineStatistic(LinesStatistics lineStatistic) throws IllegalArgumentException, RuntimeException {
		lineDAO.updateLineStatistic(lineStatistic);
	}

	@Override
	public void deleteById(LinesStatistics lineStatistic) throws RuntimeException {
		lineDAO.deleteById(lineStatistic);
	}

	@Override
	public List<LinesStatistics> listLines() throws RuntimeException {
		return lineDAO.listLines();
	}

}

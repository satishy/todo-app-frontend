package com.camellia.systems.todo.dao;

import com.camellia.systems.todo.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoTaskDaoImpl implements TodoTaskDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void save(Task task) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO TASK (TITLE,DESCRIPTION,TASKSTATUS,PRIORITY,ASSIGNEDUSER) " +
                "VALUES (:title, :description, :taskStatus, :priority, :assignedUser)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(task), keyHolder);
		task.setId(keyHolder.getKey().intValue());

	}

	@Override
	public void update(Task task) {
		String sql = "UPDATE TASK SET TITLE=:title, DESCRIPTION=:description, TASKSTATUS=:taskStatus, PRIORITY=:priority, ASSIGNEDUSER=:assignedUser WHERE id=:id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(task));

	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM TASK WHERE id= :id";
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));

	}

	@Override
	public Task findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM TASK WHERE id=:id";
		Task result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new TaskMapper());
		} catch (EmptyResultDataAccessException e) {
			// do nothing, return null
		}

		return result;
	}


	@Override
	public List<Task> findAll() {
		String sql = "SELECT * FROM task";
		List<Task> result = namedParameterJdbcTemplate.query(sql, new TaskMapper());

		return result;
	}

	public List<Task> findByStatus(String status) {
		return new ArrayList<Task>();
	}

	private SqlParameterSource getSqlParameterByModel(Task task) {
	    MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", task.getId());
		paramSource.addValue("title", task.getTitle());
		paramSource.addValue("description", task.getDescription());
		paramSource.addValue("taskStatus", task.getTaskStatus());
        paramSource.addValue("priority", task.getPriority());
        paramSource.addValue("assignedUser", task.getAssignedUser());

		return paramSource;
	}

	private static final class TaskMapper implements RowMapper<Task> {

		public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
			Task task = new Task();
			task.setId((rs.getInt("id")));
			task.setTitle((rs.getString("title")));
			task.setDescription((rs.getString("description")));
			task.setTaskStatus((rs.getString("taskStatus")));
			task.setPriority((rs.getString("priority")));
			task.setAssignedUser((rs.getString("assignedUser")));
			task.setUpdatedDate((rs.getTimestamp("updatedDate")));
			task.setCreatedDate((rs.getTimestamp("createdDate")));

			return task;
		}
	}

}
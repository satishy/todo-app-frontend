package com.camellia.systems.todo.controller;

import com.camellia.systems.todo.Common.Priority;
import com.camellia.systems.todo.Common.TaskStatus;
import com.camellia.systems.todo.dao.TodoTaskDao;
import com.camellia.systems.todo.domain.Task;
import com.camellia.systems.todo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class TodoAppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoAppController.class);

	TodoTaskDao todoTaskDao;

	TaskService taskService;

	public TodoTaskDao getTaskDao() {
		return todoTaskDao;
	}

	@Autowired
	public void setTaskDao(TodoTaskDao todoTaskDao) {
		this.todoTaskDao = todoTaskDao;
	}

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}


    /**
     *
     * @param model
     * @return
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		LOGGER.debug("index()");
		Task taskForm = new Task();
		populateDefaultModel(model);
		model.addAttribute("taskForm", taskForm);
		return "task/taskForm";
	}

    /**
     * save or update task
     * @param task
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public String saveOrUpdateTask(@ModelAttribute("taskForm") Task task,
								   BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		LOGGER.debug("saveOrUpdateTask() : {}", task);

		populateDefaultModel(model);
		if (result.hasErrors()) {
				return "task/taskForm";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if (task.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Task added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Task updated successfully!");
			}

			taskService.saveOrUpdate(task);

			// POST/REDIRECT/GET
			return "redirect:/task/" + task.getId();

		}
	}

    /**
     * List of tasks
     * @param model
     * @return
     */
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public String viewTaskList(Model model) {

		LOGGER.debug("viewTaskList()");
		model.addAttribute("tasks", taskService.findAll());
		return "task/taskList";

	}


    /**
     *
     * @param id
     * @param model
     * @return
     */
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public String taskViewById(@PathVariable("id") int id, Model model) {
		LOGGER.debug("taskViewById() id: {}", id);
		Task task = taskService.findById(id);
		if (task == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Task not found");
		}
		model.addAttribute("task", task);

		return "task/taskView";
	}

    /**
     * show update form
     * @param id
     * @param model
     * @return
     */
	@RequestMapping(value = "/task/{id}/update", method = RequestMethod.GET)
	public String showUpdateTaskForm(@PathVariable("id") int id, Model model) {
		LOGGER.debug("showUpdateTaskForm() : {}", id);
		Task taskForm = taskService.findById(id);
		populateDefaultModel(model);
		if (taskForm == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Task not found");
		}
		model.addAttribute("taskForm", taskForm);
		return "task/taskForm";
	}

    /**
     * Delete task
     * @param id
     * @param redirectAttributes
     * @param model
     * @return
     */
	@RequestMapping(value = "/task/{id}/delete", method = RequestMethod.POST)
	public String deleteTask(@PathVariable("id") int id, final RedirectAttributes redirectAttributes, Model model) {
		LOGGER.debug("deleteTask() : {}", id);
		taskService.deleteById(id);
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Task is deleted!");
		model.addAttribute("tasks", taskService.findAll());

		return "task/taskList";
	}

    /**
     *
     * @param model
     */
    private void populateDefaultModel(Model model) {

        Map<String, String> taskStatusList = new LinkedHashMap<String, String>();
		taskStatusList.put(TaskStatus.PENDING.getName(), TaskStatus.PENDING.getValue());
		taskStatusList.put(TaskStatus.INPROGRESS.getName(), TaskStatus.INPROGRESS.getValue());
		taskStatusList.put(TaskStatus.REVIEW.getName(), TaskStatus.REVIEW.getValue());
		taskStatusList.put(TaskStatus.TESTING.getName(), TaskStatus.TESTING.getValue());
		taskStatusList.put(TaskStatus.COMPLETED.getName(), TaskStatus.COMPLETED.getValue());
        model.addAttribute("taskStatusList", taskStatusList);

		Map<String, String> priorityList = new LinkedHashMap<String, String>();
		priorityList.put(Priority.BLOCKER.name(), Priority.BLOCKER.name());
		priorityList.put(Priority.CRITICAL.name(), Priority.CRITICAL.name());
		priorityList.put(Priority.MAJOR.name(), Priority.MAJOR.name());
		priorityList.put(Priority.MINOR.name(), Priority.MINOR.name());
		priorityList.put(Priority.TRIVIAL.name(), Priority.TRIVIAL.name());
		model.addAttribute("priorityList", priorityList);
    }

}


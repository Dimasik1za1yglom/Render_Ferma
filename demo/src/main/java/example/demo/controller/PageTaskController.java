package example.demo.controller;

import example.demo.dao.AccountRepository;
import example.demo.entities.Task;
import example.demo.entities.TaskStatus;
import example.demo.form.TaskForm;
import example.demo.security.details.AccountDetails;
import example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/task")
public class PageTaskController {

    private final TaskService taskService;
    private final AccountRepository accountRepository;
    static ExecutorService pool = Executors.newFixedThreadPool(3);

    @GetMapping
    public String tasksAccount(Model model) {
        List<Task> tasks = taskService.getAllTask(getIdUser());
        List<TaskStatus> taskStatuses = taskService.getAllTaskStatus(getIdUser());
        model.addAttribute("taskStatuses", taskStatuses);
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @PostMapping
    public String addTask(RedirectAttributes redirectAttributes, @Valid TaskForm taskForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().get(0).getDefaultMessage();
            redirectAttributes.addFlashAttribute("error", error);
        } else {
            try {
                Task taskSave = Task.builder()
                        .name(taskForm.getName())
                        .account(accountRepository.getReferenceById(getIdUser()))
                        .build();
                taskService.addTask(taskSave);
                finishTask(taskSave);
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            }
        }
        return "redirect:/task";
    }

    public Long getIdUser() {
        return ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccountId();
    }

    public void finishTask(Task task) {
        pool.submit(() -> {
            try {
                Thread.sleep(10_000);
                taskService.finishTask(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}

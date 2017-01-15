package jwd.wafepa.web.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Log;
import jwd.wafepa.model.Todo;
import jwd.wafepa.model.User;
import jwd.wafepa.repository.TodoRepository;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.web.dto.LogDTO;
import jwd.wafepa.web.dto.TodoDTO;

@RestController
@RequestMapping("/api/todo/")
public class TodoApi {
	
	private final Logger log = LoggerFactory.getLogger(TodoApi.class);
	
	@Inject
    private TodoRepository todoRepository;
	
	@Inject
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/JSON")
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO request) throws URISyntaxException {
        log.debug("POST /todo {}", request);
        final Todo todo = convertToTodo(request);
        final Todo result = todoRepository.save(todo);
        return ResponseEntity.created(new URI("/" + result.getId())).body(convertToCreateTodoResponse(result));
    }
	
	@RequestMapping(value = "/all-todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 0)
    @Transactional(readOnly = true)
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<TodoDTO>> allTodos() {
        log.debug("GET /all-todos");
        final List<Todo> result = todoRepository.findAll();
        return ResponseEntity.ok().body(result.stream().map(this::convertToAllTodos).collect(Collectors.toList()));
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TodoDTO> getTodo(@PathVariable Long id) {
		final Optional<Todo> result = Optional.ofNullable(todoRepository.findOne(id));
        if (result.isPresent()) {
            return ResponseEntity.ok().body(new TodoDTO(result.get()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	private Todo convertToTodo(TodoDTO dto) {
        final Todo todo = new Todo();
        todo.setTask(dto.getTask());
        todo.setDate(dto.getDate());
        todo.setStatus(dto.getStatus());
        todo.setUser(userRepository.findOne(dto.getUser().getId()));
        return todo; 
    }
	
	private TodoDTO convertToCreateTodoResponse(Todo todo){
		final TodoDTO dto = new TodoDTO(todo);
		/*dto.setDate(todo.getDate());
		dto.setStatus(todo.getStatus());
		dto.setId(todo.getId());
		dto.setTask(todo.getTask());
		dto.setUser(new UserTodoDTO(todo.getUser()));*/
		return dto;
	}
	
	private TodoDTO convertToAllTodos(Todo todo){
		final TodoDTO dto = new TodoDTO(todo);
        
        return dto;
	}

}

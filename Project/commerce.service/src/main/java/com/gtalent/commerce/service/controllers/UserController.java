package com.gtalent.commerce.service.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateUserRequest;
import com.gtalent.commerce.service.requests.UpdateUserRequest;
import com.gtalent.commerce.service.responses.GetUserListResponse;
import com.gtalent.commerce.service.responses.GetUserResponse;
import com.gtalent.commerce.service.services.UserSegmentService;
import com.gtalent.commerce.service.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("v1/users")
@CrossOrigin("*")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserSegmentService userSegmentService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, UserSegmentService userSegmentService){
        this.userRepository = userRepository;
        this.userService = userService;
        this.userSegmentService = userSegmentService;
    }

    @Operation(summary="Create user", description="Creates a new user. Required information is provided in the request body example below."+
        "<ul><li><b>email</b>: must be unique.</li><li><b>birthday</b>: optional (nullable).</li><li><b>role</b>: must be started with 'ROLE_'</li><li><b>hasNewsLetter</b>: defauts to true.</li></ul>")
    @PostMapping
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setHasNewsletter(request.getHasNewsletter());

        User newUser = userRepository.save(user);

        GetUserResponse response = new GetUserResponse(newUser.getFirstName(), newUser.getLastName(),
            newUser.getEmail(), newUser.getBirthday(), newUser.getAddress(), newUser.getCity(),
            newUser.getState(), newUser.getZipcode(), newUser.isHasNewsletter(),
            newUser.getUserSegments(), newUser.getLastLoginTime(), newUser.isDeleted());
        return ResponseEntity.ok(response);
    }

    @Operation(summary="Get all users", description="This API returns all users.")
    @GetMapping
    public ResponseEntity<List<GetUserListResponse>> getAllUsers(){
        List<User> users = userService.getAllUsers(); //調整為篩出 is_deleted=false

        System.out.print(users.stream().map(GetUserListResponse::new).toList());
        return ResponseEntity.ok(users.stream().map(GetUserListResponse::new).toList());
    }

    @Operation(summary="Get all users pagination", description="This API returns all users with pagination. The number of users per page and the page number need to be set.")
    @GetMapping("/page")
    public ResponseEntity<Page<GetUserListResponse>> getAllUsersPage(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @RequestParam(defaultValue= "") String query,
        @RequestParam(required=false) LocalDate date,
        @RequestParam(required=false) int orders,
        @RequestParam(required=false) boolean hasNewsLetter,
        //選單操作
        @Parameter(
            description = "User segment",
            schema = @Schema(allowableValues = {"Compulsive", "Collector", "Ordered Once", "Regular", "Returns", "Reviewer"})
        )@RequestParam(required=false) String segment
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(query, pageRequest, date, orders, hasNewsLetter, segment).map(GetUserListResponse::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUserResponse> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request){
        // 為了先檢查資料是否存在!
        GetUserResponse user = userService.updateUser(id, request);

        if(user!=null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setDeleted(true);
            userRepository.save(user.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/segments/{segmentId}")
    public ResponseEntity<Void>assignUserSegment(@PathVariable int id, @PathVariable int segmentId){
        userSegmentService.assignUserSegment(id, segmentId);
        return ResponseEntity.ok().build();
    }



}

package com.gtalent.commerce.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.models.UserSegment;
import com.gtalent.commerce.service.requests.CreateUserRequest;
import com.gtalent.commerce.service.requests.IdListRequest;
import com.gtalent.commerce.service.requests.UpdateUserRequest;
import com.gtalent.commerce.service.responses.DeleteUserResponse;
import com.gtalent.commerce.service.responses.GetUserListResponse;
import com.gtalent.commerce.service.responses.GetUserResponse;
import com.gtalent.commerce.service.services.UserSegmentService;
import com.gtalent.commerce.service.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin("*")
@Tag(name="User Controller")
public class UserController {
    private final UserService userService;
    private final UserSegmentService userSegmentService;

    @Autowired
    public UserController(UserService userService, UserSegmentService userSegmentService){
        this.userService = userService;
        this.userSegmentService = userSegmentService;
    }

    @Operation(summary="Create user", description="Creates a new user. Required information is provided in the request body example below."+
        "<ul><li><b>email</b>: must be unique.</li><li><b>birthday</b>: optional (nullable).</li><li><b>role</b>: must be started with 'ROLE_'</li><li><b>hasNewsLetter</b>: defauts to true.</li></ul>")
    @PostMapping
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request){
        User user = userService.createUser(request);

        if(user!=null){
            List<String> userSegments = user.getUserSegments().stream().map(us -> us.getSegment().getName()).toList();
            if (userSegments==null || userSegments.isEmpty()){
                userSegments = new ArrayList<>();
            }
            GetUserResponse response = new GetUserResponse(user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getBirthday(), user.getAddress(), user.getCity(),
                user.getState(), user.getZipcode(), user.isHasNewsletter(),
                userSegments, user.getLastLoginTime(), user.isDeleted());
            return ResponseEntity.ok(response);
        }return ResponseEntity.notFound().build();  
    }

    @Operation(summary="Get user by Id", description="This API returns user information with specified user id.")
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable int id){
        // 為了先檢查資料是否存在!
        User user = userService.getUser(id);
        List<UserSegment> userSegments = userSegmentService.getUserSegments(user);
        List<String> segments = userSegments.stream().map(userSegment -> userSegment.getSegment().getName()).toList();
        GetUserResponse result = new GetUserResponse(segments, user);
        if(result!=null){
            return ResponseEntity.ok(result);
        }return ResponseEntity.notFound().build();
    }

    @Operation(summary="Get all undeleted users", description="This API returns all undeleted users.")
    @GetMapping
    public ResponseEntity<List<GetUserListResponse>> getAllUsers(){
        List<User> users = userService.getAllUsers(); //調整為篩出 is_deleted=false
        if(users==null || users.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        System.out.print(users.stream().map(GetUserListResponse::new).toList());
        return ResponseEntity.ok(users.stream().map(GetUserListResponse::new).toList());
    }

    @Operation(summary="Get all users pagination", description="This API returns all users with pagination. The number of users per page and the page number need to be set.")
    @GetMapping("/page")
    public ResponseEntity<Page<GetUserListResponse>> getAllUsersPage(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @Parameter(description="Keyword for searching by firstName or lastName")
        @RequestParam(defaultValue= "") String query,
        //選單操作
        @Parameter(
            description = "date",
            schema = @Schema(allowableValues = {"Today", "This week", "Last week", "This month", "Last month", "Earlier"})
        )@RequestParam(required=false) String date,
        @RequestParam(required=false) Integer orders,
        @RequestParam(required=false) Boolean hasNewsLetter,
        //選單操作
        @Parameter(
            description = "User segment",
            schema = @Schema(allowableValues = {"Compulsive", "Collector", "Ordered Once", "Regular", "Returns", "Reviewer"})
        )@RequestParam(required=false) String segment
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> results = userService.getAllUsers(query, pageRequest, date, orders, hasNewsLetter, segment);
        if(results==null || results.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            Page<GetUserListResponse> response = results.map(GetUserListResponse::new); 
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/page2")
    public ResponseEntity<Page<GetUserListResponse>> getAllUsersPage2(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @RequestParam(defaultValue= "") String query,
        @RequestParam(required=false) Boolean hasNewsLetter,
        @RequestParam(required=false) Integer segmentId
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = userService.getAllUsers2(query, hasNewsLetter, segmentId, pageRequest);
        if(users==null || users.isEmpty()){
            return ResponseEntity.noContent().build();
        }return ResponseEntity.ok(users.map(GetUserListResponse::new));
    }

    @Operation(summary="Update user", description="This API updates user information with specified user id.")
    @PutMapping("/{id}")
    public ResponseEntity<GetUserResponse> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request){
        // 為了先檢查資料是否存在!
        GetUserResponse user = userService.updateUser(id, request);

        if(user!=null){
            return ResponseEntity.ok(user);
        }return ResponseEntity.notFound().build();
    }

    @Operation(summary="Delete user",
    description="This API deletes user with specified user id.<br>p.s. Set 'isDeleted' as true.")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        boolean check = userService.deleteUser(id);
        if(check){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary="Delete users",
    description="This API deletes users with specified user ids.<br>p.s. Set 'isDeleted' as true.")
    @PutMapping("/delete")
    public ResponseEntity<List<DeleteUserResponse>> deleteUsers(@RequestBody IdListRequest request){
        List<User> users = userService.deleteUsers(request);
        if(users!=null && !users.isEmpty()){
            return ResponseEntity.ok(users.stream().map(DeleteUserResponse::new).toList());
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary="Assign UserSegment with user_id & segment_id",
    description="<b>Segment:</b><ol><li>Compulsive</li><li>Collector</li><li>Ordered once</li><li>Regular</li><li>Return</li><li>Reviewer</li></ol>")
    @PostMapping("/{id}/segments/{segmentId}")
    public ResponseEntity<Void>assignUserSegment(@PathVariable int id, @PathVariable int segmentId){
        userSegmentService.assignUserSegment(id, segmentId);
        return ResponseEntity.ok().build();
    }


}

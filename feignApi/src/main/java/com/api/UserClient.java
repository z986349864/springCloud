package com.api;

import com.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("user-service")
@RequestMapping("/user")
public interface UserClient {
    @GetMapping("/{id}")
    User findById(@PathVariable("id") Long id);
    @PutMapping("/update")
    void updateUser(@RequestBody User user);

}

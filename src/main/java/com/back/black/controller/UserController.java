package com.back.black.controller;

import com.back.black.Service.UserService;
import com.back.black.Util.AliyunOSSUtil;
import com.back.black.Util.JwtUtil;
import com.back.black.Util.RestBean;
import com.back.black.Util.TheadlocalUtil;
import com.back.black.entity.User;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

;
@RestController
//@RequestMapping("/user")
public class UserController {
//    //登录验证
//    @PostMapping("/login")
//    public RestBean<String> login(@RequestParam("username") String username,
//                                  @RequestParam("password") String password){
//    }
    @Resource
    UserService userService;
    @Resource
    JwtUtil jwtUtil;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/findById")
    @ResponseBody
    public User findById(@RequestParam Integer id){
        return userService.findByUid(id);
    }
//    @GetMapping("/findAll")
//    @ResponseBody
//    public List<User> findAll(){
//        return userService.findAllUser();
//    }
        @GetMapping("/findAll")
        @ResponseBody
        public RestBean<List<User>> findAll(){
            return RestBean.success(userService.findAllUser());
        }

    @PostMapping("login")
    public RestBean<String> login(@RequestParam("username") String username,
                                  @RequestParam("password") String password){
        if(username == null){
            return RestBean.failure(401,"登陆失败，未输入用户名");
        }
        User loginUser = userService.findByUsername(username);
        if(loginUser == null){
            return RestBean.failure(401,"登陆失败，用户名不正确");
        }
        //登陆成功
        if(loginUser.getPassword().equals(password)){
            System.out.println(username + " 登陆成功");
//            Map<String,Object> claims = new HashMap<>();
//            claims.put("id",loginUser.getUid());
//            claims.put("usernamer",username);
            String token = jwtUtil.createJWT(loginUser);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token);
            return RestBean.success(token);
        }
        return RestBean.failure(401,"登陆失败，密码不正确");

    }

    @GetMapping("/userInfo")
    public RestBean<User> userInfo(){
        //从线程中得到数据map
        Map<String,Object> map = TheadlocalUtil.get();
        String username = (String) map.get("username");
//        System.out.println(map);
//        System.out.println(username);
        User user = userService.findByUsername(username);
        return RestBean.success(user);
    }
    @PatchMapping("/updatePassword")//读取请求体的json转化为map
    public RestBean<String> updatePassword(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        //校验密码
        String oldPassword = params.get("old_password");
        String newPassword = params.get("new_password");
        String re_Password = params.get("re_password");
        if(!StringUtils.hasLength(oldPassword) ||!StringUtils.hasLength(newPassword)||!StringUtils.hasLength(re_Password)){
            System.out.println(oldPassword);
            return RestBean.failure(401,"缺少必要参数");
        }
        if (!re_Password.equals(newPassword)){
            return RestBean.failure(401,"密码不一致");
        }
        Map<String,Object> map = TheadlocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        if(!user.getPassword().equals(oldPassword)){
            return RestBean.unauthoried("原密码输入不正确");
        }

        //2.调用service修改
        userService.updatePassword(newPassword,username);
        //修改redis中数据
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return RestBean.success("修改密码成功");
    }
    @PostMapping("/upload")
    public RestBean<String> upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String filename = null;
        if (originalFilename != null) {
            filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileUrl = AliyunOSSUtil.uploadFile(filename, file.getInputStream());

        return RestBean.success(fileUrl);
    }
    @PostMapping("/insertUser")
//    public RestBean<String> insertUser(@RequestParam("username") String username,
//                                       @RequestParam("password") String password,@RequestParam("email") String email){
//        System.out.println("insert");
//        User user = new User(null,username,email,password);
//        System.out.println(user);
//        userService.insertUser(user);
//        return RestBean.success(user.toString());
//    }
    public RestBean<String> insertUser(User user){
        userService.insertUser(user);
        System.out.println("insert"+user);
        return RestBean.success(user.toString());
    }
}

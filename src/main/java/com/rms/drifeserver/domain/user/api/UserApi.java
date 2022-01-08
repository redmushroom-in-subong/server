package com.rms.drifeserver.domain.user.api;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    @GetMapping("/userPage")
    public String user(Model model) {
        List<User> users= userService.findAllUsers();
        model.addAttribute("users",users);
        return "user";
    }
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("preResult",null);
        return "signup";
    }
    @PostMapping("/signupWeb")
    public String signupWeb(Model model,User user) {
        Integer result=userService.saveUser(user);
        model.addAttribute("preResult",result);
        return "signup";
    }
    //############################    API    ###############
    /*
    @yts
    request body 받아와서 회원가입 예제(postman 참고) @RequestBody 만 붙여주면됨
    --> request body가 User 랑 다른 형식이면 400에러를 던저준다
    ~~~ @RequestBody와 완전히 일치하는 생성자가 있어야 하는듯
     */
    @GetMapping("")
    @ResponseBody
    public  Map<String,Object> getAllUsers() {
        Map<String,Object> ret=new HashMap<String,Object>();
        try{
            List<User> retList=userService.findAllUsers();
            ret.put("state","success");
            ret.put("result",retList);
        }catch (Exception e){
            ret.put("state","error");
            ret.put("message",e.getMessage());
        }finally {
            return ret;
        }
    }
    @GetMapping("/{id}")
    @ResponseBody
    public  Map<String,Object> findUserById(@PathVariable("id") String userId) {
        Map<String,Object> ret=new HashMap<String,Object>();
        try{
            Optional<User> retObj=userService.findUserByUserId(userId);
            if(retObj.isPresent()){
                ret.put("state","success");
                ret.put("result",retObj);
            }else{
                ret.put("state","empty");
                ret.put("result","");
            }
        }catch (Exception e){
            ret.put("state","error");
            ret.put("message",e.getMessage());
        }finally {
            return ret;
        }
    }
    @PostMapping("")
    @ResponseBody
    public Map<String,Object> signup(@RequestBody User user) {
        Map<String,Object> ret=new HashMap<String,Object>();
        Optional<User> isExist=userService.findUserByUserId(user.getUserId());
        try{
            if(!isExist.isEmpty()){
                ret.put("state","fail");
                ret.put("message","userId (: "+isExist.get().getUserId()+" ) is already exist");
            }else{
                userService.saveUser(user);
                Optional<User> retObj=userService.findUserByUserId(user.getUserId());
                if(retObj==null){
                    throw new Exception("회원가입 실패");
                }
                ret.put("state","success");
                ret.put("result",retObj);
            }
        }catch(Exception e){
                ret.put("state","fail");
                ret.put("message",e.getMessage());
        }finally {
            return ret;
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    public Map<String,Object> updateUser(@PathVariable("id") String userId,@RequestBody User user){
        Map<String,Object> ret=new HashMap<String,Object>();
        try {
                Integer result = userService.updateUser(user,userId);
                if(result==0) {
                    ret.put("state","fail");
                    ret.put("result","no USER (ID:"+userId+")");
                }else{
                    Optional<User> retObj=userService.findUserByUserId(user.getUserId());
                    ret.put("state","success");
                    ret.put("result",retObj);
                }
        } catch (Exception e) {
            ret.put("state","error");
            ret.put("result",e.getMessage());
        }finally {
            return ret;
        }
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String,Object> deleteUser(@PathVariable("id") String userId){
        Map<String,Object> ret=new HashMap<String,Object>();
        try {
            Integer result = userService.deleteUser(userId);
            if(result==0) {
                ret.put("state","fail");
                ret.put("result","no USER (ID:"+userId+")");
            }else{
                ret.put("state","success");
                ret.put("result","deleted");
            }
        } catch (Exception e) {
            ret.put("state","error");
            ret.put("result",e.getMessage());
        }finally {
            return ret;
        }
    }


    //############################    API    ###############end

}

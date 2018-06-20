package com.levin.demo.controller;

import com.levin.demo.bean.BookBean;
import com.levin.demo.bean.User;
import com.levin.demo.common.JsonBody;
import com.levin.demo.common.UserDynamicJob;
import com.levin.demo.rabbit.HelloReceiver1;
import com.levin.demo.rabbit.HelloSender1;
import com.levin.demo.service.PunishInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@RestController//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@Controller
public class TestController {
    @Value(value = "${usename}")
    private String user;
    @Value(value = "${pwd}")
    private String pwd;
    @Autowired
    private BookBean bookBean;
    @Autowired
    private PunishInfoService punishInfoService;
    @Autowired
    private UserDynamicJob userDynamicJob;
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloReceiver1 helloReceiver1;

    private Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/listPunishInfo", produces = { "application/json;charset=UTF-8" })
    @JsonBody
    public Object listPunishInfo(){
        //throw  new ClassCastException();
        //throw  new BusinessException("test");
        return punishInfoService.listPunishInfo();
    }

    @RequestMapping(value = "/hello")
    public void hello() {
        helloSender1.send();
    }

    @RequestMapping(value = "/view")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/string")
    public String returnString() {
        return "index";
    }

    /**
     * 动态修改定时任务执行频率
     * @param cron
     * @return
     */
    @RequestMapping(value = "/cron")
    public String changeDynamicCron(String cron) {
        userDynamicJob.setCron(cron);
        return "Success";
    }

    @RequestMapping(value = "/test/userLogin", method = RequestMethod.POST)
    String userLogin(User user, Model model) {
        boolean verify = true;//TODO
        if (verify) {
            model.addAttribute("name", user.getName());
            model.addAttribute("password", user.getPassword());
            return "result.html";
        } else {
            return "redirect:/notVerify";
        }

    }

    @RequestMapping(value="/loginPage",produces = "text/html")
    String login(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }
}

package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }
    // view를 거치는 방식 - html 파일 필요로 한다
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 웹브라우저에서 name 받아야한다
        model.addAttribute("name", name);
        return "hello-template";
    }

    // view없이 데이터 그대로 내려주는 방식
    @GetMapping("hello-string")
    @ResponseBody // http의 body부분에 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 객체 그대로 내려주는 방식 - json형식임
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

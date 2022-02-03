package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")  // http://localhost:8080/sample/ 의 형태의 웹페이지를 처리할 것이라는 것을 명시
@Log4j
public class SampleController {

     @InitBinder   // localhost:8080/sample/ex03?title=test&dueDate=2022/02/03
     public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
     }

    @RequestMapping("")   // localhost:8080/sample/ 를 처리
    public void basic() {
        log.info("basic...................");
        // 리턴 결과가 없어도 동일한 이름의 basic.jsp 로 처리결과를 넘긴다.
    }

    @RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })   // localhost:8080/sample/basic 를 처리
    public void basicGet() {
        log.info("basic get...................");
    }

    @GetMapping("/basicOnlyGet")     // localhost:8080/sample/basicOnlyGet 를 처리
    public void basicGet2() {
        log.info("basic get only get...................");
    }

    @GetMapping("/ex01")     // localhost:8080/sample/ex01 를 처리
    public String ex01(SampleDTO dto) {
        log.info("" + dto);
        return "ex01";  // 뷰 페이지 이름을 나타낸다.
    }

    @GetMapping("/ex02")     // localhost:8080/sample/ex02?name=ohjiwoo&age=20 를 처리(파라미터는 ?뒤에 쿼리 형태로 적는다, 여러개를 적을 때에는 &로 연결)
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    @GetMapping("/ex02List")     // localhost:8080/sample/ex02List?ids=111&ids=222&ids=333 를 처리
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ids: " + ids);
        return "ex02List";
    }

    @GetMapping("/ex02Array")     // localhost:8080/sample/ex02Array?ids=111&ids=222&ids=333 를 처리
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));
        return "ex02Array";
    }

    @GetMapping("/ex02Bean")     // localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb 를 처리
    public String ex02Bean(SampleDTOList list) {
        log.info("list dtos: " + list);
        return "ex02Bean";
    }

    @GetMapping("/ex03")     // localhost:8080/sample/ex03 를 처리
    public String ex03(TodoDTO todo) {
        log.info("todo: " + todo);
        return "ex03";
    }

    @GetMapping("/ex04")     // localhost:8080/sample/ex04?name=pmk&age=24&page=10 를 처리
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "/sample/ex04";
    }

    @GetMapping("/ex05")     // localhost:8080/sample/ex05 를 처리
    public void ex05() {
        log.info("/ex05..........");
    }

    @GetMapping("/ex06")     // localhost:8080/sample/ex06 를 처리
    public @ResponseBody SampleDTO ex06() {
        log.info("/ex06..........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;
    }

    @GetMapping("/ex07")     // localhost:8080/sample/ex07 를 처리
    public ResponseEntity<String> ex07() {
        log.info("/ex07..........");

        // {"name": "홍길동"}
        String msg = "{\"name\": \"ohjiwoo\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")     // localhost:8080/sample/exUpload 를 처리
    public void exUpload() {
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")     // localhost:8080/sample/exUploadPost 를 처리
    public void exUploadPost(ArrayList<MultipartFile> files) {
        files.forEach(file -> {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());
            log.info("size:" + file.getSize());
        });
    }
}

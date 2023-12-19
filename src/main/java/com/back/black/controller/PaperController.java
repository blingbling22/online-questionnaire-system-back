package com.back.black.controller;

import com.back.black.Service.AnswerService;
import com.back.black.Service.OptionService;
import com.back.black.Service.PaperService;
import com.back.black.Service.QuestionService;
import com.back.black.Util.JwtUtil;
import com.back.black.Util.RestBean;
import com.back.black.entity.Answer;
import com.back.black.entity.Option;
import com.back.black.entity.Paper;
import com.back.black.entity.Question;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    JwtUtil jwtUtil;
    @Resource
    QuestionService questionService;
    @Resource
    OptionService optionService;
    @Resource
    PaperService paperService;

    @Resource
    AnswerService answerService;

    @GetMapping("/findQuestionByPaperId")
    @ResponseBody
    public RestBean<List<Question>> findAllByPaperId(@RequestParam Integer paperId) {
        List<Question> questions = questionService.findQuestionByPaperId(paperId);
        return RestBean.success(questions);
    }

    //test
    @GetMapping("/findOperationByQuestionId")
    @ResponseBody
    public RestBean<List<Option>> findOperationByQuestionId(@RequestParam Integer questionId) {
        List<Option> options = optionService.getByQuestionId(questionId);
        return RestBean.success(options);
    }
    //////////////////important////////
    @PostMapping("/uploadPaper")
    @ResponseBody
    //@RequestBody json
    public RestBean<String> uploadPaper(@RequestBody Paper paper
//                                        ,@RequestBody List<stu> nums
//                                        ,@RequestParam("questions") List<Question> questions
    ) {
        log.info("收到一张试卷");
        paper.setStatus("创建");
        paper.setCreate_time(LocalDateTime.now());
        System.out.println(paper);

        int id = paperService.insertPaper(paper);
        return RestBean.success("试卷id:"+id);
    }
    @GetMapping("/getPaper/{id}")
    @ResponseBody
    public RestBean<Paper> getPaper(
            @PathVariable Integer id
    ){
        log.info("请求试卷"+id);
        Paper paper = paperService.getPaperById(id);
        if(paper != null){
//            System.out.println(paper);
            return RestBean.success(paper);

        }
        return RestBean.failure(304,null);
    }

    @PostMapping("/uploadAnswer")
    @ResponseBody
    //@RequestBody json
    public RestBean<String> uploadAnswer(
            @RequestBody List<Answer> answers
    ) {

        log.info("收到一个提交试卷");
//        System.out.println("+++++++++++++++++++++++++++++++++++");
//        System.out.println(answers);
        answerService.insert(answers);

        return RestBean.success("提交成功");
    }
//收到一个提交试卷
//[Answer(id=null, answer_content=, question_id=41, paper_id=26, selection=null, question_type=1, option_ids=[], option_id=109), Answer(id=null, answer_content=, question_id=42, paper_id=26, selection=null, question_type=2, option_ids=[114, 115], option_id=null), Answer(id=null, answer_content=我昨晚, question_id=43, paper_id=26, selection=null, question_type=3, option_ids=[], option_id=null)]




}

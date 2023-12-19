package com.back.black;

import com.back.black.Mapper.UserMapper;
import com.back.black.Service.*;
import com.back.black.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SqlTests {
	@Resource
	UserMapper userMapper;
	@Test
	void mybatisplusTest(){
		System.out.println(userMapper.selectById(2));

		System.out.println(userMapper.selectById(232));

		//查询
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username","test3");

		System.out.println(userMapper.selectOne(wrapper));
		//分页在mybatis config 开启
		
		
		//跟新
//		userMapper.update(null, Wrappers.<User>update().set("username","test").eq("id",1));
		
	}

	@Resource
	QuestionService questionService;
	@Resource
	UserService userService;
	@Test
	void test(){
		Question id = questionService.query()
				.eq("id", 1)
				.one();
		System.out.println(userMapper.selectList(null));

	}

	@Test
	void add(){
		User user = new User(null,"insert_username","ins_email","ins_pass");
		userService.insertUser(user);
	}

	@Resource
	PaperService paperService;
	@Test
	void addPaper(){
		List<Option> options = new ArrayList<>();
		options.add(new Option(null,null,"啊啊啊",null));
		options.add(new Option(null,null,"吱吱吱吱z",null));
		options.add(new Option(null,null,"强强强强",null));
		options.add(new Option(null,null,"哇哇哇哇哇",null));

		List<Option> options2 = new ArrayList<>();
		options2.add(new Option(null,null,"123",null));
		options2.add(new Option(null,null,"1234",null));
		options2.add(new Option(null,null,"12强强强强",null));
		options2.add(new Option(null,null,"123哇哇哇哇哇",null));


		List<Question> questions = new ArrayList<>();
		questions.add(new Question(null,2,null,"这是标题手动创建",null,12.1,"AB",options));
		questions.add(new Question(null,1,null,"这是标题也手动创建123",null,100.0,"A",options2));
		paperService.insertPaper(
				new Paper(
						null,"试卷123","asd",
						1,"asd",null,null,null,100,90,
						questions));
	}
	@Resource
	OptionService optionService;
	@Test
	void getPaper(){
//		Paper paper = ;
		System.out.println(paperService.getPaperById(21));
//		System.out.println(optionService.getById(105));
//		System.out.println(questionService.getById(36));
	}

	@Test
	void addOption(){
		Option option = new Option(null,null,"ins_email","ins_pass");
		optionService.insertOpt(option);
//		User user = new User(null,"insert_username","ins_email","ins_pass");
//		userService.insertUser(user);
	}

	@Test
	void addTest(){
		Option Option =  new Option(null,null,"123","null");
		optionService.insertOpt(Option);
//		User user = new User(null,"insert_username","ins_email","ins_pass");
//		userService.insertUser(user);
	}
	@Test
	void time_(){
		LocalDateTime localDateTime = LocalDateTime.now(); // 获取当前日期和时间
		System.out.println(localDateTime);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 定义日期和时间格式
		String formatDateTime = localDateTime.format(formatter); // 格式化日期和时间
		System.out.println(formatDateTime); // 输出：2023-11-21 20:10:13
	}
	@Resource
	AnswerService answerService;
	@Test
	void addAnswer(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//		Answer answer = new Answer(null,"123",44,26,null,2,numbers,null);
		Answer answer = new Answer(null,"123",44,26,null,1,null,1);

		answerService.insert(answer);


	}


}

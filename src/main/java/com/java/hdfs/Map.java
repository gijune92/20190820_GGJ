package com.java.hdfs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper<(입력키<행번호> : 입력값<행의글자>) , (출력키<글자> : 출력값<1>)>
public class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	// 출력 키 변수
	protected Text textKey = new Text();
	// 출력 값 변수
	protected IntWritable intValue = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] values = value.toString().split(",");
		
		// 출력 키에 넣을 문자열 변수
		String strKey = values[8];
		// 출력 키에 문자열 변수 적용
		textKey.set(strKey);
		// 전체 결과 출력하기
		context.write(textKey, intValue);

		/*
		// 1. 한줄에서 열을 구분하여 데이터 만들기
		String[] values = value.toString().split(",");
		// 2. 열중에서 첫번째 값인 년도 구해오기
		String strKey = values[0] + "Year " + values[1] + "Month " + values[8];

		if (!"NA".equals(values[4]) && !"NA".equals(values[6])) {
			// 3. 년도를 Text 자료형으로 변환하기
			Text textKey = new Text();
			textKey.set(strKey);
			// 4. 출력의 값으로 정수형 1를 IntWritable 자료형으로 변환하기
			IntWritable intValue = new IntWritable(Integer.parseInt(values[11]));
			// 5. 전체 결과 값을 출력하기
			context.write(textKey, intValue);
		}
		*/
	}
	
}

package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	static Date time;
	String filename = "todolist.txt";
	
	public static void saveList(TodoList l, String filename)
	{
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList())
			{
				w.write(item.toSaveString());
			}
			
			w.close();
			
			System.out.println("모든 데이터가 저장되었습니다.");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename)
	{
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
		
			String oneline;
			
			while ((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem s = new TodoItem(category, title, desc, due_date, current_date);
				
				l.addItem(s);
				
				count++;
			}
			br.close();
			if(count != 0)
				System.out.println(count + "개 항목을 읽었습니다.");
			else
				System.out.println(filename + "파일에 저장된 항목이 없습니다.");
			
		} catch (FileNotFoundException e) {
			System.out.println(filename + "파일이 없습니다.");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createItem(TodoList list) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 추가]");
		System.out.print("카테고리 > ");
		
		category = sc.nextLine();
		
		System.out.print("제목 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복되었습니다.");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sc.nextLine();
		
		System.out.print("마감일자 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.println("삭제할 항목의 번호를 입력하시오. > ");
		
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		
		int num_count = 1;
		
		for (TodoItem item : l.getList()) {
			
			if (num_count == number) {
				System.out.print(number + ". [" + item.getCategory());
				System.out.println( "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				System.out.println("위 항목을 삭제하시겠습니까? (y/n) > ");
				char input = sc.next().charAt(0);
				
				if(input == 'y')
				{
					l.deleteItem(item);
					System.out.println("삭제되었습니다.");
					break;
				}
			}
			num_count++;
		}
	}

	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 수정]");
		System.out.print("수정할 항목의 번호를 입력하시오 > ");
		int number = sc.nextInt();
		/*if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}*/
		
		int num_count = 1;
		
		for (TodoItem item : l.getList()) {
			
			if (num_count == number) {
				System.out.print(number + ". [" + item.getCategory());
				System.out.println( "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			}
			num_count++;
		}
		sc.nextLine();
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		//sc.nextLine();

		System.out.print("새 제목 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		//sc.nextLine();
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		//sc.nextLine();
		
		System.out.print("새 마감일자 > ");
		String new_due_date = sc.nextLine().trim();
		//sc.nextLine();
		
		num_count = 1;
		
		for (TodoItem item : l.getList()) {
			
			if (num_count == number) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
			num_count++;
		}

	}

	public static void listAll(TodoList l) {
		
		int count = 0;
		
		for(TodoItem item : l.getList())
		{
			count++;
		}
		
		int number = 1;

		System.out.println("[전체 목록, 총 " + count + "개]");
		
		for (TodoItem item : l.getList()) {
			System.out.print(number + ". [" + item.getCategory());
			System.out.println( "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			number++;
		}
	}

	public static void findItem(TodoList l, String keyword) {
		// TODO Auto-generated method stub
		
		int number = 1;
		int count = 0;
		
		for(TodoItem item : l.getList())
		{
			if(item.getTitle().contains(keyword))
			{
				System.out.print(number + ". [" + item.getCategory());
				System.out.println( "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				count++;
			}
			else if(item.getDesc().contains(keyword))
			{
				System.out.print(number + ". [" + item.getCategory());
				System.out.println( "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				count++;
			}
			
			number++;
		}
		
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		
	}

	public static void findCategory(TodoList l, String keyword) {
		// TODO Auto-generated method stub
		int number = 1;
		int count = 0;
		
		for(TodoItem item : l.getList())
		{
			if(item.getCategory().contains(keyword))
			{
				System.out.print(number + ". [" + item.getCategory());
				System.out.println( "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				count++;
			}
			
			number++;
		}
		
		System.out.println("총 " + count + "개의 항목을 찾았습니다.");
		
	}

	public static void listCategory(TodoList l) {
		// TODO Auto-generated method stub
		int count = 0;
		int number = 0;
		
		for(TodoItem item : l.getList())
		{
			count++;
		}
		
		String[] saveCate = new String[count];
		int i = 0;
		
		for(TodoItem item : l.getList())
		{
			saveCate[i] = item.getCategory();
			i++;
		}
		
		int[] saveIndex = new int[count];
		
		for(int j = 0; j < count; j++)
			saveIndex[j] = 0;
		
		int lastIndex = 1;
		
		for(int j = 0; j < count; j++)
		{
			for(int k = j + 1; k < count; k++)
			{
				if(saveCate[k].equals(saveCate[j]))
				{
					saveIndex[k] = 1;
				}
			}
		}
		
		
		for(int j = 0; j < count; j++)
		{
			if(saveIndex[j] == 0)
			{

				number++;
			}
		}
		
		for(int j = 0; j < count; j++)
		{
			if(saveIndex[j] == 0)
			{
				System.out.print(saveCate[j]);
				
				if(lastIndex < number)
				{
					System.out.print(" / ");
				}
				lastIndex++;
			}
		}
		
		System.out.println("\n총 " + number + "개의 카데고리가 등록되어 있습니다!");
		
	}
}

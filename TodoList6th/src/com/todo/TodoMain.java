package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("todolist.txt");
		//boolean isList = false;
		boolean quit = false;
		//TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			//isList = false;
		
			String choice = sc.next();
			String keyword;
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				System.out.println("제목순으로 정렬");
				TodoUtil.listAll(l, "title", 1);
				//isList = true;
				break;

			case "ls_name_desc":
				System.out.println("제목역순으로 정렬");
				TodoUtil.listAll(l, "title", 0);
				//isList = true;
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬");
				TodoUtil.listAll(l, "due_date", 1);
				//l.sortByDate();
				//isList = true;
				break;

			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				keyword = sc.next();
				TodoUtil.findItem(l, keyword);
				break;
			
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬");
				TodoUtil.listAll(l, "due_date", 0);
				break;
			
			case "find_cate":
				keyword = sc.next();
				TodoUtil.findCategory(l, keyword);
				break;
				
			case "ls_cate":
				TodoUtil.listCategory(l);
				break;

			case "comp":
				int comp1 = sc.nextInt();
				TodoUtil.completeItem(l, comp1);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;

			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
			
			//if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}

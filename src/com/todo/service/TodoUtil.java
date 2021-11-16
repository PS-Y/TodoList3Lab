package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList list) {

		String title, desc;
		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 추가]\n" + "제목 > ");

		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복되어서는 안됩니다:(");
			return;
		}
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.printf("추가완료 :)");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 삭제]\n" + "삭제할 항목의 제목 > ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("없는 제목입니다 :(");
		}

		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.printf("삭제완료:)");
				break;
			}
		}
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.printf("[항목 수정]\n" + "수정할 항목의 제목 > ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 항목입니다:(");
			return;
		}

		System.out.print("새로운 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복되어선 안됩니다:(");
			return;
		}

		sc.nextLine();
		System.out.print("새로운 내용 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정완료;)");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록]\n");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			Writer w = new FileWriter("todolist.txt");

			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));

			String oneline;
			while ((oneline = br.readLine()) != null) {
				System.out.println(oneline);
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String Sdate = st.nextToken();
			}
			br.close();
			System.out.println("\n 이전 TodoList 로딩완료 :) ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

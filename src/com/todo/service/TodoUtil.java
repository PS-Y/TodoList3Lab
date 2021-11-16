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

		System.out.print("[�׸� �߰�]\n" + "���� > ");

		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��Ǿ�� �ȵ˴ϴ�:(");
			return;
		}
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.printf("�߰��Ϸ� :)");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ���� > ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("���� �����Դϴ� :(");
		}

		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.printf("�����Ϸ�:)");
				break;
			}
		}
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.printf("[�׸� ����]\n" + "������ �׸��� ���� > ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �׸��Դϴ�:(");
			return;
		}

		System.out.print("���ο� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��Ǿ �ȵ˴ϴ�:(");
			return;
		}

		sc.nextLine();
		System.out.print("���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�����Ϸ�;)");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���]\n");
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
			System.out.println("\n ���� TodoList �ε��Ϸ� :) ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

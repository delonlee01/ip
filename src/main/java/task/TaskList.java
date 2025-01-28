package task;

import java.util.ArrayList;

import exception.TaskNotFoundException;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }

    public static void insert(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
    }

    public static void delete(int idx) throws TaskNotFoundException {
        try {
            Task task = tasks.remove(idx);
            System.out.println("Got it. I've removed this task:\n" + task);
            System.out.printf("Now you have %d tasks in the list.%n", tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public static void markTaskAsDone(int idx) throws TaskNotFoundException {
        try {
            tasks.get(idx).markAsDone();
            System.out.println("Yee-haw! I've marked this task as done:");
            System.out.println(tasks.get(idx));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public static void markTaskAsNotDone(int idx) throws TaskNotFoundException {
        try {
            tasks.get(idx).markAsNotDone();
            System.out.println("Alright! I've marked this task as not done yet:");
            System.out.println(tasks.get(idx));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }
}

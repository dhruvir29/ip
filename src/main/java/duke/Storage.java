package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


/**
 * Handles the storage and loading of the text file.
 */
public class Storage {

    private String filepath;
    private TaskList tasks;

    /**
     * Constructor for storage.
     *
     * @param filepath the filepath of the file that will be used for storage.
     * @param tasks the TaskList that will be used.
     */

    public Storage(String filepath, TaskList tasks) {
        this.filepath = filepath;
        this.tasks = tasks;
    }


    /**
     * Creates the file and loads tasks from the file to the taskList.
     *
     * @throws IOException
     */

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public void saveTasks() throws IOException {
        try {
            if (!Files.isDirectory(Paths.get("data/"))) {
                Files.createDirectories(Paths.get("data/"));
            }

            if (!Files.exists(Paths.get("data/duke.txt"))) {
                Files.createFile(Paths.get("data/duke.txt"));
                System.out.println("New file created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(new File(filepath));
        while (scanner.hasNext()) {
            String[] split = scanner.nextLine().split("\\|");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].strip();
            }
            String description = split[2];
            boolean isDone = split[1].equals("X");
            switch (split[0]) {
            case "T":
                tasks.addTask(new ToDo(description, isDone));
                break;
            case "D":
                String by = split[3];
                tasks.addTask(new Deadline(description, by, isDone));
                break;
            case "E":
                String from = split[3];
                String to = split[4];
                tasks.addTask(new Event(description, split[3], split[4], isDone));
                break;
            }

        }
    }

    /**
     * Writes the data from the tasklist into the file.
     *
     * @param tasks TaskList being used to store the data.
     * @throws IOException
     */

    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        for (int i = 0; i < tasks.getSize(); i++) {
            fileWriter.write(tasks.getTask(i).toWriteString() + "\n");
        }
        fileWriter.close();
    }

}


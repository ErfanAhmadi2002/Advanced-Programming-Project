package managers;

import java.util.Scanner;

public class CliManager extends Thread{
    private final Scanner serverCommandReader;
    private final BotManager botManager;

    public CliManager(BotManager botManager) {
        this.serverCommandReader = new Scanner(System.in);
        this.botManager = botManager;
    }

    @Override
    public void run() {
        while (true) {
            String command = serverCommandReader.nextLine();
            if (command.startsWith("add bot")) {
                String result = botManager.handleServerCommand(command);
                System.out.println(result);
            }
        }
    }
}

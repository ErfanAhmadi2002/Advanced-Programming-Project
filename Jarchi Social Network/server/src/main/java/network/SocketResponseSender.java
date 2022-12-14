package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shared.events.Event;
import shared.gson.Deserializer;
import shared.gson.Serializer;
import shared.responses.Response;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketResponseSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Event.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .create();
    }

    public Event getEvent() {
        String eventString = scanner.nextLine();
        return gson.fromJson(eventString, Event.class);
    }

    public void sendResponse(Response response) {
        printStream.println(gson.toJson(response, Response.class));
    }

    public void closeResponseSender(){
        printStream.flush();
        printStream.close();
        scanner.close();
    }


}

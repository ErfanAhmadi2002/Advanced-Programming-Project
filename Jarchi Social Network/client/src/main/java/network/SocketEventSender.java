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

public class SocketEventSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketEventSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Event.class, new Serializer<>())
                .create();
    }

    public Response request(Event event) {
        String eventString = gson.toJson(event, Event.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        return gson.fromJson(responseString, Response.class);
    }

}


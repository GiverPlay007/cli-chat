package me.giverplay.clichat;

import org.json.JSONObject;

import java.util.Scanner;

public class Chat implements Runnable {

  private final WebSocketHandler handler;

  public static void main(String[] args) {
    new Chat().start();
  }

  public Chat() {
    this.handler = new WebSocketHandler("ws://localhost:4000");
  }

  public void start() {
    this.handler.connect();
    new Thread(this).start();
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("> ");
      String line = scanner.nextLine();
      System.out.println("You: " + line);

      JSONObject json = new JSONObject();
      json.put("type", "message");

      JSONObject data = new JSONObject();
      data.put("content", line);
      data.put("author", "Soldado Instant");

      json.put("data", data);

      handler.send(json.toString());
    }
  }
}

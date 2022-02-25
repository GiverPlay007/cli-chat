package me.giverplay.clichat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

public class WebSocketHandler extends WebSocketClient {

  private boolean isRunning;

  public WebSocketHandler(String uri) {
    super(URI.create(uri));
  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {
    System.out.println("Conectado");
  }

  @Override
  public void onMessage(String message) {
    JSONObject json = new JSONObject(message);
    JSONObject data = json.getJSONObject("data");

    String author = data.getString("author");
    String content = data.getString("content");

    System.out.print("\b\b");
    System.out.printf("%s: %s%n", author, content);
    System.out.print("> ");
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {
    System.out.println("Fechou: " + reason);
  }

  @Override
  public void onError(Exception ex) {
    ex.printStackTrace();
  }

  public boolean isRunning() {
    return isRunning;
  }
}

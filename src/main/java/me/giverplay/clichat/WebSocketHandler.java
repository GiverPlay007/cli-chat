package me.giverplay.clichat;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

public class WebSocketHandler extends WebSocketClient {

  private final Chat chat;

  public WebSocketHandler(Chat chat, String uri) {
    super(URI.create(uri));
    this.chat = chat;
  }

  @Override
  public void onOpen(ServerHandshake handshakedata) {
    System.out.println("Conectado");
  }

  @Override
  public void onMessage(String msg) {
    JSONObject json = new JSONObject(msg);
    JSONObject data = json.getJSONObject("data");

    switch (json.getString("type")) {
      case "message":
        processIncomingMessage(data);
        break;
    }
  }

  @Override
  public void onClose(int code, String reason, boolean remote) {
    System.out.println("Fechou: " + reason);
  }

  @Override
  public void onError(Exception ex) {
    ex.printStackTrace();
  }

  private void processIncomingMessage(JSONObject data) {
    String author = data.getString("author");
    String content = data.getString("content");
    String id = data.getString("id");

    Message message = new Message(content, new User(author, id), System.currentTimeMillis());

    chat.onMessage(message);
  }
}

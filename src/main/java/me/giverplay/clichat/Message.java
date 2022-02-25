package me.giverplay.clichat;

public class Message {

  private final String content;
  private final User author;

  private final long timestamp;

  public Message(String content, User author, long timestamp) {
    this.content = content;
    this.author = author;
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return author.getName() + ": " + content;
  }

  public String getContent() {
    return content;
  }

  public User getAuthor() {
    return author;
  }

  public long getTimestamp() {
    return timestamp;
  }
}

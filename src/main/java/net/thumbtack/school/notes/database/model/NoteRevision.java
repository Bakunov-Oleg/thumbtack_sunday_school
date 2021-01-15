package net.thumbtack.school.notes.database.model;

import java.time.LocalDateTime;
import java.util.Date;

public class NoteRevision {

  private int revisionId;
  private String body;
  private LocalDateTime created;

  public NoteRevision() {
  }

  public NoteRevision(int revisionId, String body, LocalDateTime created) {
    this.revisionId = revisionId;
    this.body = body;
    this.created = created;
  }

  public int getRevisionId() {
    return revisionId;
  }

  public void setRevisionId(int revisionId) {
    this.revisionId = revisionId;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}

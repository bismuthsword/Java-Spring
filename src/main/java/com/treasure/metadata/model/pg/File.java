package com.treasure.metadata.model.pg;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {

  @Id
  private String uuid;

  @Column(name = "user_uuid")
  private String userUuid;

  @Column(name = "storage_uuid")
  private String storageUuid;

  @Column(name = "storage_key")
  private String storageKey;

  @Column(name = "name")
  private String name;

  @Column(name = "path")
  private String path;

  @Column(name = "storage_path")
  private String storagePath;

  @Column(name = "file_type")
  private String fileType;

  @Column(name = "mime_type")
  private String mimeType;

  @Column(name = "size")
  private long size;

  @Column(name = "unencrypted_size")
  private long unencryptedSize;

  @Column(name = "creation_date")
  private Date creationDate;

  @Column(name = "last_modified")
  private Date lastModified;

  @Column(name = "favorited_at")
  private Date favoritedAt;

  @Column(name = "efk")
  private String efk;

  @Column(name = "shared")
  private boolean shared;

  @Column(name = "trashed")
  private boolean trashed;

  @Column(name = "upload")
  private boolean upload;

  @Column(name = "favorite")
  private boolean favorite;

  @Column(name = "parent_uuid")
  private String parentUuid;

  @Column(name = "attached")
  private boolean attached = true;

//
//  As per the article below, joining tables across databases is not possible in Hibernate/JPA
//  https://stackoverflow.com/questions/3552330/doing-a-join-over-2-tables-in-different-databases-using-hibernate
//
//  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//  private Media media;

  public boolean isModified(Date date) {
    return lastModified.before(date);
  }

  public boolean isUploadTimeouted(Date date) {
    return lastModified.before(date);
  }

  public boolean isExisted() {
    return Objects.nonNull(efk);
  }

}

package com.web.model;

import java.util.Date;

/**
 * 帖子
 */
public class Topic {

    private int id;
    private int classId;
    private String title;
    private String body;
    private int userId;
    private int hits;
    private int replyCount;
    private int modifiedBy;
    private Date modifiedOn;
    private int repliedBy;
    private Date repliedOn;
    private short isClose;
    private String ip;

    private String createTimeStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(int repliedBy) {
        this.repliedBy = repliedBy;
    }

    public Date getRepliedOn() {
        return repliedOn;
    }

    public void setRepliedOn(Date repliedOn) {
        this.repliedOn = repliedOn;
    }

    public short getIsClose() {
        return isClose;
    }

    public void setIsClose(short isClose) {
        this.isClose = isClose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", classId=" + classId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", hits=" + hits +
                ", replyCount=" + replyCount +
                ", modifiedBy=" + modifiedBy +
                ", modifiedOn=" + modifiedOn +
                ", repliedBy=" + repliedBy +
                ", repliedOn=" + repliedOn +
                ", isClose=" + isClose +
                ", ip='" + ip + '\'' +
                ", createTimeStr='" + createTimeStr + '\'' +
                '}';
    }
}

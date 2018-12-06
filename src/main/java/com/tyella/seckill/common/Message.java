package com.tyella.seckill.common;


import java.util.Objects;

/*
消息的格式，包含消息头和消息体
 */
public class Message<T> {

    private Head head;

    private T body;

    //TODO

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message<?> message = (Message<?>) o;
        return Objects.equals(head, message.head) &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, body);
    }
}

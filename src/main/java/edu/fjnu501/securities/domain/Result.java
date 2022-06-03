package edu.fjnu501.securities.domain;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Result implements Writable {

    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this(code, msg);
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(code);
        out.writeUTF(msg);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.code = in.readInt();
        this.msg = in.readUTF();
    }
}

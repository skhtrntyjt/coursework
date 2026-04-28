package cw;

public abstract class DbObject {
        public abstract String serialize();
        public abstract void deserialize(String s);
    }
package ru.storage.serializer;

import ru.model.Resume;

import java.io.*;

public interface StreamSerializer {

    abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    Resume doRead(InputStream inputStream) throws IOException;
}

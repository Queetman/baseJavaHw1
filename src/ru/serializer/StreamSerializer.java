package ru.serializer;

import ru.model.Resume;
import java.io.*;

public interface StreamSerializer {

    void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    Resume doRead(InputStream inputStream) throws IOException;
}

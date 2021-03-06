package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;
import ru.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.Files.list;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private StreamSerializer streamSerializerStrategy;

    protected PathStorage(String dir, StreamSerializer streamSerializerStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.streamSerializerStrategy = streamSerializerStrategy;
    }

    @Override
    public int size() {
        try {
            return (int) list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory error", null);
        }
    }

    @Override
    public void clear() {
        try {
            list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected List<Resume> getSortableList() {

        try {
            return Files.list(directory).map(this::getResume).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("IO error", "directory is empty ");
        }


    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    //будет абстрактный метод doRead который читает реюме из  файла
    @Override
    protected Resume getResume(Path path) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Cant found resume", path.toString(), e);
        }
    }

    @Override
    protected void updateResume(Path path, Resume resume) {
        try {
            streamSerializerStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Can't delete resume", path.toString(), e);
        }
    }

    @Override
    protected void saveNewResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Cannot create path", path.toString(), e);
        }
        updateResume(path, resume);
    }
}



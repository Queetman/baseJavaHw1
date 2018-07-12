package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
                directory = Paths.get(dir);
               Objects.requireNonNull(directory, "directory must not be null");
             if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
                        throw new IllegalArgumentException(dir + " is not directory or is not writable");
                    }
            }

    @Override
    public int size() {
        Path[] Paths = directory.listPaths();
        if (Paths != null) {

            return Paths.length;
        } else throw new StorageException("IO error", "directory is empty ");
    }

    @Override
    public void clear() {
                try {
                        Files.list(directory).forEach(this::deleteResume);
                    } catch (IOException e) {
                        throw new StorageException("Path delete error", null);
                   } }

    @Override
    protected List<Resume> getSortableList() {

        Resume[] resumes;
        Path[] Paths = directory.listPaths();
        resumes = new Resume[Paths.length];

        if (Paths != null) {
            for (int i = 0; i < Paths.length; i++) {
                resumes[i] = getResume(Paths[i]);
            }
            return Arrays.asList(resumes);
        } else throw new StorageException("IO error", "directory is empty ");
    }

    @Override
    protected boolean isExist(Path Path) {
        return Path.exists();
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new Path(directory, uuid);
    }

    //будет абстрактный метод doRead который читает реюме из  файла
    @Override
    protected Resume getResume(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    protected void updateResume(Path Path, Resume resume) {
        try {
            doWrite(resume, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    protected void deleteResume(Path Path) {
        if (Path.delete()) {
            System.out.println(Path.getName() + " deleted");
        } else throw new IllegalArgumentException(Path.getName() + " doesn't deleted");
    }

    @Override
    protected void saveNewResume(Resume resume, Path Path) {
        try {
            Path.createNewPath();
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
        updateResume(Path, resume);
    }

    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;
}


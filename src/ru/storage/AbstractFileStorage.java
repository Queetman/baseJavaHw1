package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File>{

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }
    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() throws IOException {
        Files.deleteIfExists(directory.toPath());

    }

    @Override
    protected List<Resume> getSortableList() {
        return null;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume getResume(File file) {
        return null;
    }

    @Override
    protected void updateResume(File file, Resume resume) {

    }

    @Override
    protected void deleteResume(File file) {

    }

    @Override
    protected void saveNewResume(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }
    protected abstract void doWrite(Resume resume, File file) throws IOException;
}
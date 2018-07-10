package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.io.*;
import java.util.*;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    //имя файлов uuid
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
        File[] files = directory.listFiles();
        if (files != null)

            return files.length;
        else throw new StorageException("IO error", "directory is empty ");
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteResume(file);
            }
        } else throw new StorageException("IO error", "directory is empty ");
    }

    @Override
    protected List<Resume> getSortableList() {

        Resume[] resumes;
        File[] files = directory.listFiles();
        resumes = new Resume[files.length];

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                resumes[i] = getResume(files[i]);
            }
            return Arrays.asList(resumes);
        } else throw new StorageException("IO error", "directory is empty ");
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    //будет абстрактный метод doRead который читает реюме из  файла
    @Override
    protected Resume getResume(File file) {
        try {
            return doRead(new  BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            doWrite(resume,new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (file.delete()) {
            System.out.println(file.getName() + " deleted");
        } else throw new IllegalArgumentException(file.getName() + " doesn't deleted");
    }

    @Override
    protected void saveNewResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        updateResume(file, resume);
    }

    protected abstract void doWrite(Resume resume, OutputStream file) throws IOException;

    protected abstract Resume doRead(InputStream file) throws IOException;
}


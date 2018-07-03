package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.io.File;
import java.io.IOException;
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
        return directory.listFiles().length;
    }

    @Override
    public void clear() {

        if (directory.exists()) {

            for (File file : Objects.requireNonNull(directory.listFiles())) {
                file.delete();
            }
        }
    }

    @Override
    protected List<Resume> getSortableList() {

        Resume[] resumes;

        File[] files = directory.listFiles();
        resumes = new Resume[files.length];

        if(resumes.length!=0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    resumes[i] = doRead(files[i]);
                } catch (IOException e) {
                    throw new StorageException("IO error", "cant read file " + files[i].getName(), e);
                }
            }
            return Arrays.asList(resumes);
        }
        else  throw new StorageException("IO error", "directory is empty ");
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
        Resume resume;
        try {
            resume = doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        return resume;
    }

    @Override
    protected void updateResume(File file, Resume resume) {
        try {
            doWrite(resume, file);
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

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

}


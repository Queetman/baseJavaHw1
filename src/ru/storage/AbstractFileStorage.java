package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return directorySize(directory);
    }

    @Override
    public void clear() throws IOException {
        Files.deleteIfExists(directory.toPath());
    }

    @Override
    protected List<Resume> getSortableList() throws IOException {
        List<Resume> resumes=new ArrayList<>();

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory())
                    directorySize(directory);
            }
            for (File file : files) {

                if (file.isFile()) {   //проверяем, файл ли это

                    resumes.add(doRead(file));
                }
            }
        }
         return resumes;
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
    protected Resume getResume(File file) throws IOException {

        if (file.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        else return doRead(file);


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
        file.delete();
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

    protected abstract Resume doRead(File file) throws IOException;

    private int directorySize(File directory){
        int n=0;
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                for (File file : files) {
                    if (file.isDirectory())
                       directorySize(directory);
                }
                for (File file : files) {

                    if (file.isFile()) {   //проверяем, файл ли это
                        n++;
                    }
                }
            }
            return  n;
        }

}


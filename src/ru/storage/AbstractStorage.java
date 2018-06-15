package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage <SearchKey> implements Storage {

    public abstract int size();

    public abstract void clear();

    @Override
    public Resume get(String uuid) {
        SearchKey searchKey = getExistedSearchKey(uuid);

        return getResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        SearchKey searchKey = getExistedSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        SearchKey searchKey = getNotExistedSearchKey(resume.getUuid());
        saveNewResume(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        SearchKey searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public SearchKey getExistedSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public SearchKey getNotExistedSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List list = getSortableList();
        Collections.sort(list, Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }

    protected abstract List<Resume> getSortableList();

    protected abstract boolean isExist(SearchKey searchKey);

    protected abstract SearchKey getSearchKey(String uuid);

    protected abstract Resume getResume(SearchKey index);

    protected abstract void updateResume(SearchKey index, Resume resume);

    protected abstract void deleteResume(SearchKey index);

    protected abstract void saveNewResume(Resume resume, SearchKey index);
}

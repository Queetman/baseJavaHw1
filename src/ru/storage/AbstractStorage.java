package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractArrayStorage.class.getName());

    public abstract int size();

    public abstract void clear();

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SearchKey searchKey = getExistedSearchKey(uuid);

        return getResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SearchKey searchKey = getExistedSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SearchKey searchKey = getNotExistedSearchKey(resume.getUuid());
        saveNewResume(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Dellete " + uuid);
        SearchKey searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public SearchKey getExistedSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + "already not exists");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public SearchKey getNotExistedSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + "already exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        LOG.warning("getAllSort");
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

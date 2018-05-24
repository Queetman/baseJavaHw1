package ru.storage;

import ru.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer)searchKey!=-1;
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    @Override
    protected Integer getSearchKey(String uuid) {

        for (int i = 0; i < size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume getResume(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
        list.set((Integer) index,resume);
    }

    @Override
    protected void deleteResume(Object index) {
        list.remove( ((Integer) index).intValue());
    }

    @Override
    protected void saveNewResume(Resume resume, Object index) {
        list.add(resume);
    }


}



